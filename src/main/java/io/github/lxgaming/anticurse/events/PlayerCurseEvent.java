/*
 * Copyright 2017 Alex Thomson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
