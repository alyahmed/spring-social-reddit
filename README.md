spring-social-reddit
====================

API Wrapper for [Reddit OAuth2 RESTful web service](http://www.reddit.com/dev/api) 
for [Spring Social](http://projects.spring.io/spring-social/).

Note: Although recommended, I have not used Springs RestTemplate for authentication. Reddit requires access_token requests include the clientId and clientSecret in a basic auth header. I don't believe this is possible with RestTemplate. 

Currently Supports authentication via Reddit using OAuth 2. I will update the api to support account account and subreddit settings via REST endpoints in the foreseeable future.


Known Bugs:
- Json parsing errors when using Jackson 1.x. Spring will automatically use the library configured in the main project. If you are using Jackson 1.x there may be errors when parsing The Reddit HTTP response.

