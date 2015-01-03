package de.ersterstreber.countentities.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import de.ersterstreber.countentities.tools.Entities;

public class DelCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 2) {
				if (cmd.getName().equalsIgnoreCase("deleteentities")) {					
					int i = 0;
					try {
						i = Integer.parseInt(args[1]);
					} catch (NumberFormatException ex) {
						p.sendMessage("§c" + args[1] + " ist keine Zahl!");
						return true;
					}
					String s = args[0];
					EntityType type = Entities.fromString(s);
					Entities.deleteEntities(type, i, p, p.getLocation().getChunk());
					return true;
				} else {
					p.sendMessage("§c/delent <Entity> <Amount>");
					return true;
				}
			} else {
				p.sendMessage("§c/delent <Entity> <Amount>");
				return true;
			}
		} else {
			sender.sendMessage("§cDu kannst diesen Befehl nur als Spieler ausführen!");
			return true;
		}
	}
}
