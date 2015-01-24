package de.ersterstreber.countentities.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.ersterstreber.countentities.tools.Entities;

public class AsyncPlayerChatListener implements Listener {

	public static Map<String, HashMap<EntityType, Integer>> confirm = new HashMap<String, HashMap<EntityType, Integer>>();
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		if (confirm.containsKey(e.getPlayer().getName())) {
			if (e.getMessage().equalsIgnoreCase("ja")) {
				HashMap<EntityType, Integer> map = confirm.get(e.getPlayer().getName());
				if (map.size() != 1) {
					e.getPlayer().sendMessage("§cEs ist ein Fehler aufgetreten, bitte melde dich bei ersterstreber!");
					return;
				}
				for (Entry<EntityType, Integer> entry : map.entrySet()) {
					Entities.deleteEntities(entry.getKey(), entry.getValue(), e.getPlayer(), e.getPlayer().getLocation().getChunk());
				}
				confirm.remove(e.getPlayer().getName());
			} else if (e.getMessage().equalsIgnoreCase("nein")) {
				e.getPlayer().sendMessage("§7Der Vorgang wurde abgebrochen.");
				confirm.remove(e.getPlayer().getName());
			} else {
				e.getPlayer().sendMessage("§7Bitte schreibe erst 'Ja' oder 'Nein' in den Chat!");
			}
			e.setCancelled(true);
		}
	}
	
}
