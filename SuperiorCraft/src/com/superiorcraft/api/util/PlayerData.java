package com.superiorcraft.api.util;

import java.io.Serializable;

public class PlayerData implements Serializable {

	private static final long serialVersionUID = 2142020039529917194L;
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
