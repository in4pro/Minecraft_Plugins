package me.in4.minecraftmadness.Events;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class IronPickEvent implements Listener {

    int useful_event = 7;
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
        lore.add(ChatColor.DARK_AQUA + "Interesting crafting recipe");

        if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
            if (block.getType().equals(Material.DIAMOND_ORE) || block.getType().equals(Material.DEEPSLATE_DIAMOND_ORE)) {
                Random random = new Random();
                int value = random.nextInt((100 - 1) + 1) + 1;

                if (value < 15) {
                    world.dropItemNaturally(loc, new ItemStack(Material.NETHERITE_SCRAP));
                }
                else if (value < 20) {
                    world.dropItemNaturally(loc, new ItemStack(Material.ANCIENT_DEBRIS));
                }
                else if (value < 10) {
                    world.dropItemNaturally(loc, new ItemStack(Material.GOLD_BLOCK));
                    world.dropItemNaturally(loc, new ItemStack(Material.NETHERITE_SCRAP));
                }
                else if (value < 25) {
                    world.dropItemNaturally(loc, new ItemStack(Material.DIAMOND_BLOCK));
                    world.dropItemNaturally(loc, new ItemStack(Material.OBSIDIAN));
                }
                else if (value < 30) {
                    world.dropItemNaturally(loc, new ItemStack(Material.DIAMOND_BLOCK));
                }
                else if (value < 5) {
                    world.dropItemNaturally(loc, new ItemStack(Material.NETHERITE_INGOT));
                }
            }
            else if (block.getType().equals(Material.IRON_ORE) || block.getType().equals(Material.DEEPSLATE_IRON_ORE)) {
                this.useful_event = 9;
                world.dropItemNaturally(loc, new ItemStack(Material.IRON_BLOCK));
                world.dropItemNaturally(loc, new ItemStack(Material.EXPERIENCE_BOTTLE));

            }
            else {
                Random random = new Random();
                int value = random.nextInt((10 - 1) + 1) + 1;
                this.useful_event = value;
                Material r_tools = tools[random.nextInt(tools.length)];
                if (value > 3) {
                    world.dropItemNaturally(loc, new ItemStack(r_tools));
                }
            }
        }

    }
    @EventHandler
    public void drop (BlockDropItemEvent event) {
        if (event == null) {
            return;
        }
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.DARK_AQUA + "Interesting crafting recipe");
        if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
            int value = this.useful_event;
            if (value > 3) {
                event.setCancelled(true);
            }
            else {
                event.setCancelled(false);
            }
        }
    }

    private Material[] tools = new Material[] {
            Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_SWORD, Material.DIAMOND_HOE, Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS
    };

    private Material[] cool_blocks = new Material[] {
            Material.RAW_IRON_BLOCK, Material.IRON_ORE, Material.IRON_BLOCK, Material.DEEPSLATE_IRON_ORE, Material.COAL_ORE, Material.COAL_BLOCK
    };
}
