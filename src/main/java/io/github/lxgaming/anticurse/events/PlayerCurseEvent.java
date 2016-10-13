package io.github.lxgaming.anticurse.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import io.github.lxgaming.anticurse.AntiCurse;

public class PlayerCurseEvent implements Listener {
	
	@EventHandler
	public void onPlayerCurseEvent (AsyncPlayerChatEvent PCE) {
		Player PCEP = PCE.getPlayer();
		for (String Curses : PCE.getMessage().toLowerCase().split(" ")) {
			if (AntiCurse.curses.getStringList("AntiCurse.Curses").contains(Curses)) {
				if (PCEP.hasPermission("AntiCurse.Bypass")) {
					AntiCurse.instance.getLogger().info("[Allowed]" + " [" + PCEP.getName() + "] " + PCE.getMessage());
				} else {
					AntiCurse.instance.getLogger().info("[Blocked]" + " [" + PCEP.getName() + "] " + PCE.getMessage());
					PCE.setCancelled(true);
					PCEP.sendMessage(ChatColor.translateAlternateColorCodes('&', AntiCurse.config.getString("AntiCurse.PlayerCurseEvent.Message")));
				}
			}
		}
	}
}