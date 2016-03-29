package me.Niek1e.JustInvSee;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;
import org.mcstats.Metrics.Graph;

import net.gravitydevelopment.updater.Updater;
import net.gravitydevelopment.updater.Updater.UpdateType;

public class JustInvSee extends JavaPlugin implements Listener {
	
	/**
	 * @author Niek1e
	 * @version 4.1
	 */

	public String prefix = "§f[§bJustInvSee§f] ";
	
	Inventory inv;
	
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

	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		Bukkit.getServer().getPluginManager().registerEvents(this, this);

		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
			this.getLogger().warning("Couldn't submit stats to Metrics!");
		}
		
		try {
		    Metrics metrics = new Metrics(this);

		    Graph weaponsUsedGraph = metrics.createGraph("TEST");

		    weaponsUsedGraph.addPlotter(new Metrics.Plotter("1") {

		            @Override
		            public int getValue() {
		                    return 4; // Number of players who used a diamond sword
		            }

		    });

		    weaponsUsedGraph.addPlotter(new Metrics.Plotter("2") {

		            @Override
		            public int getValue() {
		                    return 17;
		            }

		    });

		    metrics.start();
		} catch (IOException e) {
		    //FAILED
		}

		@SuppressWarnings("unused")
		Updater updater = new Updater(this, 66804, getFile(), UpdateType.DEFAULT, true);
	}

	@Override
	public void onDisable() {
		saveDefaultConfig();
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if(e.getInventory().getTitle() != "Armor - JustInvSee (C)"){
			return;
		}
		
		e.setCancelled(true);
		e.getWhoClicked().closeInventory();
	}


	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (!(sender instanceof Player)) {
			sender.sendMessage(prefix + Message.get("onlyingame", this, player));
		} else {

			if (label.equalsIgnoreCase("inv")) {
				if (args.length == 1) {
					if (player.hasPermission("justinvsee.inv")) {
						if (player.getServer().getPlayer(args[0]) != null) {
							Player tplayer = player.getServer().getPlayer(args[0]);
							Inventory tinv = tplayer.getInventory();
							player.openInventory(tinv);
							player.playEffect(tplayer.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
							if (tplayer.isOp()) {
								tplayer.sendMessage(prefix + Message.get("lookedinyourinv", this, player));
							}
						} else {
							player.sendMessage(prefix + Message.get("playernotonline", this, player));
						}
					} else {
						player.sendMessage(prefix + Message.get("needop", this, player));
					}
				} else {
					player.sendMessage(prefix + Message.get("usage", this, player));
				}
			} else if (label.equalsIgnoreCase("enderinv")) {
				if (args.length == 1) {
					if (player.hasPermission("justinvsee.enderinv")) {
						if (player.getServer().getPlayer(args[0]) != null) {
							Player tplayer = player.getServer().getPlayer(args[0]);
							Inventory tinv = tplayer.getEnderChest();
							player.openInventory(tinv);
							if (tplayer.isOp()) {
								tplayer.sendMessage(prefix + Message.get("lookedinyourinv", this, player));
							} else {
							}
						} else {
							player.sendMessage(prefix + Message.get("playernotonline", this, player));
						}
					} else {
						player.sendMessage(prefix + Message.get("needop", this, player));
					}
				} else {
					player.sendMessage(prefix + Message.get("enderusage", this, player));
				}
			} else if (label.equalsIgnoreCase("armorinv")){
				if (args.length == 1) {
					if (player.hasPermission("justinvsee.armorinv")) {
						if (player.getServer().getPlayer(args[0]) != null) {
							Player tplayer = player.getServer().getPlayer(args[0]);
							inv = Bukkit.getServer().createInventory(null, 9, "Armor - JustInvSee (C)");
							ItemStack overig = createGlass(ChatColor.RED + "[]");
							ItemStack helmet = tplayer.getEquipment().getHelmet();
							ItemStack chestplate = tplayer.getEquipment().getChestplate();
							ItemStack leggings = tplayer.getEquipment().getLeggings();
							ItemStack boots = tplayer.getEquipment().getBoots();
							ItemStack shield = tplayer.getEquipment().getItemInOffHand();
							inv.setItem(0, overig);
							inv.setItem(1, helmet);
							inv.setItem(2, chestplate);
							inv.setItem(3, leggings);
							inv.setItem(4, boots);
							inv.setItem(5, overig);
							inv.setItem(6, overig);
							inv.setItem(7, shield);
							inv.setItem(8, overig);
							player.openInventory(inv);
							if (tplayer.isOp()) {
								tplayer.sendMessage(prefix + Message.get("lookedinyourinv", this, player));
							} else {
							}
						} else {
							player.sendMessage(prefix + Message.get("playernotonline", this, player));
						}
					} else {
						player.sendMessage(prefix + Message.get("needop", this, player));
					}
				} else {
					player.sendMessage(prefix + Message.get("armorusage", this, player));
				}
			}
		}
		return false;
	}
	
	private ItemStack createGlass(String name) {
        ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        i.setItemMeta(im);
        return i;
    }
	
	
}