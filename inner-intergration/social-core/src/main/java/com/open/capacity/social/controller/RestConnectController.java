package com.open.capacity.social.controller;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.open.capacity.social.config.ConnectSupport;
import com.open.capacity.social.exception.OcpSocialException;
import com.open.capacity.social.resp.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.*;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Generic UI controller for managing the account-to-service-provider connection flow.
 * <ul>
 * <li>GET /connect/{providerId}  - Get a web page showing connection status to {providerId}.</li>
 * <li>POST /connect/{providerId} - Initiate an connection with {providerId}.</li>
 * <li>GET /connect/{providerId}?oauth_verifier||code - Receive {providerId} authorization callback and establish the connection.</li>
 * <li>DELETE /connect/{providerId} - Disconnect from {providerId}.</li>
 * </ul>
 */
@Slf4j
@RestController
@RequestMapping("/connect")
public class RestConnectController{

    @Autowired
    private  ConnectionFactoryLocator connectionFactoryLocator;
    @Autowired
    private  ConnectionRepository connectionRepository;

    @Autowired
    private  ConnectSupport connectSupport;

    @RequestMapping(method=RequestMethod.GET)
    public R connectionStatus() {
        Map<String, List<Connection<?>>> connections = connectionRepository.findAllConnections();
        Set<String> providerIds = connectionFactoryLocator.registeredProviderIds();
        if (!CollectionUtils.isEmpty(connections)) {
            Map<String,List<ConnectionData>> connectionDataMap = Maps.newHashMap();
            connections.forEach((key,value) ->{
                List<ConnectionData> connectionDataList = Lists.newArrayList();
                value.forEach(connection -> connectionDataList.add(connection.createData()));
                connectionDataMap.put(key,connectionDataList);
            });
            return R.ok(connectionDataMap);
        }
        return R.ok(connections);
    }

    @RequestMapping(value="/{providerId}", method=RequestMethod.GET)
    public R connectionStatus(@PathVariable String providerId) {
        List<Connection<?>> connections = connectionRepository.findConnections(providerId);
        if (!CollectionUtils.isEmpty(connections)) {
            List<ConnectionData> connectionDataList = Lists.newArrayList();
            connections.forEach(connection -> connectionDataList.add(connection.createData()));
            return R.ok(connectionDataList);
        }
        return R.error();
    }

    @RequestMapping(value="/{providerId}", method=RequestMethod.POST)
    public RedirectView connect(@PathVariable String providerId, NativeWebRequest request) throws OcpSocialException {
        try {
            ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(providerId);
            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
            return new RedirectView(connectSupport.buildOAuthUrl(connectionFactory, request, parameters));
        } catch (Exception e) {
            throw new OcpSocialException(e.getMessage());
        }
    }

    @RequestMapping(value="/{providerId}", method=RequestMethod.GET, params="code")
    public R oauth2Callback(@PathVariable String providerId, NativeWebRequest request) throws OcpSocialException {
        try {
            OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator.getConnectionFactory(providerId);
            Connection<?> connection = connectSupport.completeConnection(connectionFactory, request);
            connectionRepository.addConnection(connection);
            return R.ok();
        } catch (Exception e) {
            log.warn("Exception while handling OAuth2 callback (" + e.getMessage() + "). Redirecting to " + providerId +" connection status page.");
            throw new OcpSocialException(e.getMessage());
        }
    }

    @RequestMapping(value="/{providerId}", method=RequestMethod.DELETE)
    public R removeConnections(@PathVariable String providerId) {
        connectionRepository.removeConnections(providerId);
        return R.ok();
    }

    @RequestMapping(value="/{providerId}/{providerUserId}", method=RequestMethod.DELETE)
    public R removeConnection(@PathVariable String providerId, @PathVariable String providerUserId) {
        connectionRepository.removeConnection(new ConnectionKey(providerId, providerUserId));
        return R.ok();
    }

}
