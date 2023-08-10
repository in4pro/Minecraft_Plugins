package me.in4.minecraftbutyoucanonlystandondirt;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftButYouCanOnlyStandOnDirt extends JavaPlugin {

    public static MinecraftButYouCanOnlyStandOnDirt plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getServer().getPluginManager().registerEvents(new DeathEvent(), this);

    }
    
}
