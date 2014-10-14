/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.api.impl;

import org.springframework.social.reddit.api.RedditProfile;
import org.springframework.social.reddit.api.UserOperations;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ahmedaly
 */
public class UserTemplate extends AbstractRedditOperations implements UserOperations {
    
    private static final String USER_KARMA = RedditPaths.OAUTH_API_DOMAIN + "/api/v1/me/karma"; //Requires mysubreddits scope
    private static final String USER_PREFERENCES = RedditPaths.OAUTH_API_DOMAIN + "/api/v1/me/prefs"; //Requires Account Scope
    
    public UserTemplate(RestTemplate restTemplate, boolean isAuthorized){
        super(restTemplate, isAuthorized);
    }
    
    @Override
    public RedditProfile getUserProfile() {
        return getEntity(USER_PREFERENCES, RedditProfile.class);
    }
    
    
    
}
