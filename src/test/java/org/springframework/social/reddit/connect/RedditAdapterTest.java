/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.reddit.connect;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.reddit.api.Reddit;



/**
 *
 * @author ahmedaly
 */
public class RedditAdapterTest {
    
    private RedditAdapter apiAdapter = new RedditAdapter();
    
    private Reddit reddit = Mockito.mock(Reddit.class);
    
    @Test
    public void fetchProfile(){
        assertEquals(4, 4);
    }
    
}
