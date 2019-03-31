package com.tiedros.project.entity;

public class WebLink extends Bookmark {
	
	private String url;
	private String host;
	
	public WebLink() {
		
	}

	public WebLink(String url, String host) {
		super();
		this.url = url;
		this.host = host;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "WebLink [url=" + url + ", host=" + host + "]";
	}
	
	

}
