package com.liuyi.web.model;

public class SpiderWaitQueue {
	private String id;
	private String url;
	private String downpath;
	public String getDownpath() {
		return downpath;
	}

	public void setDownpath(String downpath) {
		this.downpath = downpath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(Integer isDownload) {
		this.isDownload = isDownload;
	}

	private Integer isDownload;
}
