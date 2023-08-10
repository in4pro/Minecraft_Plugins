package me.in4.npc.commands;

import me.in4.npc.Npc;
import me.in4.npc.bots.Bot;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveBot implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            for (Bot mob : Npc.getMobs()) {
                mob.setHealth(0);
            }
        }

        return true;
    }
}
