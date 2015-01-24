package de.ersterstreber.countentities;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import de.ersterstreber.countentities.command.CECommand;
import de.ersterstreber.countentities.command.DelCommand;
import de.ersterstreber.countentities.listeners.AsyncPlayerChatListener;

public class Main extends JavaPlugin {

	private static Main instance;
	
	@Override
	public void onEnable() {
		getCommand("countentities").setExecutor(new CECommand());
		getCommand("deleteentities").setExecutor(new DelCommand());
		
		Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
		
		instance = this;
		
		getWorldGuard();
	}
	
	public static WorldGuardPlugin getWorldGuard() {
	    Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
	 
	    if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	        Bukkit.getPluginManager().disablePlugin(instance);
	        try {
				throw new IOException("WorldGuard wurde nicht gefunden!");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	 
	    return (WorldGuardPlugin) plugin;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
}
