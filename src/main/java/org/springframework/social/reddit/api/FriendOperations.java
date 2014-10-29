package org.springframework.social.reddit.api;

import java.util.List;

/**
 *
 * @author ahmedaly
 */
public interface FriendOperations {
    
    public List<RedditProfile> getFriends();
}
