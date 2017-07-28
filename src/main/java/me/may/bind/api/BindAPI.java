package me.may.bind.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.may.bind.Entry;

/**
 * 物品绑定API
 * 
 * @author May_Speed
 * @since 2017/7/28
 */
public class BindAPI {
	
	/**
	 * 查询一个物品是否含有 使用后绑定 字样
	 * 
	 * @param item
	 *            物品
	 * @return true[有]/false[没有]
	 */
	public static boolean isPreBind(ItemStack item) {
		if (item != null && item.getType() != Material.AIR) {
			ItemMeta im = item.getItemMeta();
			if (im.hasLore()) { //判断有无Lore
				List<String> lore = im.getLore(); //对其引用
				for (int i = 0; i < lore.size(); i++) { //遍历判断
					if (lore.get(i).indexOf(Entry.getInstance().bindPreKey) != -1) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 查询一个物品是否已绑定
	 * 
	 * @param item
	 *            物品
	 * @return true[已绑定]/false[未绑定]
	 */
	public static boolean isBind(ItemStack item) {
		if (item != null && item.getType() != Material.AIR) {
			ItemMeta im = item.getItemMeta();
			if (im.hasLore()) { //判断有无Lore
				List<String> lore = im.getLore(); //对其引用
				for (int i = 0; i < lore.size(); i++) { //遍历判断
					if (lore.get(i).indexOf(Entry.getInstance().bindKey) != -1) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 绑定一个物品
	 * 
	 * @param item
	 *            物品
	 * @return true[成功]/false[失败]
	 */
	public static boolean bindItem(ItemStack item) {
		if (item != null && item.getType() != Material.AIR && !isBind(item)) {
			ItemMeta im = item.getItemMeta();
			List<String> lore = null;
			if (im.hasLore()) {
				lore = im.getLore();
			}else {
				lore = new ArrayList<String>();
			}
			lore.add(" ");
			lore.add(Entry.getInstance().bindKey);
			im.setLore(lore);
			item.setItemMeta(im);
			return true;
		}
		return false;
	}
	
	/**
	 * 解绑一个物品
	 * 
	 * @param item
	 *            物品
	 * @return true[成功]/false[失败]
	 */
	public static boolean unBindItem(ItemStack item) {
		if (item != null && item.getType() != Material.AIR && isBind(item)) {
			ItemMeta im = item.getItemMeta();
			if (!im.hasLore()) {
				return false;
			}
			List<String> lore = im.getLore();
			for (int i = 0; i < lore.size(); i++) {
				if (lore.get(i).indexOf(Entry.getInstance().bindKey) != -1) {
					lore.remove(i);
				}
			}
			im.setLore(lore);
			item.setItemMeta(im);
			return true;
		}
		return false;
	}
	
	
}
