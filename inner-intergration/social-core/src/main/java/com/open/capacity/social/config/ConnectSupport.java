/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.open.capacity.social.config;

import static java.util.Arrays.*;

import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.OAuth1ConnectionFactory;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuth1Version;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.NativeWebRequest;


@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectSupport {

	private boolean useAuthenticateUrl;

	private String applicationUrl;

	private String callbackUrl;

	/**
	 * Flag indicating if this instance will support OAuth-based authentication instead of the traditional user authorization.
	 * Some providers expose a special "authenticateUrl" the user should be redirected to as part of an OAuth-based authentication attempt.
	 * Setting this flag to true has {@link #buildOAuthUrl(ConnectionFactory, NativeWebRequest) oauthUrl} return this authenticate URL.
	 * @param useAuthenticateUrl whether to use the authenticat url or not
	 * @see OAuth1Operations#buildAuthenticateUrl(String, OAuth1Parameters)
	 * @see OAuth2Operations#buildAuthenticateUrl(OAuth2Parameters)
	 */
	public void setUseAuthenticateUrl(boolean useAuthenticateUrl) {
		this.useAuthenticateUrl = useAuthenticateUrl;
	}

	/**
	 * Configures the base secure URL for the application this controller is being used in e.g. <code>https://myapp.com</code>. Defaults to null.
	 * If specified, will be used to generate OAuth callback URLs.
	 * If not specified, OAuth callback URLs are generated from {@link HttpServletRequest HttpServletRequests}. 
	 * You may wish to set this property if requests into your application flow through a proxy to your application server.
	 * In this case, the HttpServletRequest URI may contain a scheme, host, and/or port value that points to an internal server not appropriate for an external callback URL.
	 * If you have this problem, you can set this property to the base external URL for your application and it will be used to construct the callback URL instead.
	 * @param applicationUrl the application URL value
	 */
	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
	
	/**
	 * Configures a specific callback URL that is to be used instead of calculating one based on the application URL or current request URL.
	 * When set this URL will override the default behavior where the callback URL is derived from the current request and/or a specified application URL.
	 * When set along with applicationUrl, the applicationUrl will be ignored.
	 * @param callbackUrl the callback URL to send to providers during authorization. Default is null. 
	 */
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	/**
	 * Builds the provider URL to redirect the user to for connection authorization.
	 * @param connectionFactory the service provider's connection factory e.g. FacebookConnectionFactory
	 * @param request the current web request
	 * @return the URL to redirect the user to for authorization
	 * @throws IllegalArgumentException if the connection factory is not OAuth1 based.
	 */
	public String buildOAuthUrl(ConnectionFactory<?> connectionFactory, NativeWebRequest request) {
		return buildOAuthUrl(connectionFactory, request, null);
	}
	
	/**
	 * Builds the provider URL to redirect the user to for connection authorization.
	 * @param connectionFactory the service provider's connection factory e.g. FacebookConnectionFactory
	 * @param request the current web request
	 * @param additionalParameters parameters to add to the authorization URL.
	 * @return the URL to redirect the user to for authorization
	 * @throws IllegalArgumentException if the connection factory is not OAuth1 based.
	 */
	public String buildOAuthUrl(ConnectionFactory<?> connectionFactory, NativeWebRequest request, MultiValueMap<String, String> additionalParameters) {
		if (connectionFactory instanceof OAuth2ConnectionFactory) {
			return buildOAuth2Url((OAuth2ConnectionFactory<?>) connectionFactory, request, additionalParameters);
		} else {
			throw new IllegalArgumentException("ConnectionFactory not supported");
		}		
	}


	/**
	 * Complete the connection to the OAuth2 provider.
	 * @param connectionFactory the service provider's connection factory e.g. FacebookConnectionFactory
	 * @param request the current web request
	 * @return a new connection to the service provider
	 */
	public Connection<?> completeConnection(OAuth2ConnectionFactory<?> connectionFactory, NativeWebRequest request) {
		String code = request.getParameter("code");
		try {
			AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, callbackUrl(request), null);
			return connectionFactory.createConnection(accessGrant);
		} catch (HttpClientErrorException e) {
			log.warn("HttpClientErrorException while completing connection: " + e.getMessage());
			log.warn("      Response body: " + e.getResponseBodyAsString());
			throw e;
		}
	}

	protected String callbackUrl(NativeWebRequest request) {
		if (callbackUrl != null) {
			return callbackUrl;
		}
		HttpServletRequest nativeRequest = request.getNativeRequest(HttpServletRequest.class);
		if (applicationUrl != null) {
			return applicationUrl + connectPath(nativeRequest);
		} else {
			return nativeRequest.getRequestURL().toString();
		}
	}



	private String buildOAuth2Url(OAuth2ConnectionFactory<?> connectionFactory, NativeWebRequest request, MultiValueMap<String, String> additionalParameters) {
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		String defaultScope = connectionFactory.getScope();
		OAuth2Parameters parameters = getOAuth2Parameters(request, defaultScope, additionalParameters);
		String state = connectionFactory.generateState();
		parameters.add("state", state);
		if (useAuthenticateUrl) { 
			return oauthOperations.buildAuthenticateUrl(parameters);
		} else {
			return oauthOperations.buildAuthorizeUrl(parameters);
		}
	}

	private OAuth2Parameters getOAuth2Parameters(NativeWebRequest request, String defaultScope, MultiValueMap<String, String> additionalParameters) {
		OAuth2Parameters parameters = new OAuth2Parameters(additionalParameters);
		parameters.putAll(getRequestParameters(request, "scope"));
		parameters.setRedirectUri(callbackUrl(request));
		String scope = request.getParameter("scope");
		if (scope != null) {
			parameters.setScope(scope);
		} else if (defaultScope != null) {
			parameters.setScope(defaultScope);
		}
		return parameters;
	}

	private String connectPath(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();
		return request.getServletPath() + (pathInfo != null ? pathInfo : "");
	}
	
	private MultiValueMap<String, String> getRequestParameters(NativeWebRequest request, String... ignoredParameters) {
		List<String> ignoredParameterList = asList(ignoredParameters);
		MultiValueMap<String, String> convertedMap = new LinkedMultiValueMap<String, String>();
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			if (!ignoredParameterList.contains(entry.getKey())) {
				convertedMap.put(entry.getKey(), asList(entry.getValue()));
			}
		}
		return convertedMap;
	}

}
