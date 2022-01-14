package me.opd02.Gamble.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class SummonGamblerCommand implements CommandExecutor{

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]){
    	if(cmd.getName().equalsIgnoreCase("gamblersummon")){
    		
    		if(!(sender instanceof Player)) return true;
    		
    		Player p = (Player) sender;
    		
    		if(!p.hasPermission("voyagegamble.summon")){
    			p.sendMessage(ChatColor.RED + "You do not have permision to use that command!");
    			return true;
    		}
    		
    		summonGambler(p);
    		p.sendMessage(ChatColor.GREEN + "[Gamble] Gambler has been summoned.");
    		
    		return true;
    		
    	}
    	
    	return false;
    }
	
    public static void summonGambler(Player p){
		Villager v = (Villager) p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
		v.setCustomName("§b§lGAMBLER");
		v.setCustomNameVisible(true);
		v.setCanPickupItems(false);
		v.spigot().isInvulnerable();
		v.setProfession(Profession.LIBRARIAN);
		
		setInvulnerable((Entity)v);
		setSilent((Entity)v);
		noAI((Entity)v);
		
		p.sendMessage(ChatColor.GREEN + "Gambler summoned!");
    }

    static void noAI(Entity bukkitEntity) {
        net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) bukkitEntity).getHandle();
        NBTTagCompound tag = nmsEntity.getNBTTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        nmsEntity.c(tag);
        tag.setInt("NoAI", 1);
        nmsEntity.f(tag);
    }
    
    static void setSilent(Entity bukkitEntity) {
        net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) bukkitEntity).getHandle();
        NBTTagCompound tag = nmsEntity.getNBTTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        nmsEntity.c(tag);
        tag.setInt("Silent", 1);
        nmsEntity.f(tag);
    }
    
    static void setInvulnerable(Entity bukkitEntity) {
        net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) bukkitEntity).getHandle();
        NBTTagCompound tag = nmsEntity.getNBTTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        nmsEntity.c(tag);
        tag.setInt("Invulnerable", 1);
        nmsEntity.f(tag);
    }
}
