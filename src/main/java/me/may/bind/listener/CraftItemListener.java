package me.may.bind.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import me.may.bind.Entry;
import me.may.bind.api.BindAPI;

public class CraftItemListener implements Listener {
	
	@EventHandler
	public void onCraft(CraftItemEvent e) {
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getWhoClicked();
		if (player.isOp()) {
			return;
		}
		ItemStack[] item = e.getInventory().getMatrix();
		for (int i = 0; i < item.length; i++) {
			ItemStack itemStack = item[i];
			if (BindAPI.isBind(itemStack)) {
				e.setCancelled(true);
				player.sendMessage(Entry.getInstance().getLanagerManager().getLanguage("Craft_IsBind"));
			}
		}
	}
}
