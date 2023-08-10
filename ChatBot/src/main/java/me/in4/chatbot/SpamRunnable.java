package me.in4.chatbot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpamRunnable extends BukkitRunnable {

    Player user;

    public SpamRunnable (Player player) {
        user = player;
    }

    @Override
    public void run() {
        user.setDisplayName("MrBeast");
        for (Player other : Bukkit.getOnlinePlayers()) {
            if (!user.equals(other)) {
                user.performCommand("say " + other.getName() + " I HAVE A CHALLENGE FOR ALL OF YOU:" +
                       " go to every person: you mom, your dad and INSTALL HONEY!");

            }
        }
    }
}
