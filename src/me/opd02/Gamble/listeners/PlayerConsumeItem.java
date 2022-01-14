package me.opd02.Gamble.listeners;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import me.opd02.Gamble.GamblePlugin;
import net.milkbowl.vault.economy.Economy;

public class PlayerConsumeItem implements Listener {
	
	GamblePlugin gamblePlugin;
	private String prefix = "§b§lGambler §7» ";
    private Economy economy = GamblePlugin.economy;

	public PlayerConsumeItem(GamblePlugin gamblePlugin) {
		gamblePlugin = this.gamblePlugin;
	}

	@EventHandler
	public void onPlayerConsumeItem(PlayerItemConsumeEvent e){
		
		Player p = e.getPlayer();
		ItemStack item = e.getItem();
		
		if(!item.getType().equals(Material.POTION)) return;
		if(!item.hasItemMeta() || !item.getItemMeta().hasDisplayName() || !item.getItemMeta().hasLore()) return;

		if(item.getItemMeta().getLore().toString().contains("Drink this potion")){
			
			drinkPotion(item, p);
			p.getInventory().removeItem(item);
			e.setCancelled(true);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void drinkPotion(ItemStack potion, Player p){
	    Random random = new Random();
		double spunChance = random.nextDouble();
	    
		List<String> lore = potion.getItemMeta().getLore();
		String costString = lore.get(3).substring(15, lore.get(3).length()).replaceAll(",", "");
		int costInt = Integer.valueOf(costString);
		double costDouble = Double.valueOf(costString);
		
		if(100 * spunChance >= 50){
			
			Bukkit.getServer().broadcastMessage((prefix + "§b" + p.getName() + "§7 has §a§lwon §r$" + String.format("%,d", costInt * 2) + "§7 from a gamble potion!"));
			//Bukkit.getServer().broadcastMessage((prefix + "§b" + p.getName() + "§7 has won §r" + String.valueOf(Math.multiplyExact(2, Integer.parseInt(cost))) + "§7 from a gamble potion!"));
			
			economy.depositPlayer(p.getName(), costDouble * 2);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
			p.sendTitle("§aCongratulations!", "§aYou won!");
			return;
		} else{
			Bukkit.getServer().broadcastMessage((prefix + "§b" + p.getName() + "§7 has §c§llost §r$" + String.format("%,d", costInt) + "§7 from a gamble potion!"));
			//Bukkit.getServer().broadcastMessage((prefix + "§b" + p.getName() + "§7 has won §r" + String.valueOf(Math.multiplyExact(2, Integer.parseInt(cost))) + "§7 from a gamble potion!"));

			p.playSound(p.getLocation(), Sound.GLASS, 1, (float) 0.5);
			p.sendTitle("§cSorry!", "§cBetter luck next time!");
			return;
		}
		
		
	}
}
