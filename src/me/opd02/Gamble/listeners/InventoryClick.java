package me.opd02.Gamble.listeners;

import java.sql.SQLException;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

import me.opd02.Gamble.GamblePlugin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class InventoryClick implements Listener {

	GamblePlugin gamblePlugin;
	
	public InventoryClick(GamblePlugin gamblePlugin){
		gamblePlugin = this.gamblePlugin;
	}
	
    private Economy economy = GamblePlugin.economy;
	
    String prefix = "§b§lGambler §7»§r ";
	
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e){
    	
    	Player p = (Player) e.getWhoClicked();
    	ItemStack item = e.getCurrentItem();
    	
    	
    	if(e.getSlotType().equals(SlotType.OUTSIDE)) return;
    	
    	if(e.getClickedInventory().getTitle() != null && e.getClickedInventory().getTitle().equals("§b§lGAMBLE MENU")){
    		
    		if(item.getType() != Material.AIR && item.getType().equals(Material.POTION) && item.getItemMeta().hasDisplayName()){
    			
    			switch(item.getItemMeta().getDisplayName()){
    			
    			case "§c§lTier I Gamble Potion":
    				if(p.getInventory().firstEmpty() == -1){
    					p.sendMessage(prefix + "§cYour inventory is full!");
    					p.playSound(p.getLocation(), Sound.WITHER_SHOOT, (float) 0.5, 2);
    					e.setCancelled(true);
    					return;
    				}
    				EconomyResponse r1 = economy.withdrawPlayer(p.getName(), 500000);
        			if(r1.transactionSuccess()){
        				p.getInventory().addItem(item);
        				p.sendMessage(prefix + "§aYou have bought a Tier I gamble potion!");
        				p.playSound(p.getLocation(), Sound.VILLAGER_YES, (float) 0.5, 1);
        			//	if(gamblePlugin.getConfig().getBoolean("enable-database-usage") == true){
            				try {
    							//gamblePlugin.statement.executeUpdate("INSERT INTO Purchases (PLAYERNAME, TIER) VALUES ('" + p.getName() + ", 1');");
            					gamblePlugin.statement.executeUpdate("INSERT INTO PlayerData (PLAYERNAME, BALANCE) VALUES ('Playername', 100);");
    						} catch (SQLException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    							p.sendMessage("Oh No");
    						}	
        				//}
        				
        			}
        			else{
        				p.sendMessage(prefix + "§cYou do not have enough money to buy this item!");
        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, (float) 0.5, 1);
        			}
        			e.setCancelled(true);
    				break;
    			
    			case "§9§lTier II Gamble Potion":
    				if(p.getInventory().firstEmpty() == -1){
    					p.sendMessage(prefix + "§cYour inventory is full!");
    					p.playSound(p.getLocation(), Sound.WITHER_SHOOT, (float) 0.5, 2);
    					e.setCancelled(true);
    					return;
    				}
    				EconomyResponse r2 = economy.withdrawPlayer(p.getName(), 1000000);
        			if(r2.transactionSuccess()){
        				p.getInventory().addItem(item);
        				p.sendMessage(prefix + "§aYou have bought a Tier II gamble potion!");
        				p.playSound(p.getLocation(), Sound.VILLAGER_YES, (float) 0.5, 1);
        			}
        			else{
        				p.sendMessage(prefix + "§cYou do not have enough money to buy this item!");
        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, (float) 0.5, 1);
        			}
        			e.setCancelled(true);
    				break;
    			
    			case "§2§lTier III Gamble Potion":
    				if(p.getInventory().firstEmpty() == -1){
    					p.sendMessage(prefix + "§cYour inventory is full!");
    					p.playSound(p.getLocation(), Sound.WITHER_SHOOT, (float) 0.5, 2);
    					e.setCancelled(true);
    					return;
    				}
    				EconomyResponse r3 = economy.withdrawPlayer(p.getName(), 2500000);
        			if(r3.transactionSuccess()){
        				p.getInventory().addItem(item);
        				p.sendMessage(prefix + "§aYou have bought a Tier III gamble potion!");
        				p.playSound(p.getLocation(), Sound.VILLAGER_YES, (float) 0.5, 1);
        			}
        			else{
        				p.sendMessage(prefix + "§cYou do not have enough money to buy this item!");
        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, (float) 0.5, 1);
        			}
        			e.setCancelled(true);
    				break;
    			
    			case "§6§lTier IV Gamble Potion":
    				if(p.getInventory().firstEmpty() == -1){
    					p.sendMessage(prefix + "§cYour inventory is full!");
    					p.playSound(p.getLocation(), Sound.WITHER_SHOOT, (float) 0.5, 2);
    					e.setCancelled(true);
    					return;
    				}
    				EconomyResponse r4 = economy.withdrawPlayer(p.getName(), 5000000);
        			if(r4.transactionSuccess()){
        				p.getInventory().addItem(item);
        				p.sendMessage(prefix + "§aYou have bought a Tier IV gamble potion!");
        				p.playSound(p.getLocation(), Sound.VILLAGER_YES, (float) 0.5, 1);
        			}
        			else{
        				p.sendMessage(prefix + "§cYou do not have enough money to buy this item!");
        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, (float) 0.5, 1);
        			}
        			e.setCancelled(true);
    				break;
    				
    			case "§8§k|||§r§a§l Tier V Gamble Potion §r§8§k|||":
    				if(p.getInventory().firstEmpty() == -1){
    					p.sendMessage(prefix + "§cYour inventory is full!");
    					p.playSound(p.getLocation(), Sound.WITHER_SHOOT, (float) 0.5, 2);
    					e.setCancelled(true);
    					return;
    				}
    				EconomyResponse r5 = economy.withdrawPlayer(p.getName(), 10000000);
        			if(r5.transactionSuccess()){
        				p.getInventory().addItem(item);
        				p.sendMessage(prefix + "§aYou have bought a Tier V gamble potion!");
        				p.playSound(p.getLocation(), Sound.VILLAGER_YES, (float) 0.5, 1);
        			}
        			else{
        				p.sendMessage(prefix + "§cYou do not have enough money to buy this item!");
        				p.playSound(p.getLocation(), Sound.VILLAGER_NO, (float) 0.5, 1);
        			}
        			e.setCancelled(true);
    				break;
    			
    			default:
    				break;
    				
    			}
    			
    		}
    		
    		e.setCancelled(true);
    		
    	}
    }
    
}
