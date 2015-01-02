package de.ersterstreber.countentities.tools;

import org.bukkit.entity.EntityType;

public class Language {
	
	public static String translate(EntityType type) {
		switch (type) {
		case CAVE_SPIDER:
			return "Höhlenspinne";
		case MAGMA_CUBE:
			return "Magma-Slime";
		case SILVERFISH:
			return "Silberfisch";
		case SKELETON:
			return "Skelett";
		case SPIDER:
			return "Spinne";
		case WITCH:
			return "Hexe";
		case ENDER_DRAGON:
			return "Enderdrache";
		case GIANT:
			return "Riese";
		case SNOWMAN:
			return "Schneemann";
		case SHEEP:
			return "Schaf";
		case PIG:
			return "Schwein";
		case BAT:
			return "Fledermaus";
		case CHICKEN:
			return "Hühnchen";
		case MUSHROOM_COW:
			return "Pilzkuh";
		case COW:
			return "Kuh";
		case SQUID:
			return "Tintenfisch";
		case HORSE:
			return "Pferd";
		case PIG_ZOMBIE:
			return "Zombie-Pigman";
		default:
			break;
		}
		String s = type.name();
		s = s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
		return s;
	}
	
}
