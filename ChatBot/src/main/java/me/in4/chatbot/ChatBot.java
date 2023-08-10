package me.in4.chatbot;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ChatBot extends JavaPlugin {

    public static ChatBot plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Objects.requireNonNull(getCommand("spambot")).setExecutor(new SpamCommand());
        Objects.requireNonNull(getCommand("say")).setExecutor(new ChatCommand());
    }

}
