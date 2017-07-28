package me.may.bind.command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.may.bind.Entry;
import me.may.bind.api.BindAPI;

public class BindCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bind")) {
			if (args.length == 0) {
				sender.sendMessage("§8[§6Bind§8] §a§l>");
				sender.sendMessage("§b/bind hand §7绑定你手上的物品!");
				sender.sendMessage("§b/bind unbind §7解绑你手上的物品!");
				sender.sendMessage("§b/bind reload §7重载配置文件");
				return true;
			}
			
			if (!sender.isOp()) {
				sender.sendMessage(Entry.getInstance().getLanagerManager().getLanguage("NoPermission"));
				return true;
			}
			if (!(sender instanceof Player)) {
				return true;
			}
			Player player = (Player) sender;
			if (args[0].equalsIgnoreCase("hand")) {
				if (!BindAPI.bindItem(player.getItemInHand())) {
					player.sendMessage(Entry.getInstance().getLanagerManager().getLanguage("BindFail"));
					return true;
				}
				player.sendMessage(Entry.getInstance().getLanagerManager().getLanguage("BindSuccess"));
				return true;
			}
			
			if (args[0].equalsIgnoreCase("unbind")) {
				if (!BindAPI.unBindItem(player.getItemInHand())) {
					player.sendMessage(Entry.getInstance().getLanagerManager().getLanguage("UnBindFail"));
					return true;
				}
				player.sendMessage(Entry.getInstance().getLanagerManager().getLanguage("UnBindSuccess"));
				return true;
			}
			
			if (args[0].equalsIgnoreCase("reload")) {
				Entry.getInstance().reloadConfig();
				Entry.getInstance().bindKey = Entry.getInstance().getConfig().getString("Bind.Key").replaceAll("&", "§");
				if (Entry.getInstance().bindKey == null || Entry.getInstance().bindKey.isEmpty() || Entry.getInstance().bindKey.equalsIgnoreCase("")) {
					Entry.getInstance().bindKey = "§4灵魂绑定";
				}
				
				Entry.getInstance().bindPreKey = Entry.getInstance().getConfig().getString("Bind.PreKey").replaceAll("&", "§");
				if (Entry.getInstance().bindPreKey == null || Entry.getInstance().bindPreKey.isEmpty() || Entry.getInstance().bindPreKey.equalsIgnoreCase("")) {
					Entry.getInstance().bindPreKey = "§e使用后绑定";
				}
				
				Entry.getInstance().sound = Sound.valueOf(Entry.getInstance().getConfig().getString("Bind.Sound"));
				if (Entry.getInstance().sound == null) {
					Entry.getInstance().sound = Sound.LEVEL_UP;
				}
				
				player.sendMessage(Entry.getInstance().getLanagerManager().getLanguage("ReloadSuccess"));
				Entry.getInstance().getLanagerManager().reload();
				return true;
			}
		}
		return false;
	}
	
}
