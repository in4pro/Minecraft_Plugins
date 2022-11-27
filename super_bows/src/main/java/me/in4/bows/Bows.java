package me.in4.bows;

import me.in4.bows.commands.BowsCommand;
import me.in4.bows.events.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bows extends JavaPlugin {

    public void onEnable() {
        //getServer().getPluginManager().registerEvents(new TeleportBowEvent(), this);
        getServer().getPluginManager().registerEvents(new TNTBowEvent(), this);
        getServer().getPluginManager().registerEvents(new SuperJumpBowEvent(), this);
        getServer().getPluginManager().registerEvents(new EnchantBowEvent(), this);
        getServer().getPluginManager().registerEvents(new XRayBowEvent(), this);
        getServer().getPluginManager().registerEvents(new DashBowEvent(), this);
        getCommand("bows").setExecutor(new BowsCommand());
    }
}