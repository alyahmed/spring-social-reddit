/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import org.springframework.social.reddit.api.Reddit;

/**
 *
 * @author ahmedaly
 */
public class RedditConnectionFactory extends OAuth2ConnectionFactory<Reddit>{

    public RedditConnectionFactory(String clientId, String clientSecret) {
        super("reddit", new RedditServiceProvider(clientId, clientSecret), new RedditAdapter());
    }
    
    
    
    
    
}
