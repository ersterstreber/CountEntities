package de.ersterstreber.countentities;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import de.ersterstreber.countentities.command.CECommand;
import de.ersterstreber.countentities.command.DelCommand;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getCommand("countentities").setExecutor(new CECommand());
		getCommand("deleteentities").setExecutor(new DelCommand());
		
		getWorldGuard();
	}
	
	public static WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    // WorldGuard may not be loaded
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        return null; // Maybe you want throw an exception instead
	    }
	 
	    return (WorldGuardPlugin) plugin;
	}
	
}
