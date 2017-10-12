package com.superiorcraft.api.util.ranks;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.superiorcraft.commands.CommandConstruct;

public class RankCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("rank")) {
			if (CommandConstruct.match(args, new String[] {""})) {
				
			}
			
			return true;
		}
		
		
		return false;
	}

}
