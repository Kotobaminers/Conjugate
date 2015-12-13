package org.kotobaminers.kmconjugation;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Util {
	
	private final static String PREFIX = ChatColor.AQUA + "["
			+ ChatColor.YELLOW + "Conjugation Game" + ChatColor.AQUA + "] "
			+ ChatColor.WHITE;
	
	public static void usage(Player player) {
		send(player,
				"The following commands are available:\n"
						+ "/conjugate past - Choose past tense conjugation \n"
						+ "/conjugate potential - Choose potential form conjugation \n");
	}
	
	public static void send(Player player, String message) {
		if (player != null) {
			player.sendMessage(PREFIX + message);
		}
	}

}
