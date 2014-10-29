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
import org.springframework.social.reddit.api.RedditProfile;
import org.springframework.social.reddit.api.impl.RedditPaths;

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
    public void setConnectionValues(Reddit reddit, ConnectionValues values) {
        RedditProfile profile = reddit.userOperations().getUserProfile();
        values.setProfileUrl(
                new StringBuilder(RedditPaths.USER_BASE_URL).append(profile.getUsername()).toString());
        values.setProviderUserId(profile.getUsername());
        values.setDisplayName(profile.getUsername());
    }

    @Override
    public UserProfile fetchUserProfile(Reddit reddit) {
        RedditProfile profile = reddit.userOperations().getUserProfile();
        return new UserProfileBuilder()
                .setUsername(profile.getUsername())
                .setName(profile.getUsername())
                .build();
                                            
    }

    @Override
    public void updateStatus(Reddit reddit, String message) {
    }

}
