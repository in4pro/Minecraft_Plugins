package me.in4.chatbot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static me.in4.chatbot.ChatBot.plugin;

public class SpamCommand implements CommandExecutor {

    SpamRunnable spamRunnable;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!spammers.contains(player)) {
                spamRunnable = new SpamRunnable(player);
                spamRunnable.runTaskTimer(plugin, 1, 200);
                spammers.add(player);
            }
            else {
                spammers.remove(player);
                spamRunnable.cancel();
            }
        }

        return true;
    }

    ArrayList<Player> spammers = new ArrayList<>();

}
