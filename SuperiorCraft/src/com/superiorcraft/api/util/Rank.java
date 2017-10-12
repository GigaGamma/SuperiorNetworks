package com.superiorcraft.api.util;

import java.io.Serializable;

/**
 * Use %rankname% and %playername%
 * @author augus
 *
 */
public interface Rank extends Serializable {
	
	public String getRankPath();
	public String getRankName();
	public String getPlayerName();
	public String getPlayerPrefix();
	
}
