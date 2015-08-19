package com.me.util;

public class HtmlUtil {

	public static String getContent(String table) {
		if (table == null) {
			return null;
		}
		int start = table.indexOf(">");
		int end = table.lastIndexOf("<");
		if (start < 0) {
			start = 0;
		}
		if (end < start) {
			return table.substring(start);
		} else {
			return table.substring(start + 1, end);
		}
	}
}
