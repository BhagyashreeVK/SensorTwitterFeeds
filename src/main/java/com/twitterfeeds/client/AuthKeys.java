package com.twitterfeeds.client;


public class AuthKeys {
	
	public String consumerKey, consumerSecretKey, accessTokenKey, accessTokenSecretKey;
	
	public AuthKeys(String consumerKey, String consumerSecretKey, String accessTokenKey, String accessTokenSecretKey){
		this.consumerKey = consumerKey;
		this.consumerSecretKey = consumerSecretKey;
		this.accessTokenKey = accessTokenKey;
		this.accessTokenSecretKey = accessTokenSecretKey;
	}
		
	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecretKey() {
		return consumerSecretKey;
	}

	public void setConsumerSecretKey(String consumerSecretKey) {
		this.consumerSecretKey = consumerSecretKey;
	}

	public String getAccessTokenKey() {
		return accessTokenKey;
	}

	public void setAccessTokenKey(String accessTokenKey) {
		this.accessTokenKey = accessTokenKey;
	}

	public String getAccessTokenSecretKey() {
		return accessTokenSecretKey;
	}

	public void setAccessTokenSecretKey(String accessTokenSecretKey) {
		this.accessTokenSecretKey = accessTokenSecretKey;
	}

}
