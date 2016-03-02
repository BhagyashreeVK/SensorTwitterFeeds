package com.twitterfeeds.response;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tweet")
public class Tweet {
	
	private int id;
	private String text;
	
	public Tweet(){
	}
	
    public Tweet(int id, String text){
    	this.id = id;
    	this.text = text;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	@XmlElement
	public void setText(String text) {
		this.text = text;
	}

}

