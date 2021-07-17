package mc.danielwang.playerdata;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Playerdata extends JavaPlugin {

    private static Economy econ = null;

    @Override
    public void onEnable() {
        //VaultAPI
        if (!setupEconomy() ) {
            System.out.println("No economy plugin found. Disabling Vault");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getLogger().info("--------------------");
        getLogger().info("   Log Player Data   ");
        getLogger().info("      Loading...     ");
        getLogger().info("--------------------");

        getServer().getPluginManager().registerEvents(new PlayerJoinLog(),this);
        getCommand("pd").setExecutor(new PDCommand());
    }
    //VaultAPI
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    //VaultAPI
    public static Economy getEconomy() {
        return econ;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
