package com.twitter.operations;

import org.json.JSONArray;


public interface TwitterActionsI {
	
	public void postToTimeline(String message);
	public void getTweetsFromTimeline(int numberOfTweets);
	public JSONArray getTweets(int numberOfTweets);
	public void searchTweets(String searchTerm, int numberOfTweets);

}
