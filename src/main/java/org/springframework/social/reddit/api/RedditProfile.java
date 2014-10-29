package org.springframework.social.reddit.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author ahmedaly
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditProfile {
    
    @JsonProperty(value = "name", required = true)
    private String username;
    
    @JsonProperty("link_karma")
    private Long linkKarma;
    
    @JsonProperty("comment_karma")
    private Long commentkarma;

    @JsonProperty("gold_credits")
    private Long redditGold;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public Long getLinkKarma() {
        return linkKarma;
    }

    private void setLinkKarma(Long linkKarma) {
        this.linkKarma = linkKarma;
    }

    public Long getCommentkarma() {
        return commentkarma;
    }

    private void setCommentkarma(Long commentkarma) {
        this.commentkarma = commentkarma;
    }

    public Long getRedditGold() {
        return redditGold;
    }

    private void setRedditGold(Long redditGold) {
        this.redditGold = redditGold;
    }
    
    
    
    
    
    
}
