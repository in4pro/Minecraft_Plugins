package me.in4.voidfightz.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static me.in4.voidfightz.runnables.WorldsRunClass.world1;

public class InvulnerableCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender instanceof Player)) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            location.setYaw(100F);
            location.setPitch(100F);
            player.teleport(location);
        }
        return true;
    }
}
