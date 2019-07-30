package com.open.capacity.social.controller;

import java.util.List;
import java.util.UUID;

import com.open.capacity.social.config.ConnectSupport;
import com.open.capacity.social.exception.OcpSocialException;
import com.open.capacity.social.resp.R;
import com.open.capacity.social.util.SocialRedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Spring MVC Controller for handling the provider user sign-in flow.
 * <ul>
 * <li>POST /signin/{providerId}  - Initiate user sign-in with {providerId}.</li>
 * <li>GET /signin/{providerId}?oauth_token&amp;oauth_verifier||code - Receive {providerId} authentication callback and establish the connection.</li>
 * </ul>
 */
@Slf4j
@RestController
@RequestMapping("/signup")
public class RestProviderSignInController{

	@Autowired
	private  ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private  UsersConnectionRepository usersConnectionRepository;

	@Autowired
	private ConnectSupport connectSupport;

	@Autowired
	private SocialRedisUtils socialRedisUtils;


	@PostMapping(value="/{providerId}")
	public RedirectView signIn(@PathVariable String providerId, NativeWebRequest request) throws OcpSocialException {
		try {
			ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(providerId);
			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
			return new RedirectView(connectSupport.buildOAuthUrl(connectionFactory, request, parameters));
		} catch (Exception e) {
			log.error("Exception while building authorization URL: ", e);
			throw new OcpSocialException(e.getMessage());
		}
	}


	@GetMapping(value="/{providerId}", params="code")
	public R oauth2Callback(@PathVariable String providerId , @RequestParam("code") String code, NativeWebRequest request)  {
		R result = R.error();
		try {
			OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator.getConnectionFactory(providerId);
			Connection<?> connection = connectSupport.completeConnection(connectionFactory, request);
			List<String> userIds = usersConnectionRepository.findUserIdsWithConnection(connection);
			if(CollectionUtils.isEmpty(userIds)){
				String bindKey = UUID.randomUUID().toString();
				socialRedisUtils.saveConnectionData(bindKey,connection.createData());
				return result.setCode(2000).setMessage("请使用bindKey绑定！").setData(bindKey);
			}
			if (userIds.size() == 1) {
				usersConnectionRepository.createConnectionRepository(userIds.get(0)).updateConnection(connection);
				return result.setMessage("不能重复绑定！");
			} else {
				throw new OcpSocialException("查询到多条重复用户信息！");
			}
		} catch (Exception e) {
			log.error("Exception while completing OAuth 2 connection: ", e);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@GetMapping(value="/test/bind/{bindKey}")
	public R bind(@PathVariable String bindKey) throws OcpSocialException {
		try {
			socialRedisUtils.doPostSignUp(bindKey,"admin");
			return R.ok();
		} catch (Exception e) {
			log.error("Exception while building authorization URL: ", e);
			throw new OcpSocialException(e.getMessage());
		}
	}

}
