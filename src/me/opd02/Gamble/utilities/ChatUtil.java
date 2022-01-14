package me.opd02.Gamble.utilities;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.opd02.Gamble.GamblePlugin;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class ChatUtil {
	
    private GamblePlugin gamblePlugin;
    public String prefix = this.colorize("&b&lGambler &7» ");
    
    public ChatUtil(final GamblePlugin gamblePlugin) {
        this.gamblePlugin = gamblePlugin;
    }
    
    public String colorize(final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    
    public void sendConsoleMessage(final String msg) {
        Bukkit.getConsoleSender().sendMessage(this.colorize(msg));
    }
    
    public void sendMessage(final CommandSender p, final String msg) {
        final String prefix = this.gamblePlugin.getConfig().getString("prefix");
        p.sendMessage(this.colorize(prefix + msg));
    }
    
    public void sendMessageWithoutPrefix(final CommandSender p, final String msg) {
        p.sendMessage(this.colorize(msg));
    }
    
    public void sendAnnouncement(final String msg) {
        final String prefix = this.gamblePlugin.getConfig().getString("prefix");
      //  Bukkit.broadcastMessage(this.colorize(prefix + msg));
        for(Player pla : Bukkit.getServer().getOnlinePlayers()){
        	pla.sendMessage(this.colorize(prefix + msg));
        }
    }
    
    public void sendBroadcast(final String msg) {
      //  Bukkit.broadcastMessage(this.colorize(msg));
        for(Player pla : Bukkit.getServer().getOnlinePlayers()){
        	pla.sendMessage(this.colorize(msg));
        }
    }
    
    public void sendList(final CommandSender p, final List<String> list) {
        for (final String msg : list) {
            p.sendMessage(this.colorize(msg));
        }
    }
    
    public TextComponent createHoverMessage(final String msg, final String hoverText, final String command) {
        final TextComponent hoverMessage = new TextComponent(this.colorize(msg));
        hoverMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(this.colorize(hoverText)).create()));
        hoverMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return hoverMessage;
    }
}