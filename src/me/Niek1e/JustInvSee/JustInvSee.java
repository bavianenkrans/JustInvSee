package me.Niek1e.JustInvSee;

import java.io.IOException;

import me.Niek1e.JustInvSee.Message.Messages;

import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class JustInvSee extends JavaPlugin {

	public String prefix = "§f[§bJustInvSee§f] ";

	@Override
	public void onEnable() {
		saveDefaultConfig();

		try {
			MetricsLite metrics = new MetricsLite(this);
			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
			this.getLogger().warning("Couldn't submit stats to Metrics!");
		}

	}

	@Override
	public void onDisable() {
		saveDefaultConfig();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (!(sender instanceof Player)) {
			sender.sendMessage(prefix + Message.get(Messages.onlyingame, this, player));
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
								tplayer.sendMessage(prefix + Message.get(Messages.lookedinyourinv, this, player));
							}
						} else {
							player.sendMessage(prefix + Message.get(Messages.playernotonline, this, player));
						}
					} else {
						player.sendMessage(prefix + Message.get(Messages.needop, this, player));
					}
				} else {
					player.sendMessage(prefix + Message.get(Messages.usage, this, player));
				}
			} else if (label.equalsIgnoreCase("enderinv")) {
				if (args.length == 1) {
					if (player.hasPermission("justinvsee.enderinv")) {
						if (player.getServer().getPlayer(args[0]) != null) {
							Player tplayer = player.getServer().getPlayer(args[0]);
							Inventory tinv = tplayer.getEnderChest();
							player.openInventory(tinv);
							if (tplayer.isOp()) {
								tplayer.sendMessage(prefix + Message.get(Messages.lookedinyourinv, this, player));
							} else {
							}
						} else {
							player.sendMessage(prefix + Message.get(Messages.playernotonline, this, player));
						}
					} else {
						player.sendMessage(prefix + Message.get(Messages.needop, this, player));
					}
				} else {
					player.sendMessage(prefix + Message.get(Messages.enderusage, this, player));
				}
			}
		}
		return false;
	}
}
