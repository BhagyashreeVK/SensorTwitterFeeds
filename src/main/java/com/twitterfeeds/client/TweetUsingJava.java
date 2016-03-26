package com.twitterfeeds.client;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

public class TweetUsingJava implements TwitterActionsI {
	
	private OAuthConsumer oAuthConsumer;
	
	public TweetUsingJava(AuthKeys authKeys){
		oAuthConsumer = new CommonsHttpOAuthConsumer(authKeys.getConsumerKey(),
				authKeys.getConsumerSecretKey());
		oAuthConsumer.setTokenWithSecret(authKeys.getAccessTokenKey(), 
				authKeys.getAccessTokenSecretKey());
	}
	
	public HttpResponse postTweet(String message, String mediaLink) {
		String parsedMessage = parseMessage(message);
		String media_id = new String();
		StringBuilder request = new StringBuilder("https://api.twitter.com/1.1/statuses/update.json?status="+parsedMessage);
		HttpResponse httpResponse = null;
		HttpPost httpPost = null;
		HttpClient httpClient = HttpClientBuilder.create().build();
		MultipartEntityBuilder entity =  MultipartEntityBuilder.create();
		try {
		//if posting media, first get media_id using upload media endpoint and then pass on the media_id update endpoint
		if(mediaLink != null && mediaLink.length() > 0){
			URL url = new URL(mediaLink);
			File file = new File("/temp." + mediaLink.substring(mediaLink.length()-3));
			FileUtils.copyURLToFile(url, file);
			StringBuilder mediaUploadRequest = new StringBuilder("https://upload.twitter.com/1.1/media/upload.json");
			entity.addPart("media",new FileBody(file));
			httpPost = new HttpPost(new String(mediaUploadRequest));
			oAuthConsumer.sign(httpPost);
			httpPost.setEntity(entity.build());
			httpResponse = httpClient.execute(httpPost);
			JSONObject obj = new JSONObject(IOUtils.toString(httpResponse.getEntity().getContent()));
			media_id = obj.getString("media_id_string");
			request.append("&media_ids=").append(media_id);
		}
		httpPost = new HttpPost(new String(request));
		System.out.println(httpPost.getMethod());
			oAuthConsumer.sign(httpPost);
			httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println(statusCode + ':'
					+ httpResponse.getStatusLine().getReasonPhrase());
			System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));
		} catch (OAuthMessageSignerException 
				| OAuthExpectationFailedException 
				| OAuthCommunicationException 
				| IOException 
				| JSONException e) {
			e.printStackTrace();
		}
		return httpResponse;
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
public HttpResponse searchTweets(String searchTerm, int numberOfTweets) {
	String parsedMessage = parseMessage(searchTerm);
	HttpGet httpGet = new HttpGet(
			"https://api.twitter.com/1.1/search/tweets.json?q="+ parsedMessage +"&count=" + numberOfTweets + "&lang=en");
	HttpClient httpClient = HttpClientBuilder.create().build();
	HttpResponse httpResponse = null;
	try {
		oAuthConsumer.sign(httpGet);
		httpResponse = httpClient.execute(httpGet);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Statuscode: " + statusCode + "\n" +"Reason Phrase: "
				+ httpResponse.getStatusLine().getReasonPhrase());
		
	} catch (OAuthMessageSignerException 
			| OAuthExpectationFailedException 
			| OAuthCommunicationException 
			| IOException e) {
		e.printStackTrace();
	}
	return httpResponse;
}

@Override
public HttpResponse getTweets(int numberOfTweets) {
	HttpGet httpGet = new HttpGet(
			"https://api.twitter.com/1.1/statuses/home_timeline.json?count=" + numberOfTweets);
	HttpResponse httpResponse = null;
	HttpClient httpClient = HttpClientBuilder.create().build();
	try {
		oAuthConsumer.sign(httpGet);
		httpResponse = httpClient.execute(httpGet);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		System.out.println("Statuscode: " + statusCode + "\n" +"Reason Phrase: "
				+ httpResponse.getStatusLine().getReasonPhrase());
	} catch (OAuthMessageSignerException 
			| OAuthExpectationFailedException 
			| OAuthCommunicationException 
			| IOException e) {
		e.printStackTrace();
	}
	return httpResponse;
}
}
