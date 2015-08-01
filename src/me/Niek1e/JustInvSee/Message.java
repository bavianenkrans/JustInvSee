package me.Niek1e.JustInvSee;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {
	
//	Copyright (C) 2015 Niek1e
//	See JustInvSee.java for more information about the license
	
	/**
	 * @author Niek1e
	 * @version 3.0
	 */
	
	

	public static String get(String message, JustInvSee main, Player player) {
		String msg = "";
		if(message == "lookedinyourinv")
			msg = ChatColor.GOLD + player.getName() + ChatColor.WHITE + main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + "lookedinyourinv");
		else
			msg = ChatColor.RED + main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + message);
		return msg;
	}
}