package me.may.bind.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import me.may.bind.Entry;
import me.may.bind.api.BindAPI;

public class PlayerDropItemListener implements Listener {
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		ItemStack item = e.getItemDrop().getItemStack();
		if (BindAPI.isBind(item) && !e.getPlayer().isOp()) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(Entry.getInstance().getLanagerManager().getLanguage("Drop_IsBind"));
			return;
		}
	}
}
