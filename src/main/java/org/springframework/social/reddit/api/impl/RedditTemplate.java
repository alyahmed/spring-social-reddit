/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.api.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.reddit.api.MessageOperations;
import org.springframework.social.reddit.api.Reddit;
import org.springframework.social.reddit.api.SubredditOperations;
import org.springframework.social.reddit.api.ThreadOperations;
import org.springframework.social.reddit.api.UserOperations;

/**
 * Implementation of Reddit API Binding interface.
 *
 * <p>
 * The Reddit REST Api requires authentication via OAuth2 to access most
 * endpoint resources. Upon authentication, this is the implementation for
 * accessing the RESTful Reddit API.
 * </p>
 *
 * @author ahmedaly
 */
public class RedditTemplate extends AbstractOAuth2ApiBinding implements Reddit {

    private String accessToken;

    private MessageOperations messageOperations;
    private UserOperations userOperations;
    private SubredditOperations subredditOperations;
    private ThreadOperations threadOperations;

    public RedditTemplate() {
        setUp();
    }

    public RedditTemplate(String accessToken) {
        super(accessToken);
        this.accessToken = accessToken;
        setUp();
    }

    private void setUp() {
        this.userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
    }

    @Override
    public MessageOperations messageOperations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubredditOperations subredditOperations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ThreadOperations threadOperations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserOperations userOperations() {
        return userOperations;
    }

}
