package me.may.bind.listener;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.may.bind.Entry;
import me.may.bind.api.BindAPI;

public class PlayerInteractListener implements Listener {
	
	@EventHandler(priority = EventPriority.LOW)
	public void onInteract(final PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			final ItemStack item = e.getPlayer().getItemInHand();
			if (item != null && item.getType() != Material.AIR) {
				if (BindAPI.isPreBind(item)) {
					ItemMeta im = item.getItemMeta();
					List<String> lore = im.getLore();
					
					lore.remove(Entry.getInstance().bindPreKey);
					im.setLore(lore);
					item.setItemMeta(im);

					BindAPI.bindItem(item);
					e.getPlayer().playSound(e.getPlayer().getLocation(), Entry.getInstance().sound, 0.3f, 1f);
				}
			}
		}
//		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
//			ItemStack item = e.getPlayer().getItemInHand();
//			if (item != null && item.getType() != Material.AIR) {
//				if (BindAPI.isPreBind(item)) {
//					ItemMeta im = item.getItemMeta();
//					List<String> lore = im.getLore();
//					if (lore.contains(Entry.getInstance().bindPreKey)) {
//						System.out.println("yes");
//						System.out.println(lore);
//						System.out.println(BindAPI.bindItem(item));
//						System.out.println(lore);
//						lore.remove(Entry.getInstance().bindPreKey);
//						im.setLore(lore);
//						System.out.println(lore);
//						System.out.println(im);
//						item.setItemMeta(im);
//						e.getPlayer().playSound(e.getPlayer().getLocation(), Entry.getInstance().sound, 0.3f, 1f);
//					}
//					
//				}
//			}
//		}
	}
}
