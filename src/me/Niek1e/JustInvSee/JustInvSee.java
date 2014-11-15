package me.Niek1e.JustInvSee;

import java.io.IOException;

import org.bukkit.ChatColor;
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
    	}
    	
    }
    
    @Override
    public void onDisable() {
    	saveDefaultConfig();
    }
    
    
    
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(prefix + this.getConfig().getString("lang." + getConfig().getString("language") + "." + "onlyingame"));
		}else{
			
			Player player = (Player) sender;
			
			if(label.equalsIgnoreCase("inv")){
				if(args.length == 1){
					if(player.hasPermission("justinvsee.inv")){
						if (player.getServer().getPlayer(args[0]) != null) {
				            Player tplayer = player.getServer().getPlayer(args[0]);
				            Inventory tinv = tplayer.getInventory();
				            player.openInventory(tinv);
				            player.playEffect(tplayer.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
				            if(tplayer.isOp()){
				            	tplayer.sendMessage(prefix + ChatColor.GOLD + player.getName() + this.getConfig().getString("lang." + getConfig().getString("language") + "." + "lookedinyourinv"));
				            }else{
				            }
						}else{
							player.sendMessage(prefix + this.getConfig().getString("lang." + getConfig().getString("language") + "." + "playernotonline"));
						}
					}else{
						player.sendMessage(prefix + this.getConfig().getString("lang." + getConfig().getString("language") + "." + "needop"));
					}
				}else{
					player.sendMessage(prefix + this.getConfig().getString("lang." + getConfig().getString("language") + "." + "usage"));
				}
			}else if(label.equalsIgnoreCase("enderinv")){
				if(args.length == 1){
					if(player.hasPermission("justinvsee.enderinv")){
						if (player.getServer().getPlayer(args[0]) != null) {
				            Player tplayer = player.getServer().getPlayer(args[0]);
				            Inventory tinv = tplayer.getEnderChest();
				            player.openInventory(tinv);
				            if(tplayer.isOp()){
				            	tplayer.sendMessage(prefix + ChatColor.GOLD + player.getName() + this.getConfig().getString("lang." + getConfig().getString("language") + "." + "lookedinyourinv"));
				            }else{
				            }
						}else{
							player.sendMessage(prefix + this.getConfig().getString("lang." + getConfig().getString("language") + "." + "playernotonline"));
						}
					}else{
						player.sendMessage(prefix + this.getConfig().getString("lang." + getConfig().getString("language") + "." + "needop"));
					}
				}else{
					player.sendMessage(prefix + this.getConfig().getString("lang." + getConfig().getString("language") + "." + "enderusage"));
				}
			}
		}
		return false;
	}
}
