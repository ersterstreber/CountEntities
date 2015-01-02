package de.ersterstreber.countentities;

import org.bukkit.plugin.java.JavaPlugin;

import de.ersterstreber.countentities.command.CECommand;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getCommand("countentities").setExecutor(new CECommand());
	}
	
}
