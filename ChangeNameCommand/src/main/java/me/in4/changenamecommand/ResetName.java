package me.in4.changenamecommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetName implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.setDisplayName(player.getName());

            player.sendMessage(ChatColor.YELLOW + "Your name has been reset!");
        }

        return true;
    }
}
