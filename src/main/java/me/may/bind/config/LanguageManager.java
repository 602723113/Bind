package me.may.bind.config;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

public class LanguageManager {
	
	private FileConfiguration config;
	private Map<String, String> map = new HashMap<String, String>();
	
	public LanguageManager(FileConfiguration config) {
		this.config = config;
		map.put("BindSuccess", config.getString("Tips.BindSuccess").replaceAll("&", "§"));
		map.put("BindFail", config.getString("Tips.BindFail").replaceAll("&", "§"));
		map.put("UnBindSuccess", config.getString("Tips.UnBindSuccess").replaceAll("&", "§"));
		map.put("UnBindFail", config.getString("Tips.UnBindFail").replaceAll("&", "§"));
		map.put("ReloadSuccess", config.getString("Tips.ReloadSuccess").replaceAll("&", "§"));
		map.put("NoPermission", config.getString("Tips.NoPermission").replaceAll("&", "§"));
		map.put("Inventory_IsBind", config.getString("Tips.Inventory.IsBind").replaceAll("&", "§"));
		map.put("Drop_IsBind", config.getString("Tips.Drop.IsBind").replaceAll("&", "§"));
		map.put("Craft_IsBind", config.getString("Tips.Craft.IsBind").replaceAll("&", "§"));
	}
	
	public String getLanguage(String key) {
		if (map.containsKey(key)) {
			return map.get(key);
		}else {
			return "";
		}
	}
	
	public void reload() {
		map.put("BindSuccess", config.getString("Tips.BindSuccess").replaceAll("&", "§"));
		map.put("BindFail", config.getString("Tips.BindFail").replaceAll("&", "§"));
		map.put("UnBindSuccess", config.getString("Tips.UnBindSuccess").replaceAll("&", "§"));
		map.put("UnBindFail", config.getString("Tips.UnBindFail").replaceAll("&", "§"));
		map.put("ReloadSuccess", config.getString("Tips.ReloadSuccess").replaceAll("&", "§"));
		map.put("NoPermission", config.getString("Tips.NoPermission").replaceAll("&", "§"));
		map.put("Inventory_IsBind", config.getString("Tips.Inventory.IsBind").replaceAll("&", "§"));
		map.put("Drop_IsBind", config.getString("Tips.Drop.IsBind").replaceAll("&", "§"));
		map.put("Craft_IsBind", config.getString("Tips.Craft.IsBind").replaceAll("&", "§"));
	}
}
