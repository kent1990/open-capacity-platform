package com.open.capacity.social.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.capacity.social.util.ClientDetailsUtil;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("socialAuthenticationSuccessHandler")
public class SocialAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private ClientDetailsUtil clientDetailsUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String header = request.getHeader("Authorization");

        ClientDetailsUtil.ClientInfo clientInfo =  clientDetailsUtil.buildClientInfo(header);

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientInfo.getClientId());

        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_SORTED_MAP, clientInfo.getClientId(), clientDetails.getScope(), "custom");

        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(token));
    }

}
