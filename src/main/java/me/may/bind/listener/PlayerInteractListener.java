package me.may.bind.listener;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.may.bind.Entry;
import me.may.bind.api.BindAPI;

public class PlayerInteractListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			ItemStack item = e.getPlayer().getItemInHand();
			if (item != null && item.getType() != Material.AIR) {
				if (BindAPI.isPreBind(item)) {
					ItemMeta im = item.getItemMeta();
					List<String> lore = im.getLore();
					for (int i = 0; i < lore.size(); i++) {
						if (lore.get(i).indexOf(Entry.getInstance().bindPreKey) != -1) {
							lore.remove(i);
							im.setLore(lore);
							item.setItemMeta(im);
							break;
						}
					}
					BindAPI.bindItem(item);
					e.getPlayer().playSound(e.getPlayer().getLocation(), Entry.getInstance().sound, 0.3f, 1f);
				}
			}
		}
	}
}
