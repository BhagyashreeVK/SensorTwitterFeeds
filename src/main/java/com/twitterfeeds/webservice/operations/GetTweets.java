package com.twitterfeeds.webservice.operations;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;

import com.twitterfeeds.javamain.AccessTwitterMain;
import com.twitterfeeds.javamain.AuthKeys;
import com.twitterfeeds.javamain.TweetUsingJava;
import com.twitterfeeds.response.Tweet;

@Path("/getTweets")
public class GetTweets {
	
	 @GET
	 @Produces(MediaType.APPLICATION_XML)
	 public Tweet getTweets() throws JSONException {
		 
		 AuthKeys authKeys = AccessTwitterMain.initializeAuthenticationKeys();
			TweetUsingJava tweetWithJava = new TweetUsingJava(authKeys);
			JSONArray arr = tweetWithJava.getTweets(1);
			return new Tweet(arr.getJSONObject(0).getInt("id"),arr.getJSONObject(0).getString("text"));
		  }
}
