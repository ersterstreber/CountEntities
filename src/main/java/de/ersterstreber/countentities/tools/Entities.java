package de.ersterstreber.countentities.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Entities {

	private static EntityType[] aggressiveMobs = { EntityType.BLAZE,
			EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.GHAST,
			EntityType.MAGMA_CUBE, EntityType.SILVERFISH, EntityType.SKELETON,
			EntityType.SLIME, EntityType.SPIDER, EntityType.WITCH,
			EntityType.WITHER, EntityType.ZOMBIE, EntityType.ENDER_DRAGON, EntityType.GIANT };

	private static EntityType[] passiveMobs = { EntityType.VILLAGER,
			EntityType.SNOWMAN, EntityType.OCELOT, EntityType.SHEEP,
			EntityType.PIG, EntityType.BAT, EntityType.CHICKEN,
			EntityType.MUSHROOM_COW, EntityType.PIG, EntityType.COW,
			EntityType.SQUID, EntityType.HORSE };
	
	private static EntityType[] neutralMobs = { EntityType.ENDERMAN, EntityType.PIG_ZOMBIE, EntityType.WOLF };

	public static boolean isPlayer(Entity ent) {
		return ent instanceof Player;
	}

	public static boolean isAggressiveMob(Entity ent) {
		return Arrays.asList(aggressiveMobs).contains(ent.getType());
	}
	
	public static boolean isPassiveMob(Entity ent) {
		return Arrays.asList(passiveMobs).contains(ent.getType());
	}
	
	public static boolean isNeutralMob(Entity ent) {
		return Arrays.asList(neutralMobs).contains(ent.getType());
	}
	
	public static void perform(Entity[] entitites, Player p) {
		List<Entity> neutral = new ArrayList<Entity>();
		List<Entity> passive = new ArrayList<Entity>();
		List<Entity> aggressive = new ArrayList<Entity>();
		
		for (Entity ent : entitites) {
			if (isAggressiveMob(ent)) {
				aggressive.add(ent);
				continue;
			}
			if (isNeutralMob(ent)) {
				neutral.add(ent);
				continue;
			}
			if (isPassiveMob(ent)) {
				passive.add(ent);
				continue;
			}
		}
		p.sendMessage("§7§oMobs in deinem Chunk:");
		
		if (passive.size() != 0) {
			p.sendMessage("§aFreundlich§6 (" + passive.size() + ")§f:");
			Map<EntityType, Integer> mobs = new HashMap<EntityType, Integer>();
			for (Entity ent : passive) {
				if (mobs.containsKey(ent.getType())) {
					mobs.put(ent.getType(), mobs.get(ent.getType()) + 1);
				} else {
					mobs.put(ent.getType(), 1);
				}
			}
			for (Map.Entry<EntityType, Integer> entry : mobs.entrySet()) {
				p.sendMessage("  §f" + Language.translate(entry.getKey()) + ": §6" + entry.getValue());
			}
			//Clearen
			mobs = null;
		}
		
		if (neutral.size() != 0) {
			p.sendMessage("§7Neutral§6 (" + neutral.size() + ")§f:");
			Map<EntityType, Integer> mobs = new HashMap<EntityType, Integer>();
			for (Entity ent : neutral) {
				if (mobs.containsKey(ent.getType())) {
					mobs.put(ent.getType(), mobs.get(ent.getType()) + 1);
				} else {
					mobs.put(ent.getType(), 1);
				}
			}
			for (Map.Entry<EntityType, Integer> entry : mobs.entrySet()) {
				p.sendMessage("  §f" + Language.translate(entry.getKey()) + ": §6" + entry.getValue());
			}
			//Clearen
			mobs = null;
		}
		
		if (aggressive.size() != 0) {
			p.sendMessage("§cFeindlich§6 (" + aggressive.size() + ")§f:");
			Map<EntityType, Integer> mobs = new HashMap<EntityType, Integer>();
			for (Entity ent : aggressive) {
				if (mobs.containsKey(ent.getType())) {
					mobs.put(ent.getType(), mobs.get(ent.getType()) + 1);
				} else {
					mobs.put(ent.getType(), 1);
				}
			}
			for (Map.Entry<EntityType, Integer> entry : mobs.entrySet()) {
				p.sendMessage("  §f" + Language.translate(entry.getKey()) + ": §6" + entry.getValue());
			}
			//Clearen
			mobs = null;
		}
		if (neutral.size() == 0 && passive.size() == 0 && aggressive.size() == 0) {
			p.sendMessage("§cIn deinem Chunk sind keine Mobs.");
			return;
		}
		int i = neutral.size() + aggressive.size() + passive.size();
		String s;
		if (i <= 20) {
			s = "§2Gesamt: §2" + i;
		} else {
			s = "§cGesamt: §c" + i;
		}
		p.sendMessage(s);
		
		//Alles clearen
		s = null;
		neutral = null;
		passive = null;
		aggressive = null;
	}

}
