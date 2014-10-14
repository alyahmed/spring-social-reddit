package org.springframework.social.reddit.api.impl;

import java.net.URI;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Base Operations class representing common functionality among all Operations
 * class implementations.
 *
 * @author ahmedaly
 */
public class AbstractRedditOperations {

    private final RestTemplate restTemplate;
    private boolean isAuthorized;

    private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();

    public AbstractRedditOperations(RestTemplate restTemplate, boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        this.restTemplate = restTemplate;
    }

    protected void requireAuthorization() {
        if (!isAuthorized) {
            throw new MissingAuthorizationException("google");
        }
    }

    protected <T> T getEntity(String url, Class<T> type) {
        return restTemplate.getForObject(url, type);
    }

    protected URI buildUri(String path) {
        return buildUri(path, EMPTY_PARAMETERS);
    }

    protected URI buildUri(String path, String parameterName, String parameterValue) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.set(parameterName, parameterValue);
        return buildUri(path, parameters);
    }

    protected URI buildUri(String path, MultiValueMap<String, String> parameters) {
        return URIBuilder.fromUri(RedditPaths.OAUTH_API_DOMAIN + path).queryParams(parameters).build();
    }
    
}
