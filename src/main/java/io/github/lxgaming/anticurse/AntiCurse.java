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

package io.github.lxgaming.anticurse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.lxgaming.anticurse.commands.AntiCurseCommand;
import io.github.lxgaming.anticurse.events.PlayerCurseEvent;

public class AntiCurse extends JavaPlugin {

	public static AntiCurse instance;
	public static FileConfiguration config, curses;

	@Override
	public void onEnable() {
		instance = this;
		loadConfig();
		PluginManager pm = getServer().getPluginManager();
		if (AntiCurse.config.getBoolean("AntiCurse.PlayerCurseEvent.Enable") == true) {
			pm.registerEvents(new PlayerCurseEvent(), this);
		} else {
			getLogger().info("PlayerCurseEvent is Disabled!");
		}
		this.getCommand("anticurse").setExecutor(new AntiCurseCommand());
		getLogger().info("AntiCurse Has Started!");
	}
	
	@Override
	public void onDisable() {
		instance = null;
		getLogger().info("AntiCurse Has Stopped!");
	}
	
	public void loadConfig() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		File configFile = new File(getDataFolder(), "config.yml");
		File cursesFile = new File(getDataFolder(), "curses.yml");
		if (!configFile.exists()) {
			copy(getResource("config.yml"), configFile);
			getLogger().info("Config file created.");
		}
		if (!cursesFile.exists()) {
			copy(getResource("curses.yml"), cursesFile);
			getLogger().info("Curses file created.");
		}
		config = new YamlConfiguration();
		curses = new YamlConfiguration();
		try {
			config.load(configFile);
			curses.load(cursesFile);
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().severe("Failed to load files!");
		}
	}
	
	public void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().severe("Failed to save files!");
		}
	}
}
