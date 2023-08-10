package me.in4.randomdrops;

import org.bukkit.plugin.java.JavaPlugin;

public final class RandomDrops extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new DropListeners(), this);
    }

}
