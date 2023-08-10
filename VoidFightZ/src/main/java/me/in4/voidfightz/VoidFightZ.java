package me.in4.voidfightz;

import me.in4.voidfightz.commands.InvulnerableCommand;
import me.in4.voidfightz.commands.QueueCommand;
import me.in4.voidfightz.events.*;
import me.in4.voidfightz.items.Items;
import me.in4.voidfightz.items.PotionOfLevitation;
import me.in4.voidfightz.runnables.RunClass2;
import me.in4.voidfightz.runnables.RunClass3;
import me.in4.voidfightz.runnables.WorldsRunClass;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidFightZ extends JavaPlugin {

    public static VoidFightZ plugin;

    @Override
    public void onEnable() {
        plugin = this;
        ItemsEvents item_events = new ItemsEvents();
        Kills kills_events = new Kills();
        PotionOfLevitation.init();
        Items.init();
        getCommand("voidfightz").setExecutor(new QueueCommand());
        getCommand("invulnerable").setExecutor(new InvulnerableCommand());
        Bukkit.getServer().getPluginManager().registerEvents(item_events, plugin);
        Bukkit.getServer().getPluginManager().registerEvents(kills_events, plugin);

        new WorldsRunClass(item_events, kills_events).runTaskLater(plugin, 1);
        new RunClass2().runTaskTimer(plugin, 1, 140);
        new RunClass3().runTaskTimer(plugin, 1, 2400);
    }

}
