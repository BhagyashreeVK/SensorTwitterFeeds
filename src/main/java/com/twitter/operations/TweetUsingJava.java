package com.twitter.operations;


import java.io.IOException;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class TweetUsingJava implements TwitterActionsI {
	
	private AuthKeys authKeys;
	private OAuthConsumer oAuthConsumer;
	
	public TweetUsingJava(AuthKeys authKeys){
		this.authKeys = authKeys;
		oAuthConsumer = new CommonsHttpOAuthConsumer(authKeys.getConsumerKey(),
				authKeys.getConsumerSecretKey());
		oAuthConsumer.setTokenWithSecret(authKeys.getAccessTokenKey(), 
				authKeys.getAccessTokenSecretKey());
	}
	
	public void postToTimeline(String message) {
		
		String parsedMessage = parseMessage(message);
		HttpPost httpPost = new HttpPost(
				"https://api.twitter.com/1.1/statuses/update.json?status=" + parsedMessage);
		try {
			oAuthConsumer.sign(httpPost);
			
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse httpResponse = httpClient.execute(httpPost);

			int statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println(statusCode + ':'
					+ httpResponse.getStatusLine().getReasonPhrase());
			System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));
			
		} catch (OAuthMessageSignerException 
				| OAuthExpectationFailedException 
				| OAuthCommunicationException 
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
//to replace whitespaces with %20	
 public String parseMessage(String message){
		
		String parsedMessage = new String();
		int whiteSpaceLoc = 0;
		
		for(int i=0; i < message.length(); i++ ){
			if(message.charAt(i) == ' '){
				parsedMessage += message.substring(whiteSpaceLoc, i) + "%20";
				whiteSpaceLoc = i+1;
			}
		}
		parsedMessage += message.substring(whiteSpaceLoc, message.length());
		
		return parsedMessage;
	}

@Override
public void getTweetsFromTimeline(int numberOfTweets) {
	
	HttpGet httpGet = new HttpGet(
			"https://api.twitter.com/1.1/statuses/home_timeline.json?count=" + numberOfTweets);
	try {
		oAuthConsumer.sign(httpGet);
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse httpResponse = httpClient.execute(httpGet);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Statuscode: " + statusCode + "\n" +"Reason Phrase: "
				+ httpResponse.getStatusLine().getReasonPhrase());
		
		JSONArray arr = new JSONArray(IOUtils.toString(httpResponse.getEntity().getContent()));

		for (int i = 0; i < arr.length(); i++)
		{
		    String tweetText = arr.getJSONObject(i).getString("text");
		    System.out.println("Tweet "+ (i+1) + " : "+ tweetText);
		}
		
	} catch (OAuthMessageSignerException 
			| OAuthExpectationFailedException 
			| OAuthCommunicationException 
			| IOException e) {
		e.printStackTrace();
	}
	
}

@Override
public void searchTweets(String searchTerm, int numberOfTweets) {
	HttpGet httpGet = new HttpGet(
			"https://api.twitter.com/1.1/search/tweets.json?q="+searchTerm +"&count=" + numberOfTweets + "&lang=en");
	try {
		oAuthConsumer.sign(httpGet);
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse httpResponse = httpClient.execute(httpGet);
	
		
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Statuscode: " + statusCode + "\n" +"Reason Phrase: "
				+ httpResponse.getStatusLine().getReasonPhrase());
		
		JSONObject obj = new JSONObject(IOUtils.toString(httpResponse.getEntity().getContent()));
		JSONArray arr = obj.getJSONArray("statuses");

		for (int i = 0; i < arr.length(); i++)
		{
		    String tweetText = arr.getJSONObject(i).getString("text");
		    System.out.println("Tweet "+ (i+1) + " : "+ tweetText);
		}
		
	} catch (OAuthMessageSignerException 
			| OAuthExpectationFailedException 
			| OAuthCommunicationException 
			| IOException e) {
		e.printStackTrace();
	}
	
}

}
