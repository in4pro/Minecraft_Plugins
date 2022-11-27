package me.in4.minecraftmadness;

import me.in4.minecraftmadness.Events.*;
import me.in4.minecraftmadness.classes.Hoes;
import me.in4.minecraftmadness.commands.Commands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import me.in4.minecraftmadness.classes.RunClass;
import org.bukkit.scoreboard.*;

public final class MinecraftMadness extends JavaPlugin {

    public static MinecraftMadness instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("pizdets").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new SuperHoeEvent(), this);
        getServer().getPluginManager().registerEvents(new KillEvent(), this);
        getServer().getPluginManager().registerEvents(new DumbPickEvent(), this);
        getServer().getPluginManager().registerEvents(new IronPickEvent(), this);
        getServer().getPluginManager().registerEvents(new EchantsChestEvent(), this);
        getServer().getPluginManager().registerEvents(new OpItemsChestEvent(), this);
        getServer().getPluginManager().registerEvents(new CopyPasterHoeEvent(), this);
        getServer().getPluginManager().registerEvents(new TeleporterHoeEvent(), this);
        getServer().getPluginManager().registerEvents(new BossesEvents(), this);

    }
}
