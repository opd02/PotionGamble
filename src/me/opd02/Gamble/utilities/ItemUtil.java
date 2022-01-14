package me.opd02.Gamble.utilities;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {
	
	  public static ItemStack createItem(String name, Material mat, int amount, byte data, List<String> lore){
	    ItemStack item = new ItemStack(mat, amount, data);
	    ItemMeta meta = item.getItemMeta();
	    meta.setDisplayName(name);
	    meta.setLore(lore);
//	    meta.addEnchant(Enchantment.DURABILITY, 1, true);
//	    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//	    Glow glow = new Glow(70);
//	    meta.addEnchant(glow, 1, true);
	    
	    item.setItemMeta(meta);
	    return item;
	  }

	  public static ItemStack createPotion(String name, Material mat, int amount, byte data, short durability, List<String> lore){
		    ItemStack item = new ItemStack(mat, amount, data);
		    ItemMeta meta = item.getItemMeta();
		    meta.setDisplayName(name);
		    meta.setLore(lore);
		    item.setDurability(durability);
		    
		    meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		    
		    item.setItemMeta(meta);
		    return item;
		  }
}
