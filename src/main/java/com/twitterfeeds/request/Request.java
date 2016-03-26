package com.twitterfeeds.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request")
public class Request {

	private int numberOfResults;
	private String searchTerm;
	private String messageToPost;
	private String imageLink;
	
	
	public int getNumberOfResults() {
		return numberOfResults;
	}
	
	@XmlElement
	public void setNumberOfResults(int numberOfResults) {
		this.numberOfResults = numberOfResults;
	}
	
	public String getSearchTerm() {
		return searchTerm;
	}
	
	@XmlElement
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	public String getMessageToPost() {
		return messageToPost;
	}
	
	@XmlElement
	public void setMessageToPost(String messageToPost) {
		this.messageToPost = messageToPost;
	}

	public String getImageLink() {
		return imageLink;
	}

	@XmlElement
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
}
