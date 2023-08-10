package me.in4.npc;

import me.in4.npc.bots.Bot;
import me.in4.npc.commands.CreateBot;
import me.in4.npc.commands.RemoveBot;
import me.in4.npc.listeners.EventListeners;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Npc extends JavaPlugin {

    public static List<ServerPlayer> bots = new ArrayList<>();
    public static List<Bot> mobs = new ArrayList<>();
    public static Npc plugin;

    public static List<ServerPlayer> getBots() {
        return bots;
    }

    public static List<Bot> getMobs() {
        return mobs;
    }


    @Override
    public void onEnable() {
        plugin = this;
        getCommand("create").setExecutor(new CreateBot());
        getCommand("remove").setExecutor(new RemoveBot());
        getServer().getPluginManager().registerEvents(new EventListeners(), this);
    }

}
