package de.ersterstreber.countentities.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ersterstreber.countentities.tools.Entities;

public class CECommand implements CommandExecutor {

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				if (cmd.getName().equalsIgnoreCase("countentities")) {
					Entities.perform(p.getLocation().getChunk().getEntities(), p);
					return true;
				} else {
					p.sendMessage("§c/ce");
					return true;
				}
			} else {
				p.sendMessage("§c/ce");
				return true;
			}
		} else {
			sender.sendMessage("§cDu kannst diesen Befehl nur als Spieler ausführen!");
			return true;
		}
	}
	
}
