package com.twitterfeeds.webservice.operations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.twitterfeeds.javamain.AccessTwitterMain;
import com.twitterfeeds.javamain.AuthKeys;
import com.twitterfeeds.javamain.TweetUsingJava;
import com.twitterfeeds.response.Response;

@Path("/")
public class TwitterWebService {
	
	 @GET
	 @Path("getTweets")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.APPLICATION_XML)
	 public Response getTweets(@QueryParam("count") Integer count){
		    AuthKeys authKeys = AccessTwitterMain.initializeAuthenticationKeys();
			TweetUsingJava tweetWithJava = new TweetUsingJava(authKeys);
			HttpResponse httpResponse = tweetWithJava.getTweets(count);
			Response response = new Response();
			if(httpResponse == null){
				response.setResponseReason("Error while processing request");
			} else {
			List<String> responseTweets = new ArrayList<String>();
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			response.setStatusCode(Integer.toString(statusCode));
			response.setResponseReason(httpResponse.getStatusLine().getReasonPhrase());
			if(statusCode == 200){
				JSONArray jsonArray;
				try {
					jsonArray = new JSONArray(IOUtils.toString(httpResponse.getEntity().getContent()));
					for(int i=0; i < jsonArray.length() ; i++){
						responseTweets.add(jsonArray.getJSONObject(i).getString("text"));
					}
					response.setResponseText(responseTweets);
				} catch (JSONException | UnsupportedOperationException
						| IOException e) {
					e.printStackTrace();
				}
			} 
		 }
			return response;
     }
	 
	 @GET
	 @Path("searchTweets")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.APPLICATION_XML)
	 public Response searchTweets(@QueryParam("query") String searchQuery, @QueryParam("count") Integer count){
		    AuthKeys authKeys = AccessTwitterMain.initializeAuthenticationKeys();
			TweetUsingJava tweetWithJava = new TweetUsingJava(authKeys);
			HttpResponse httpResponse = tweetWithJava.searchTweets(searchQuery, count);
			Response response = new Response();
			if(httpResponse == null){
				response.setResponseReason("Error while processing request");
			} else {
			List<String> responseTweets = new ArrayList<String>();
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			response.setStatusCode(Integer.toString(statusCode));
			response.setResponseReason(httpResponse.getStatusLine().getReasonPhrase());
			if(statusCode == 200){
				JSONObject jsonObject;
				JSONArray jsonArray;
				try {
					jsonObject = new JSONObject(IOUtils.toString(httpResponse.getEntity().getContent()));
					jsonArray = jsonObject.getJSONArray("statuses");
					for(int i=0; i < jsonArray.length() ; i++){
						responseTweets.add(jsonArray.getJSONObject(i).getString("text"));
					}
					response.setResponseText(responseTweets);
				} catch (JSONException | UnsupportedOperationException
						| IOException e) {
					e.printStackTrace();
				}
			}
		   }
			return response;
     }
	 
	 @GET
	 @Path("postTweet")
	 @Consumes(MediaType.TEXT_PLAIN)
	 public Response postTweet(@QueryParam("text") String textToPost, @QueryParam("media") String mediaURL){
		    AuthKeys authKeys = AccessTwitterMain.initializeAuthenticationKeys();
			TweetUsingJava tweetWithJava = new TweetUsingJava(authKeys);
			HttpResponse httpResponse = tweetWithJava.postTweet(textToPost,mediaURL);
			Response response = new Response();
			if(httpResponse == null){
				response.setResponseReason("Error while processing request");
			} else {
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				response.setStatusCode(Integer.toString(statusCode));
				response.setResponseReason(httpResponse.getStatusLine().getReasonPhrase());
			}
			return response;
     }
}
