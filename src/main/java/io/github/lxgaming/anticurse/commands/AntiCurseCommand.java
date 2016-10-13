package io.github.lxgaming.anticurse.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AntiCurseCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("anticurse") && sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "AntiCurse" + ChatColor.GOLD + " ===== ");
			player.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Version 0.3.2");
			player.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "Commands" + ChatColor.GOLD + " ===== ");
			player.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Anticurse");
			return true;
		}
		return false;
	}
}