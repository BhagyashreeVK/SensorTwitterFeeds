package com.twitterfeeds.javamain;

import org.apache.http.HttpResponse;


public interface TwitterActionsI {
	public HttpResponse postTweet(String message, String imageLink);
	public HttpResponse getTweets(int numberOfTweets);
	public HttpResponse searchTweets(String searchTerm, int numberOfTweets);
}
