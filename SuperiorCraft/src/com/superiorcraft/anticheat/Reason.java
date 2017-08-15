package com.superiorcraft.anticheat;

public enum Reason {
	
	SPAMMING ("spamming", 0),
	FLYING ("flying", 1),
	SPEED_HACKING("speed hacking", 1),
	XRAY ("using xray hacks", 1),
	DDOS ("attempting to ddos", 10);
	
	private String rName;
	private int offenseLevel;
	
	Reason(String rName, int offenseLevel) {
		this.rName = rName;
		this.offenseLevel = offenseLevel;
	}
	
	String getReadableName() {
		return this.rName;
	}
	
	int getOffenseLevel() {
		return this.offenseLevel;
	}
	
}
