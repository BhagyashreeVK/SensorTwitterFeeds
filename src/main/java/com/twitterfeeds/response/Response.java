package com.twitterfeeds.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class Response {
	
	private List<String> responseText;
	private String responseStatusCode;
	private String responseReason;

	public List<String> getResponseText() {
		if (responseText == null) {
			responseText = new ArrayList<String>();
		}
		return responseText;
	}

	@XmlElementWrapper(name = "responseText")
	@XmlElement(name = "tweet")
	public void setResponseText(List<String> responseText) {
		this.responseText = responseText;
	}

	public String getResponseReason() {
		return responseReason;
	}

	@XmlElement(name = "reasonPhrase")
	public void setResponseReason(String responseReason) {
		this.responseReason = responseReason;
	}
	
	public String getStatusCode() {
		return responseStatusCode;
	}

	@XmlElement(name = "reasonCode")
	public void setStatusCode(String responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}
}

