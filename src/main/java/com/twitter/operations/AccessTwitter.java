package com.twitter.operations;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AccessTwitter {

	public static AuthKeys initializeAuthenticationKeys() {
		Properties properties = new Properties();
		String propertiesFile = "AuthenticationKeys.properties";
		InputStream inputStream = AccessTwitter.class.
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

		AuthKeys authKeys = AccessTwitter.initializeAuthenticationKeys();
		TweetUsingJava tweetWithJava = new TweetUsingJava(authKeys);
		//tweetWithJava.postToTimeline("This is a third test tweet using Java");
		tweetWithJava.getTweetsFromTimeline(2);
		//tweetWithJava.searchTweets("Maryland", 5);
	}
}
