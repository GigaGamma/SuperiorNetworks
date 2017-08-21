package com.superiorcraft.api.util.json;

import java.util.List;

import com.google.gson.Gson;

public class JsonUtil {
	
	public static String toJson(Object obj) {
		return new Gson().toJson(obj);
	}
	
	public static Object fromJson(String str, Class<?> robject) {
		return new Gson().fromJson(str, robject);
	}
	
}
