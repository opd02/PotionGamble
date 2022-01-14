package me.opd02.Gamble.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.opd02.Gamble.GamblePlugin;
import me.opd02.Gamble.utilities.ItemUtil;

public class VillagerClick implements Listener {

	private GamblePlugin gamblePlugin;
	
	public VillagerClick(GamblePlugin gamblePlugin){
		gamblePlugin = this.gamblePlugin;
	}
	
	@EventHandler
	public void onPlayerVillagerInteract(PlayerInteractEntityEvent e){
		
		Player p = e.getPlayer();
		Entity ent = e.getRightClicked();
		
		if(!(ent instanceof Villager)) return;
		
		if(ent.getCustomName() != null && ent.getCustomName().equalsIgnoreCase("§b§lGAMBLER")){
			
			openGUI(p);
			
			e.setCancelled(true);
		}
	}
	
	public void openGUI(Player p){
		
		//Inventory gui = Bukkit.createInventory(p, 9, "§b§lGAMBLE MENU§7 - Select a potion");
		Inventory gui = Bukkit.createInventory(p, 9, "§b§lGAMBLE MENU");
		
		ItemStack blueGlass = ItemUtil.createItem(" ", Material.STAINED_GLASS_PANE, 1, (byte) 3, null);
		
		gui.setItem(0, blueGlass);
		gui.setItem(1, blueGlass);
		gui.setItem(7, blueGlass);
		gui.setItem(8, blueGlass);
		p.playSound(p.getLocation(), Sound.CHEST_OPEN, (float) 0.4, (float) 1.5);
		
		List<String> firstLore = new ArrayList<String>();
		firstLore.add("§7This gamble potion is purchasable by talking");
		firstLore.add("§7to the Gambler at spawn");
		firstLore.add(" ");
		firstLore.add("§l§c * Cost: §r500,000");
		firstLore.add("§l§c * Reward: §r1,000,000");
		firstLore.add(" ");
		firstLore.add("§l§c * Chance: §r50/50");
		firstLore.add(" ");
		firstLore.add("§f§l(!) Drink this potion to gamble the value");
		
		List<String> secondLore = new ArrayList<String>();
		secondLore.add("§7This gamble potion is purchasable by talking");
		secondLore.add("§7to the Gambler at spawn");
		secondLore.add(" ");
		secondLore.add("§l§9 * Cost: §r1,000,000");
		secondLore.add("§l§9 * Reward: §r2,000,000");
		secondLore.add(" ");
		secondLore.add("§l§9 * Chance: §r50/50");
		secondLore.add(" ");
		secondLore.add("§f§l(!) Drink this potion to gamble the value");
		
		List<String> thirdLore = new ArrayList<String>();
		thirdLore.add("§7This gamble potion is purchasable by talking");
		thirdLore.add("§7to the Gambler at spawn");
		thirdLore.add(" ");
		thirdLore.add("§l§2 * Cost: §r2,500,000");
		thirdLore.add("§l§2 * Reward: §r5,000,000");
		thirdLore.add(" ");
		thirdLore.add("§l§2 * Chance: §r50/50");
		thirdLore.add(" ");
		thirdLore.add("§f§l(!) Drink this potion to gamble the value");
		
		List<String> forthLore = new ArrayList<String>();
		forthLore.add("§7This gamble potion is purchasable by talking");
		forthLore.add("§7to the Gambler at spawn");
		forthLore.add(" ");
		forthLore.add("§l§6 * Cost: §r5,000,000");
		forthLore.add("§l§6 * Reward: §r10,000,000");
		forthLore.add(" ");
		forthLore.add("§l§6 * Chance: §r50/50");
		forthLore.add(" ");
		forthLore.add("§f§l(!) Drink this potion to gamble the value");
		
		List<String> fifthLore = new ArrayList<String>();
		fifthLore.add("§7This gamble potion is purchasable by talking");
		fifthLore.add("§7to the Gambler at spawn");
		fifthLore.add(" ");
		fifthLore.add("§l§a * Cost: §r10,000,000");
		fifthLore.add("§l§a * Reward: §r20,000,000");
		fifthLore.add(" ");
		fifthLore.add("§l§a * Chance: §r50/50");
		fifthLore.add(" ");
		fifthLore.add("§f§l(!) Drink this potion to gamble the value");
		
		ItemStack first = ItemUtil.createPotion(ChatColor.RED + "" + ChatColor.BOLD + "Tier I Gamble Potion", Material.POTION, 1, (byte) 0, (short) 5, firstLore);
		ItemStack second = ItemUtil.createPotion(ChatColor.BLUE + "" + ChatColor.BOLD + "Tier II Gamble Potion", Material.POTION, 1, (byte) 0, (short) 13, secondLore);
		ItemStack third = ItemUtil.createPotion(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Tier III Gamble Potion", Material.POTION, 1, (byte) 0, (short) 20, thirdLore);
		ItemStack forth = ItemUtil.createPotion(ChatColor.GOLD + "" + ChatColor.BOLD + "Tier IV Gamble Potion", Material.POTION, 1, (byte) 0, (short) 3, forthLore);
		ItemStack fifth = ItemUtil.createPotion("§8§k|||§r§a§l Tier V Gamble Potion §r§8§k|||", Material.POTION, 1, (byte) 0, (short) 8203, fifthLore);
		
		gui.addItem(first);
		gui.addItem(second);
		gui.addItem(third);
		gui.addItem(forth);
		gui.addItem(fifth);
		
		p.openInventory(gui);
	}
	
}
