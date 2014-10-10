package org.springframework.social.reddit.api;

import org.springframework.social.ApiBinding;

/**
 *
 * Reddit API Binding. Interface specifying a basic set of
 * operations for interacting with Reddit.
 * 
 * @author ahmedaly 
 */
public interface Reddit extends ApiBinding {

    /**
     *
     * @return the portion of the Reddit API containing message Operations.
     */
    MessageOperations messageOperations();

    /**
     *
     * @return the portion of the Reddit API containing subreddit Operations.
     */
    SubredditOperations subredditOperations();

    /**
     *
     * @return the portion of the Reddit API containing thread Operations.
     */
    ThreadOperations threadOperations();

    /**
     *
     * @return the portion of the Reddit API containing user Operations.
     */
    UserOperations userOperations();

}
