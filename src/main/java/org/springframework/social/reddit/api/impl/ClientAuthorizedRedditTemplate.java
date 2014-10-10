/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.api.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

/**
 *
 * @author ahmedaly
 */
class ClientAuthorizedRedditTemplate extends AbstractOAuth2ApiBinding{

    public ClientAuthorizedRedditTemplate(String clientToken) {
        super(clientToken);
    }
    
}
