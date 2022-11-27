package me.in4.minecraftmadness.Events;

import me.in4.minecraftmadness.classes.SpawnEggs;
import org.bukkit.Material;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BossesEvents implements Listener {

    private ItemStack[] spawn_eggs = new ItemStack[] {
            SpawnEggs.king_skeleton_spawn_egg, SpawnEggs.ender_dragon_spawn_egg, SpawnEggs.wither_spawn_egg, SpawnEggs.iron_golem_spawn_egg
    };

    @EventHandler
    public void gettingAdvancement(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        //player.sendMessage("l");
        Random random = new Random();
        ItemStack random_egg = spawn_eggs[random.nextInt(spawn_eggs.length)];
        if (random_egg != null) {
            player.getWorld().dropItemNaturally(player.getLocation(), SpawnEggs.king_skeleton_spawn_egg);
            //player.sendMessage(SpawnEggs.ender_dragon_spawn_egg + "");
        }
    }
}
