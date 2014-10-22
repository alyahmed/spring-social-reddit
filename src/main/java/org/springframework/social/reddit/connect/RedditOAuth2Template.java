/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.connect;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOG = LogManager.getLogger(RedditOAuth2Template.class);

    private String clientId;
    private String clientSecret;

    public RedditOAuth2Template(String clientId, String clientSecret) {
        super(clientId, clientSecret, RedditPaths.OAUTH_AUTH_URL, RedditPaths.OAUTH_TOKEN_URL);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        HttpHeaders headers = createHeaders(clientId, clientSecret);
        headers.set(accessTokenUrl, accessTokenUrl);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
        LOG.info("requestEntity: " + requestEntity.toString());
        ResponseEntity<Map> responseEntity = getRestTemplate().exchange(accessTokenUrl, HttpMethod.POST, requestEntity, Map.class);
        LOG.info("response Entity: " + responseEntity);
        Map<String, Object> responseMap = responseEntity.getBody();
        LOG.info("response map: " + responseMap);
        return extractAccessGrant(responseMap);
    }

    /*
     Reddit requires client_id and client_secret be 
     placed via HTTP basic Auth when retrieving the access_token
     */
    private HttpHeaders createHeaders(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        HttpHeaders headers = new HttpHeaders();
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
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
