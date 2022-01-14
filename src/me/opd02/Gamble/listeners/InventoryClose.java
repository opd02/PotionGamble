package me.opd02.Gamble.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import me.opd02.Gamble.GamblePlugin;

public class InventoryClose implements Listener {

	GamblePlugin gamblePlugin;
	
	public InventoryClose(GamblePlugin gamblePlugin){
		gamblePlugin = this.gamblePlugin;
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e){
		
		Player p = (Player) e.getPlayer();
		
		if(e.getView() != null && e.getView().getTitle() != null && e.getView().getTitle().equals("§b§lGAMBLE MENU")){
			
			p.playSound(p.getLocation(), Sound.CHEST_CLOSE, (float) 0.4, (float) 1.5);
			
			return;
		}
	}
}
