package com.me.util;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.selector.Html;

import com.me.entity.Form;
import com.me.entity.Input;

public class FormUtil {

	public static Form packForm(String html) {
		String sForm = Html.create(html).$("form").toString();
		String action = Html.create(sForm).$("form", "action").toString();
		String method = Html.create(sForm).$("form", "method").toString();
		List<String> sInputs = Html.create(sForm).$("input").all();
		List<Input> inputs = new ArrayList<Input>();
		for (String sIn : sInputs) {
			inputs.add(packInput(sIn));
		}
		Form form = new Form();
		form.setAction(action);
		form.setMethod(method);
		form.setInputs(inputs);
		return form;
	}

	public static Input packInput(String html) {
		String name = Html.create(html).$("input", "name").toString();
		String value = Html.create(html).$("input", "value").toString();
		String type = Html.create(html).$("input", "type").toString();
		return new Input(name, value, type);
	}
}
