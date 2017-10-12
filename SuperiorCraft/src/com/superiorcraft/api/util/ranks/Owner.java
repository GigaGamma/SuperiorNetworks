package com.superiorcraft.api.util.ranks;

import com.superiorcraft.api.util.Rank;

public class Owner implements Rank {
	
	@Override
	public String getRankPath() {
		return "admin.owner";
	}
	
	@Override
	public String getRankName() {
		return "Owner";
	}

	@Override
	public String getPlayerName() {
		return "&4&n%playername%";
	}

	@Override
	public String getPlayerPrefix() {
		return "&n[%rankname%]";
	}

}
