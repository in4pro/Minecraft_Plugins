package me.in4.bedrockgame;

import me.in4.bedrockgame.Commands.Command;

import me.in4.bedrockgame.Events.BedrockBootsEvent;
import me.in4.bedrockgame.Events.BedrockDropEvent;
import me.in4.bedrockgame.Events.BedrockPickaxeEvent;
import me.in4.bedrockgame.Events.BedrockSword;
import org.bukkit.plugin.java.JavaPlugin;

public final class BedrockGame extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("start").setExecutor(new Command());
        getServer().getPluginManager().registerEvents(new BedrockBootsEvent(), this);
        getServer().getPluginManager().registerEvents(new BedrockSword(), this);
        getServer().getPluginManager().registerEvents(new BedrockDropEvent(), this);
        getServer().getPluginManager().registerEvents(new BedrockPickaxeEvent(), this);
    }

}
