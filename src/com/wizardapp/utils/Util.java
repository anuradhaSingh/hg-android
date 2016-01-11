package com.wizardapp.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.wizardapp.model.Scholarship;

public class Util {

	static Gson gson = null;

	private static Gson getGsonInstance() {
		if (null == gson)
			gson = new Gson();
		return gson;
	}

	public static Object getResponse(InputStream source) {
		Reader reader = new InputStreamReader(source);
		return getGsonInstance().fromJson(reader, Scholarship.class);
	}

}
