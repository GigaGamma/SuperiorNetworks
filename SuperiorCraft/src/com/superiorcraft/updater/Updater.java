package com.superiorcraft.updater;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Updater {
	
	public JsonElement getVersionJson(URL url) {
		try {
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.connect();

			JsonParser jp = new JsonParser();
			return jp.parse(new InputStreamReader((InputStream) request.getContent()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
