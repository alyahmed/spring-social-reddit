/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.connect;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.reddit.api.Reddit;
import org.springframework.social.reddit.api.RedditProfile;
import org.springframework.social.reddit.api.UserOperations;

/**
 *
 * @author ahmedaly
 */
public class RedditAdapterTest {

    private RedditAdapter apiAdapter = new RedditAdapter();
    Reddit reddit = Mockito.mock(Reddit.class);

    @Test
    public void fetchProfileShouldReturnARedditProfile() {
        UserOperations userOperations = Mockito.mock(UserOperations.class);
        Mockito.when(reddit.userOperations()).thenReturn(userOperations);
        
        
        RedditProfile profile = Mockito.mock(RedditProfile.class);
        Mockito.when(profile.getUsername()).thenReturn("RedditUser");

        Mockito.when(userOperations.getUserProfile()).thenReturn(profile);

        UserProfile userProfile = apiAdapter.fetchUserProfile(reddit);
        assertEquals("RedditUser", userProfile.getUsername());
        assertEquals("RedditUser", userProfile.getName());
    }

}
