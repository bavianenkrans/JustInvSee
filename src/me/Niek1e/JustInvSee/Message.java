package me.Niek1e.JustInvSee;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {

	public static enum Messages {
		needop, onlyingame, usage, enderusage, playernotonline, lookedinyourinv;
	}

	public static String get(Messages message, JustInvSee main, Player player) {
		String msg = "";
		switch (message) {
		case needop:
			msg = ChatColor.RED + main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + "needop");
		case onlyingame:
			msg = main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + "onlyingame");
		case lookedinyourinv:
			msg = ChatColor.GOLD + player.getName()
					+ main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + "lookedinyourinv");
		case playernotonline:
			msg = ChatColor.RED + main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + "playernotonline");
		case usage:
			msg = ChatColor.GREEN + main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + "usage");
		case enderusage:
			msg = ChatColor.GREEN + main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + "enderusage");
		}

		return msg;
	}
}
