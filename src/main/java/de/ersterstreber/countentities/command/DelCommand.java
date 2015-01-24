package de.ersterstreber.countentities.command;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.ersterstreber.countentities.Main;
import de.ersterstreber.countentities.listeners.AsyncPlayerChatListener;
import de.ersterstreber.countentities.tools.Entities;

public class DelCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			final Player p = (Player) sender;
			if (args.length == 2) {
				if (cmd.getName().equalsIgnoreCase("deleteentities")) {					
					int i = 0;
					try {
						i = Integer.parseInt(args[1]);
					} catch (NumberFormatException ex) {
						p.sendMessage("§c" + args[1] + " ist keine Zahl!");
						return true;
					}
					if (i == 0) {
						p.sendMessage("§7Du kannst nicht 0 Mobs löschen.");
						return true;
					}
					String s = args[0];
					EntityType type = Entities.fromString(s);
					p.sendMessage("§cAchtung!");
					String msg;
					if (i == 1) {
						msg = "§7Es wird §c" + i + " §7Mob unwiderruflich gelöscht!";
					} else {
						msg = "§7Es werden §c" + i + " §7Mobs unwiderruflich gelöscht!";
					}
					p.sendMessage(msg);
					p.sendMessage("§7Gebe §2Ja §7ein um fortzufahren und §cNein §7um den Vorgang abzubrechen.");
					p.sendMessage("§7Nach 15 Sekunden wird der Vorgang automatisch abgebrochen.");
					HashMap<EntityType, Integer> map = new HashMap<EntityType, Integer>();
					map.put(type, i);
					AsyncPlayerChatListener.confirm.put(p.getName(), map);
					new BukkitRunnable() {
						
						@Override
						public void run() {
							if (AsyncPlayerChatListener.confirm.containsKey(p.getName())) {
								//Nicht bestätigt
								p.sendMessage("§7Der Vorgang wurde abgebrochen.");
								AsyncPlayerChatListener.confirm.remove(p.getName());
							}
						}
					}.runTaskLater(Main.getInstance(), 15 * 20L);
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
