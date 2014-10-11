/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.api.impl;

import org.springframework.social.reddit.api.RedditProfile;
import org.springframework.social.reddit.api.UserOperations;

/**
 *
 * @author ahmedaly
 */
public class UserTemplate extends AbstractRedditOperations implements UserOperations {

    public UserTemplate(boolean isUserAuthorized, boolean isAppAuthorized) {
        super(isUserAuthorized, isAppAuthorized);
    }
    
    
    @Override
    public RedditProfile getUserProfile() {
        return null;
    }
    
    
}
