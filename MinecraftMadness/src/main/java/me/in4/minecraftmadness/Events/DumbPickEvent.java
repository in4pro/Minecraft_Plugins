package me.in4.minecraftmadness.Events;

import me.in4.minecraftmadness.classes.Hoes;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class DumbPickEvent implements Listener {

    @EventHandler
    public void pickEvent (BlockBreakEvent event) {
        if (event == null) {
            return;
        }
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location loc = block.getLocation();
        World world = block.getWorld();
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Op Hoes miner");
        if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
            Random random = new Random();
            int value = random.nextInt((100 - 1) + 1) + 1;
            if (value <= 36) {
                world.dropItemNaturally(loc, new ItemStack(Hoes.teleporter_hoe));
            }
            if (value <= 20) {
                world.dropItemNaturally(loc, new ItemStack(Hoes.entity_copy_paster));
            }
        }
    }
    private ItemStack[] hoes = new ItemStack[] {
            Hoes.teleporter_hoe, Hoes.op_mobs_spawner, Hoes.entity_copy_paster
    };

}
