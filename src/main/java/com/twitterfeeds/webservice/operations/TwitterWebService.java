package com.twitterfeeds.webservice.operations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.twitterfeeds.client.AuthKeys;
import com.twitterfeeds.client.TweetUsingJava;
import com.twitterfeeds.client.WebServiceClientMain;
import com.twitterfeeds.response.Response;

@Path("/")
public class TwitterWebService {
	
	 @GET
	 @Path("getTweets")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.APPLICATION_XML)
	 public Response getTweets(@QueryParam("count") Integer count, 
			 @QueryParam("consumerKey") String consumerKey,
			 @QueryParam("consumerSecretKey") String consumerSecretKey,
			 @QueryParam("accessTokenKey") String accessTokenKey,
			 @QueryParam("accessTokenSecretKey") String accessTokenSecretKey){
			 AuthKeys authKeys = null;
			 Response response = new Response();
			 if(consumerKey != null && consumerSecretKey != null && accessTokenKey != null && accessTokenSecretKey != null){
				 authKeys = new AuthKeys(consumerKey, consumerSecretKey, accessTokenKey, accessTokenSecretKey);
			 } else{
				 // for time being, if not passed through the query params, intialize automatically
			     authKeys = WebServiceClientMain.initializeAuthenticationKeys();
				 //response.setResponseReason("Authentication Error");
				 //return response;
			 }
			TweetUsingJava tweetWithJava = new TweetUsingJava(authKeys);
			HttpResponse httpResponse = tweetWithJava.getTweets(count);
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
		    AuthKeys authKeys = WebServiceClientMain.initializeAuthenticationKeys();
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
		    AuthKeys authKeys = WebServiceClientMain.initializeAuthenticationKeys();
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
