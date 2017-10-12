package com.superiorcraft.api.util.ranks;
import com.superiorcraft.api.util.Rank;

public enum RankAdapter {
	
	OWNER (new Owner());
	
	private final Rank rank;
	
	private RankAdapter (Rank rank) {
		this.rank = rank;
	}
	
	public Rank rank() {return rank;}
	
}
