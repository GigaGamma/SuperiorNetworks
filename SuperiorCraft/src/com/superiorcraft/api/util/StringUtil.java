package com.superiorcraft.api.util;

import java.util.HashMap;

public class StringUtil {
	
	public static String replaceAll(HashMap<String, String> replacements, String string) {
		String f = string;
		
		for (String s : replacements.keySet()) {
			f = f.replace(s, replacements.get(s));
		}
		
		return f;
	}
	
}
