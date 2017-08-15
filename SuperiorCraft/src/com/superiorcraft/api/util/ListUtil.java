package com.superiorcraft.api.util;

import java.util.List;
import java.util.Random;

import com.superiorcraft.SuperiorCraft;

public class ListUtil {
	
	public static Object getRandomItem(List<?> list) {
		Random r = new Random();
		return list.get(r.nextInt(list.size()));
	}
	
}
