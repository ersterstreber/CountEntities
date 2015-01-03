package de.ersterstreber.countentities.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_7_R4.ChatClickable;
import net.minecraft.server.v1_7_R4.ChatHoverable;
import net.minecraft.server.v1_7_R4.ChatMessage;
import net.minecraft.server.v1_7_R4.ChatModifier;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.EnumClickAction;
import net.minecraft.server.v1_7_R4.EnumHoverAction;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

import org.bukkit.Chunk;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import de.ersterstreber.countentities.Main;

public class Entities {

	private static EntityType[] aggressiveMobs = { EntityType.BLAZE,
			EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.GHAST,
			EntityType.MAGMA_CUBE, EntityType.SILVERFISH, EntityType.SKELETON,
			EntityType.SLIME, EntityType.SPIDER, EntityType.WITCH,
			EntityType.WITHER, EntityType.ZOMBIE, EntityType.ENDER_DRAGON,
			EntityType.GIANT };

	private static EntityType[] passiveMobs = { EntityType.VILLAGER,
			EntityType.SNOWMAN, EntityType.OCELOT, EntityType.SHEEP,
			EntityType.PIG, EntityType.BAT, EntityType.CHICKEN,
			EntityType.MUSHROOM_COW, EntityType.PIG, EntityType.COW,
			EntityType.SQUID, EntityType.HORSE };

	private static EntityType[] neutralMobs = { EntityType.ENDERMAN,
			EntityType.PIG_ZOMBIE, EntityType.WOLF };

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
				IChatBaseComponent chat = ChatSerializer.a("{\"text\": \"  §f" + Language.translate(entry.getKey()) + ": §6" + entry.getValue() + "\"}");
				ChatModifier modifier = new ChatModifier();
				modifier.setChatClickable(new ChatClickable(EnumClickAction.SUGGEST_COMMAND, "/delent " + Language.translate(entry.getKey()) + " " + 0));
				modifier.a(new ChatHoverable(EnumHoverAction.SHOW_TEXT, new ChatMessage("Diese Mobs löschen")));
				chat.setChatModifier(modifier);
				PacketPlayOutChat packet = new PacketPlayOutChat(chat);
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			}
			// Clearen
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
				IChatBaseComponent chat = ChatSerializer.a("{\"text\": \"  §f" + Language.translate(entry.getKey()) + ": §6" + entry.getValue() + "\"}");
				ChatModifier modifier = new ChatModifier();
				modifier.setChatClickable(new ChatClickable(EnumClickAction.SUGGEST_COMMAND, "/delent " + Language.translate(entry.getKey()) + " " + 0));
				modifier.a(new ChatHoverable(EnumHoverAction.SHOW_TEXT, new ChatMessage("Diese Mobs löschen")));
				chat.setChatModifier(modifier);
				PacketPlayOutChat packet = new PacketPlayOutChat(chat);
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			}
			// Clearen
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
				IChatBaseComponent chat = ChatSerializer.a("{\"text\": \"  §f" + Language.translate(entry.getKey()) + ": §6" + entry.getValue() + "\"}");
				ChatModifier modifier = new ChatModifier();
				modifier.setChatClickable(new ChatClickable(EnumClickAction.SUGGEST_COMMAND, "/delent " + Language.translate(entry.getKey()) + " " + 0));
				modifier.a(new ChatHoverable(EnumHoverAction.SHOW_TEXT, new ChatMessage("Diese Mobs löschen")));
				chat.setChatModifier(modifier);
				PacketPlayOutChat packet = new PacketPlayOutChat(chat);
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			}
			// Clearen
			mobs = null;
		}
		if (neutral.size() == 0 && passive.size() == 0
				&& aggressive.size() == 0) {
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

		// Alles clearen
		s = null;
		neutral = null;
		passive = null;
		aggressive = null;
	}
	
	public static void deleteEntities(EntityType type, int amount, Player p, Chunk c) {
		RegionManager manager = Main.getWorldGuard().getRegionManager(c.getWorld());
		ApplicableRegionSet regions = manager.getApplicableRegions(p.getLocation());
		
		ProtectedRegion region = manager.getRegion("__global__");
		if (region != null && regions.size() == 0) {
			if (!region.isOwner(Main.getWorldGuard().wrapPlayer(p))) {
				p.sendMessage("§cDieser Chunk gehört nicht dir!");
				return;
			}
		} else if (regions.size() == 0) {
			p.sendMessage("§cHier ist keine Region, die dir gehören könnte!");
			return;
		}
		
		for (ProtectedRegion rg : regions) {
			if (!rg.isOwner(Main.getWorldGuard().wrapPlayer(p))) {
				p.sendMessage("§cDieser Chunk gehört nicht dir!");
				return;
			}
		}
		p.sendMessage("§7Entfernvorgang gestartet...");
		int removed = 0;
		for (Entity ent : c.getEntities()) {
			if (amount <= 0) break;
			if (ent.getType() == type) {
				ent.remove();
				amount--;
				removed++;
			}
		}
		p.sendMessage("§7Abgeschlossen! Es wurde(n) " + removed + " Mobs entfernt.");
		
		//Clearen
		removed = 0;
		regions = null;
		manager = null;
		return;
	}
	
	@SuppressWarnings("deprecation")
	public static EntityType fromString(String s) {
		EntityType type = EntityType.fromName(s);
		if (type == null) {
			switch (s) {
			case "Höhlenspinne":
				return EntityType.CAVE_SPIDER;
			case "Magma-Slime":
				return EntityType.MAGMA_CUBE;
			case "Silberfisch":
				return EntityType.SILVERFISH;
			case "Skelett":
				return EntityType.SKELETON;
			case "Spinne":
				return EntityType.SPIDER;
			case "Hexe":
				return EntityType.WITCH;
			case "Enderdrache":
				return EntityType.ENDER_DRAGON;
			case "Riese":
				return EntityType.GIANT;
			case "Schneemann":
				return EntityType.SNOWMAN;
			case "Schaf":
				return EntityType.SHEEP;
			case "Schwein":
				return EntityType.PIG;
			case "Fledermaus":
				return EntityType.BAT;
			case "Hühnchen":
				return EntityType.CHICKEN;
			case "Pilzkuh":
				return EntityType.MUSHROOM_COW;
			case "Kuh":
				return EntityType.COW;
			case "Tintenfisch":
				return EntityType.SQUID;
			case "Pferd":
				return EntityType.HORSE;
			case "Zombie-Pigman":
				return EntityType.PIG_ZOMBIE;
			default:
				break;
			}
		}
		return type;
	}

}
