package com.twitterfeeds.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

public class WebServiceClientMain {

	public static AuthKeys initializeAuthenticationKeys() {
		Properties properties = new Properties();
		String propertiesFile = "AuthenticationKeys.properties";
		InputStream inputStream = WebServiceClientMain.class.
				getClassLoader().getResourceAsStream(propertiesFile);
		AuthKeys authKeys = null;
		try {
			//load properties file
			properties.load(inputStream);
			// Your Twitter App's Consumer Key
			String consumerKey = properties.getProperty("consumerKey");
			// Your Twitter App's Consumer Secret
			String consumerSecretKey = properties.getProperty("consumerSecretKey");
			// Your Twitter Access Token
			String accessTokenKey = properties.getProperty("accessTokenKey");
			// Your Twitter Access Token Secret
			String accessTokenSecretKey = properties.getProperty("accessTokenSecretKey");
			authKeys = new AuthKeys(consumerKey, consumerSecretKey,
					accessTokenKey, accessTokenSecretKey);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return authKeys;
	}

	public static void main(String[] args) {
		AuthKeys authKeys = WebServiceClientMain.initializeAuthenticationKeys();
		TweetUsingJava tweetWithJava = new TweetUsingJava(authKeys);
		try {
			//Post tweet
			System.out.println("== Post Tweet Response ==");
			System.out.println(IOUtils.toString(tweetWithJava.postTweet("Hello world from server project", null).getEntity().getContent()));
			//Search tweets
			System.out.println("== Search Tweets Response ==");
			System.out.println(IOUtils.toString(tweetWithJava.searchTweets("Maryland", 5).getEntity().getContent()));
			//Get tweets from timeline
			System.out.println("== Get Tweets Response ==");
			System.out.println(IOUtils.toString(tweetWithJava.getTweets(10).getEntity().getContent()));
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
	}
}
