package me.in4.changenamecommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ChangeYourName implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                player.setCustomName(args[0]);
                player.setCustomNameVisible(true);
                player.sendMessage(ChatColor.YELLOW + "Your name is now: " + args[0]);
            }
            else{
                player.sendMessage(ChatColor.RED + "You need to give one argument: /newname [argument]");
            }
        }

        return true;
    }



}
