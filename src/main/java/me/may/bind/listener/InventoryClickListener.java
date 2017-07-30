package me.may.bind.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.may.bind.Entry;
import me.may.bind.api.BindAPI;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getRawSlot() < 0) {
			return;
		}
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		
		Player player = (Player) e.getWhoClicked();
		if (player.isOp()) {
			return;
		}
		ItemStack currentItem = e.getCurrentItem();
		if (BindAPI.isBind(currentItem) 
				&& !e.getInventory().getTitle().equalsIgnoreCase("container.inventory") 
				&& !e.getInventory().getTitle().equalsIgnoreCase("container.crafting")) {
			e.setCancelled(true);
			player.sendMessage(Entry.getInstance().getLanagerManager().getLanguage("Inventory_IsBind"));
			return;
		}
	}
}
