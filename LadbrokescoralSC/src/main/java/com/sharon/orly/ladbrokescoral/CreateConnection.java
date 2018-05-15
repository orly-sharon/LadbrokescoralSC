package com.sharon.orly.ladbrokescoral;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CreateConnection {

	public CreateConnection() {
	};

	public String BuiledUrl(String bandName) throws IOException {
		String clientId = "pCNN85KHlpoe5K6ZlysWZBEgLJRcftOd";
		String urlString = "http://api.soundcloud.com/tracks/?";

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("q", bandName);
		parameters.put("client_id", clientId);
		urlString = urlString.concat(getParamsString(parameters));
		return urlString;
	}

	public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}
		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}

}
