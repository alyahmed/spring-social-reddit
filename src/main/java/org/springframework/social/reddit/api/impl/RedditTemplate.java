/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.api.impl;

import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;
import org.springframework.social.reddit.api.MessageOperations;
import org.springframework.social.reddit.api.Reddit;
import org.springframework.social.reddit.api.SubredditOperations;
import org.springframework.social.reddit.api.ThreadOperations;
import org.springframework.social.reddit.api.UserOperations;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

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
public class RedditTemplate extends AbstractOAuth1ApiBinding implements Reddit {

    private MessageOperations messageOperations;
    private UserOperations userOperations;
    private SubredditOperations subredditOperations;
    private ThreadOperations threadOperations;

    private RestTemplate restTemplate = null;

    public RedditTemplate(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        initSubApis();
    }

    public RedditTemplate(String clientToken) {
        super();
        Assert.notNull(clientToken, "Constructor arguement 'client token' cannot be null");
        this.restTemplate = createRestTemplate(clientToken);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initSubApis() {

    }

    private RestTemplate createRestTemplate(String clientToken) {
        RestTemplate rt = new ClientAuthorizedRedditTemplate(clientToken).getRestTemplate();
        rt.setMessageConverters(getMessageConverters());
        configureRestTemplate(rt);
        return restTemplate;
    }

    private boolean isAuthorizedForApp() {
        return restTemplate != null;
    }
}
