/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONValue;
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

    private String getAccessToken(String code, String redirectUrl) throws UnsupportedEncodingException, IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            
            //Reddit Requires clientId and clientSecret be attached via basic auth
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("ssl.reddit.com", 443),
                    new UsernamePasswordCredentials(clientId,clientSecret));

            HttpPost httppost = new HttpPost(RedditPaths.OAUTH_TOKEN_URL);
            
            List<NameValuePair> nvps = new ArrayList<NameValuePair>(3);
            nvps.add(new BasicNameValuePair("code", code));
            nvps.add(new BasicNameValuePair("grant_type", "authorization_code"));
            nvps.add(new BasicNameValuePair("redirect_uri", redirectUrl));

            httppost.setEntity(new UrlEncodedFormEntity(nvps));
            httppost.addHeader("User-Agent", "a unique user agent");
            httppost.setHeader("Accept", "any;");

            HttpResponse request = httpclient.execute(httppost); 
            HttpEntity response = request.getEntity(); // Reddit response containing accessToken
            
            if (response != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getContent()));
                StringBuilder content = new StringBuilder();
                String line;
                while (null != (line = br.readLine())) {
                    content.append(line);
                }
                System.out.println(content.toString());
                Map json = (Map) JSONValue.parse(content.toString());
                if (json.containsKey("access_token")) {
                    return (String) (json.get("access_token"));
                }
            }
            EntityUtils.consume(response);
        } finally {
            httpclient.getConnectionManager().shutdown(); //cleanup
        }
        return null;
    }
    
    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        try {
            String accessToken = getAccessToken(authorizationCode, redirectUri);
            AccessGrant grant = new AccessGrant(accessToken);
            return grant;
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(RedditOAuth2Template.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
