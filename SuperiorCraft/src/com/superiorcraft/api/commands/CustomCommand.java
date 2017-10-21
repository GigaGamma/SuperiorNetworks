package com.superiorcraft.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CustomCommand extends Command {

	private CommandExecutor executer = null;
	
	public CustomCommand(String name) {
		super(name);
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (executer != null) {
			executer.onCommand(sender, this, label, args);
		}
		
		return false;
	}
	
	public void setExecutor(CommandExecutor executer){
		this.executer = executer;
	}

}
