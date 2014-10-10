/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.reddit.api.Reddit;

/**
 *
 * @author ahmedaly
 */
public class RedditAdapter implements ApiAdapter<Reddit>{

    @Override
    public boolean test(Reddit reddit) {
        try{
            reddit.userOperations().getUserProfile();
            return true;
        }
        catch(ApiException e){
            return false;
        }
    }

    @Override
    public void setConnectionValues(Reddit api, ConnectionValues values) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserProfile fetchUserProfile(Reddit api) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateStatus(Reddit api, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
