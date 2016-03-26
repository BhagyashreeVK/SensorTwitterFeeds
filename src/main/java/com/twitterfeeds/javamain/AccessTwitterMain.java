package com.twitterfeeds.javamain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

public class AccessTwitterMain {

	public static AuthKeys initializeAuthenticationKeys() {
		Properties properties = new Properties();
		String propertiesFile = "AuthenticationKeys.properties";
		InputStream inputStream = AccessTwitterMain.class.
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
		AuthKeys authKeys = AccessTwitterMain.initializeAuthenticationKeys();
		TweetUsingJava tweetWithJava = new TweetUsingJava(authKeys);
		try {
			System.out.println(IOUtils.toString(tweetWithJava.postTweet("Hello", null).getEntity().getContent()));
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
	}
}
