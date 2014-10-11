/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.connect;

import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.reddit.api.impl.RedditPaths;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author ahmedaly
 */
public class RedditOAuth2Template extends OAuth2Template {

    public RedditOAuth2Template(String clientId, String clientSecret) {
        super(clientId, clientSecret, RedditPaths.OAUTH_AUTH_URL, RedditPaths.OAUTH_TOKEN_URL);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
        ResponseEntity<Map> responseEntity = getRestTemplate().exchange(accessTokenUrl, HttpMethod.POST, requestEntity, Map.class);
        Map<String, Object> responseMap = responseEntity.getBody();
        return extractAccessGrant(responseMap);
    }

    private AccessGrant extractAccessGrant(Map<String, Object> result) {
        String accessToken = (String) result.get("access_token");
        String scope = (String) result.get("scope");
        String refreshToken = (String) result.get("refresh_token");

        // result.get("expires_in") may be an Integer, so cast it to Number first. 	
        Number expiresInNumber = (Number) result.get("expires_in");
        Long expiresIn = (expiresInNumber == null) ? null : expiresInNumber.longValue();

        return createAccessGrant(accessToken, scope, refreshToken, expiresIn, result);

    }

}
