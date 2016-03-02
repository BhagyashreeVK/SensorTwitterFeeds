package com.twitter.operations;


public interface TwitterActionsI {
	
	public void postToTimeline(String message);
	public void getTweetsFromTimeline(int numberOfTweets);
	public void searchTweets(String searchTerm, int numberOfTweets);

}
