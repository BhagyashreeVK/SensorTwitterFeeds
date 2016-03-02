package com.rest.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.twitter.operations.AccessTwitter;
import com.twitter.operations.AuthKeys;
import com.twitter.operations.TweetUsingJava;

@Path("/getTweets")
public class GetTweets {
	
	 @GET
	 @Produces("application/json")
	 public Response getTweets() throws JSONException {
		 
		 AuthKeys authKeys = AccessTwitter.initializeAuthenticationKeys();
			TweetUsingJava tweetWithJava = new TweetUsingJava(authKeys);
			tweetWithJava.getTweetsFromTimeline(2);
			return Response.status(200).entity(tweetWithJava).build();
		  }
}
