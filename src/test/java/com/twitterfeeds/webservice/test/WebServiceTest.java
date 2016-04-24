package com.twitterfeeds.webservice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.http.HttpResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.twitterfeeds.client.AuthKeys;
import com.twitterfeeds.client.TweetUsingJava;
import com.twitterfeeds.client.WebServiceClientMain;

public class WebServiceTest {

	AuthKeys authKeys;
	TweetUsingJava tweetWithJava;
	HttpResponse httpResponse = null;
	int statusCode;
	String reasonPhrase = null;
	@Before
	public void setup() {
		authKeys = WebServiceClientMain.initializeAuthenticationKeys();
		tweetWithJava = new TweetUsingJava(authKeys);
	}
	
	@Ignore
	@Test
	public void test001a_postTweet() {
		httpResponse = tweetWithJava.postTweet("Hello Twitter 3 from server project", null,null);
		if(httpResponse != null){
				statusCode = httpResponse.getStatusLine().getStatusCode();
				reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
			} 
		assertNotNull(httpResponse);
		assertEquals(200,statusCode);
		assertEquals("OK",reasonPhrase);
	}
	
	
	@Test
	public void test001b_postTweet() {
		httpResponse = tweetWithJava.postTweet("Hello Twitter 3 from server project", null,"index.png");
		if(httpResponse != null){
				statusCode = httpResponse.getStatusLine().getStatusCode();
				reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
			} 
		assertNotNull(httpResponse);
		assertEquals(200,statusCode);
		assertEquals("OK",reasonPhrase);
	}
	
	@Test
	public void test002_searchTweet() {
		httpResponse = tweetWithJava.searchTweets("Maryland", 5);
		if(httpResponse != null){
				statusCode = httpResponse.getStatusLine().getStatusCode();
				reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
			} 
		assertNotNull(httpResponse);
		assertEquals(200,statusCode);
		assertEquals("OK",reasonPhrase);
	}
	
	@Test
	public void test003_getTweets() {
		httpResponse = tweetWithJava.getTweets(3);
		if(httpResponse != null){
				statusCode = httpResponse.getStatusLine().getStatusCode();
				reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
			} 
		assertNotNull(httpResponse);
		assertEquals(200,statusCode);
		assertEquals("OK",reasonPhrase);
	}
}
