package com.me.entity;

import java.util.List;

public class WordObject {

	private String no;

	private String word;

	private List<String> keys;

	private String rightAns;

	private String yinbiao;

	private String def;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public String getRightAns() {
		return rightAns;
	}

	public void setRightAns(String rightAns) {
		this.rightAns = rightAns;
	}

	public String getYinbiao() {
		return yinbiao;
	}

	public void setYinbiao(String yinbiao) {
		this.yinbiao = yinbiao;
	}

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	@Override
	public String toString() {
		String str = "";
		if (keys != null) {
			for (String k : keys) {
				str += k;
				str += ",";
			}
		}
		return "WordObject [no=" + no + ", word=" + word + ", keys=" + str + ", rightAns=" + rightAns + ", yinbiao=" + yinbiao + ", def="
				+ def + "]";
	}

}
