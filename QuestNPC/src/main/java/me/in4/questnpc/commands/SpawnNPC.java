package me.in4.questnpc.commands;

import me.in4.questnpc.QuestNPC;
import me.in4.questnpc.managers.NPCManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnNPC implements CommandExecutor {

    private final NPCManager npcManager;

    public SpawnNPC(NPCManager npcManager) {
        this.npcManager = npcManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            npcManager.spawnGagloev(player);
        }
        return true;
    }
}
