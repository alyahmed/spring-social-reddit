/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.reddit.api.Reddit;
import org.springframework.social.reddit.api.impl.RedditPaths;
import org.springframework.social.reddit.api.impl.RedditTemplate;

/**
 *
 * @author ahmedaly
 */
public final class RedditServiceProvider extends AbstractOAuth2ServiceProvider<Reddit> {

    public RedditServiceProvider(String clientId, String clientSecret) {
        super(new OAuth2Template(clientId, clientSecret, 
                RedditPaths.OAUTH_AUTH_URL, RedditPaths.OAUTH_TOKEN_URL));
    }

    public Reddit getApi(String accessToken) {
        return new RedditTemplate(accessToken);
    }

}
