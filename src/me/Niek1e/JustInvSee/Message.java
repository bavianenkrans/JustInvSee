package me.Niek1e.JustInvSee;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Message {
	
//	JustInvSee, a plugin to enhance your server.
//	Copyright (C) 2016 Niek1e
//
//	This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License.
//
//	This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
//	
//	You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
//
//	You can contact Niek1e by email: 'prinscou@gmail.com'
	
	/**
	 * @author Niek1e
	 * @version 4.1
	 */
	
	

	public static String get(String message, JustInvSee main, Player player) {
		String msg = "";
		if(message == "lookedinyourinv")
			msg = ChatColor.GOLD + player.getName() + " " + ChatColor.WHITE + main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + "lookedinyourinv");
		else
			msg = ChatColor.RED + main.getConfig().getString("lang." + main.getConfig().getString("language") + "." + message);
		return msg;
	}
}