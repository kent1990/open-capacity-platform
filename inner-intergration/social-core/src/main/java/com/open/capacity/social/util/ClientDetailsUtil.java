package com.open.capacity.social.util;

import io.netty.util.CharsetUtil;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;

@Component
public class ClientDetailsUtil {

    @Autowired
    private ClientDetailsService redisClientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClientDetails getClientDetails(String clientId, String clientSecret) throws UnapprovedClientAuthenticationException {
        ClientDetails clientDetails = redisClientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
        } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
        }
        return clientDetails;
    }

    private String[] extractAndDecodeHeader(String header) {
        if (header == null || !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中没有找到client信息！");
        }
        try {
            byte[] base64Token = header.substring(6).getBytes("UTF-8");
            byte[] decoded = Base64.decode(base64Token);
            String token = new String(decoded, CharsetUtil.UTF_8);
            int delim = token.indexOf(":");
            if (delim == -1) {
                throw new UnapprovedClientAuthenticationException("Invalid basic authentication token");
            }
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        } catch (Exception e) {
            throw new UnapprovedClientAuthenticationException(
                    "Failed to decode basic authentication token");
        }
    }

    public ClientInfo buildClientInfo(String header){
        String[] tokens = extractAndDecodeHeader(header);
        assert tokens.length == 2;
        return ClientInfo.builder()
                .clientId(tokens[0])
                .clientSecret(tokens[1])
                .build();
    }

    @Data
    @Builder
    public static class ClientInfo{

        private String clientId;

        private String clientSecret;

    }
}
