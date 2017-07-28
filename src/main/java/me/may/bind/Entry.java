package me.may.bind;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

import me.may.bind.command.BindCommand;
import me.may.bind.config.LanguageManager;
import me.may.bind.listener.CraftItemListener;
import me.may.bind.listener.InventoryClickListener;
import me.may.bind.listener.PlayerDropItemListener;
import me.may.bind.listener.PlayerInteractListener;

public final class Entry extends JavaPlugin {
	
	private static Entry instance;
	private LanguageManager manager;
	public String bindKey;
	public String bindPreKey;
	public Sound sound;
	
	public void onEnable() {
		instance = this;
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		Bukkit.getPluginCommand("bind").setExecutor(new BindCommand());
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(), this);
		Bukkit.getPluginManager().registerEvents(new CraftItemListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
		
		bindKey = getConfig().getString("Bind.Key").replaceAll("&", "§");
		if (bindKey == null || bindKey.isEmpty() || bindKey.equalsIgnoreCase("")) {
			bindKey = "§4灵魂绑定";
		}
		
		bindPreKey = getConfig().getString("Bind.PreKey").replaceAll("&", "§");
		if (bindPreKey == null || bindPreKey.isEmpty() || bindPreKey.equalsIgnoreCase("")) {
			bindPreKey = "§e使用后绑定";
		}
		
		sound = Sound.valueOf(getConfig().getString("Bind.Sound"));
		if (sound == null) {
			sound = Sound.LEVEL_UP;
		}
		
		manager = new LanguageManager(getConfig());
	}
	
	public static Entry getInstance() {
		return instance;
	}
	
	public LanguageManager getLanagerManager() {
		return manager;
	}
}
