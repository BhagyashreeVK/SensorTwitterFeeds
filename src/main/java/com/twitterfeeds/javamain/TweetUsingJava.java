package com.twitterfeeds.javamain;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;

public class TweetUsingJava implements TwitterActionsI {
	
	private OAuthConsumer oAuthConsumer;
	
	public TweetUsingJava(AuthKeys authKeys){
		oAuthConsumer = new CommonsHttpOAuthConsumer(authKeys.getConsumerKey(),
				authKeys.getConsumerSecretKey());
		oAuthConsumer.setTokenWithSecret(authKeys.getAccessTokenKey(), 
				authKeys.getAccessTokenSecretKey());
	}
	
	public HttpResponse postTweet(String message, String imageLink) {
		String parsedMessage = parseMessage(message);
		StringBuilder request = new StringBuilder("https://api.twitter.com/1.1/statuses/update.json");
		HttpResponse httpResponse = null;
		MultipartEntityBuilder entity =  MultipartEntityBuilder.create();
		entity.addPart("status", new StringBody(parsedMessage, ContentType.TEXT_PLAIN));
		try {
		if(imageLink != null && imageLink.length() > 0){
			URL url = new URL(imageLink);
			//BufferedImage img = ImageIO.read(url.openStream());
			//String tDir = System.getProperty("java.io.tmpdir"); 
			//String path = tDir + "tmp" + ".jpg"; 
			//File file = new File(path);
			//ImageIO.write(img, "jpg", file);
			//file.deleteOnExit();
			File file = new File("/temp.jpg");
			FileUtils.copyURLToFile(url, file);
			entity.addPart("image", new FileBody(file));
			//entity.addPart("media_url", url.toString());
		}
		HttpPost httpPost = new HttpPost(new String(request));
		httpPost.setEntity(entity.build());
		System.out.println(httpPost.getMethod());
		HttpClient httpClient = HttpClientBuilder.create().build();
			oAuthConsumer.sign(httpPost);
			httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println(statusCode + ':'
					+ httpResponse.getStatusLine().getReasonPhrase());
			System.out.println(IOUtils.toString(httpResponse.getEntity().getContent()));
		} catch (OAuthMessageSignerException 
				| OAuthExpectationFailedException 
				| OAuthCommunicationException 
				| IOException e) {
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
