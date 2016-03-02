package com.twitterfeeds.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;

import com.twitter.operations.AccessTwitter;
import com.twitter.operations.AuthKeys;
import com.twitter.operations.TweetUsingJava;
import com.twitterfeeds.response.Tweet;

@Path("/getTweets")
public class GetTweets {
	
	 @GET
	 @Produces(MediaType.APPLICATION_XML)
	 public Tweet getTweets() throws JSONException {
		 
		 AuthKeys authKeys = AccessTwitter.initializeAuthenticationKeys();
			TweetUsingJava tweetWithJava = new TweetUsingJava(authKeys);
			JSONArray arr = tweetWithJava.getTweets(1);
			return new Tweet(arr.getJSONObject(0).getInt("id"),arr.getJSONObject(0).getString("text"));
		  }
}
