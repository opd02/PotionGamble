package me.opd02.Gamble;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.opd02.Gamble.commands.SummonGamblerCommand;
import me.opd02.Gamble.listeners.InventoryClick;
import me.opd02.Gamble.listeners.InventoryClose;
import me.opd02.Gamble.listeners.PlayerConsumeItem;
import me.opd02.Gamble.listeners.VillagerClick;
import me.opd02.Gamble.utilities.ChatUtil;
import net.milkbowl.vault.economy.Economy;

public class GamblePlugin extends JavaPlugin{

    public static Economy economy;
    private ChatUtil chatUtil;
    String host, port, database, username, password;
    static Connection connection;
    public java.sql.Statement statement;
    
    public GamblePlugin(){
    	this.setChatUtil(new ChatUtil(this));
    }
	
	public void onEnable(){
		
		//if(!new AdvancedLicense("WBEQ-DI31-R439-8KM2", "http://100.24.36.85/verify.php", this).register()) return;
		
        if (!setupEconomy()) {
        	chatUtil.sendConsoleMessage((ChatColor.RED + "[VoyageGamble] You must install an economy!"));
            Bukkit.shutdown();
        }
        
        //COMMANDS
        this.getCommand("gamblersummon").setExecutor(new SummonGamblerCommand());
        
        //EVENTS
        Bukkit.getPluginManager().registerEvents(new PlayerConsumeItem(this), this);
        Bukkit.getPluginManager().registerEvents(new VillagerClick(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClose(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(this), this);
        
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        
        if(this.getConfig().getBoolean("enable-database-usage") == true){
            host = this.getConfig().getString("databse.host");
            port = this.getConfig().getString("databse.port");
            database = this.getConfig().getString("databse.database");
            username = this.getConfig().getString("databse.username");
            password = this.getConfig().getString("databse.password");
        }
        
        try {    
            openConnection();
            statement = connection.createStatement();          
        		} catch (ClassNotFoundException e) {
        			e.printStackTrace();
        		} catch (SQLException e) {
        			e.printStackTrace();
        }
        
	}
	
    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

	public ChatUtil getChatUtil() {
		return chatUtil;
	}

	public void setChatUtil(ChatUtil chatUtil) {
		this.chatUtil = chatUtil;
	}
	
	public void openConnection() throws SQLException, ClassNotFoundException {
		if (connection != null && !connection.isClosed()) {
				return;
			}
// Class.forName("com.mysql.jdbc.Driver"); - Use this with old version of the Driver
		//Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://"
        + this.host+ ":" + this.port + "/" + this.database,
        this.username, this.password);
}
    
}
