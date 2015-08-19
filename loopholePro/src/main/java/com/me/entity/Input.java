package com.me.entity;

public class Input {

	private InputType type;

	private String name;

	private String value;

	public Input(String name, String value, InputType type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}

	public Input(String name, String value, String type) {
		this.name = name;
		this.value = value;
		this.type = InputType.getInputTypeByName(type);
	}

	public InputType getType() {
		return type;
	}

	public void setType(InputType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
