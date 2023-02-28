package me.in4.voidfightz;

import me.in4.voidfightz.commands.QueueCommand;
import me.in4.voidfightz.events.*;
import me.in4.voidfightz.events.RunClass;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidFightZ extends JavaPlugin {

    public static VoidFightZ plugin;

    @Override
    public void onEnable() {
        plugin = this;
        PotionOfLevitation.init();
        getCommand("voidfightz").setExecutor(new QueueCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new ItemsEvents(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Kills(), this);

        new WorldsRunClass().runTaskLater(plugin, 1);
        new RunClass2().runTaskTimer(plugin, 1, 100);
        new RunClass3().runTaskTimer(plugin, 1, 2400);

    }

}
