package com.me.entity;

public enum InputType {
	HIDDEN("hidden"), RADIO("radio");
	private String name;

	InputType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static InputType getInputTypeByName(String name) {
		InputType[] values = InputType.values();
		for (InputType it : values) {
			if (it.getName().equals(name)) {
				return it;
			}
		}
		return null;
	}
}
