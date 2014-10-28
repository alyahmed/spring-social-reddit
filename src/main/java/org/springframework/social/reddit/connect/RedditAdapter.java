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
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.reddit.api.Reddit;

/**
 *
 * @author ahmedaly
 */public class RedditAdapter implements ApiAdapter<Reddit> {

	public boolean test(Reddit reddit) {
		try {
			reddit.userOperations().getUserProfile();
			return true;
		} catch (ApiException e) {
			return false;
		}
	}


    @Override
    public void setConnectionValues(Reddit api, ConnectionValues values) {
    }

    @Override
    public UserProfile fetchUserProfile(Reddit api) {
        return new UserProfileBuilder().setUsername("boxsc2").setFirstName("Ahmed").build();
    }

    @Override
    public void updateStatus(Reddit api, String message) {
    }

}
