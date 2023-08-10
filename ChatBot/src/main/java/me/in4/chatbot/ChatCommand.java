package me.in4.chatbot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChatCommand implements CommandExecutor {

    ArrayList<String> names = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            String p_name = player.getDisplayName();
            if (args.length >= 2) {
                for (Player target : Bukkit.getOnlinePlayers()) {
                    if (!target.equals(player)) {
                        String name = target.getName();
                        names.add(name);
                        if (names.contains(args[0])) {
                            StringBuilder message = new StringBuilder();
                            for (int cnt = 1; cnt < args.length; cnt++) {
                                message.append(" ").append(args[cnt]);
                            }
                            target.sendMessage("<" + p_name + "> " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + message);
                        }
                    }
                }
            }
        }
        return true;
    }

}
