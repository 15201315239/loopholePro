package com.me.entity;

import java.util.List;

public class Form {

	public static final String METHOD_GET = "get";

	public static final String METHOD_POST = "post";

	private String action;

	private List<Input> inputs;

	private String method;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<Input> getInputs() {
		return inputs;
	}

	public void setInputs(List<Input> inputs) {
		this.inputs = inputs;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public static String getMethodGet() {
		return METHOD_GET;
	}

	public static String getMethodPost() {
		return METHOD_POST;
	}

}
