package com.me.entity;

import java.util.List;

public class Sheet {

	private String title;

	private List<String> heads;

	private List<List<String>> contents;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getHeads() {
		return heads;
	}

	public void setHeads(List<String> heads) {
		this.heads = heads;
	}

	public List<List<String>> getContents() {
		return contents;
	}

	public void setContents(List<List<String>> contents) {
		this.contents = contents;
	}

}
