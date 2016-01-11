package com.wizardapp.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;

import com.google.gson.Gson;
import com.wizardapp.model.Scholarship;

public class Util {

	static Gson gson = null;

	private static Gson getGsonInstance() {
		if (null == gson)
			gson = new Gson();
		return gson;
	}

	public static <T extends Object> Object getResponse(HttpEntity source,T className) throws Exception {
		/*ByteArrayOutputStream out = new ByteArrayOutputStream();
		source.writeTo(out);
        String responseString = out.toString();
        out.close();
		return getGsonInstance().fromJson(responseString, T.class);*/
		return null;
	}

}
