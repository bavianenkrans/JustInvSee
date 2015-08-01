package me.Niek1e.JustInvSee;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {

	public static String get(String message, JustInvSee main, Player player) {
		String msg = "";
		if(message == "lookedinyourinv")
			msg = ChatColor.GOLD + player.getName() + ChatColor.WHITE + main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + "lookedinyourinv");
		else
			msg = ChatColor.RED + main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + message);
		return msg;
	}
}