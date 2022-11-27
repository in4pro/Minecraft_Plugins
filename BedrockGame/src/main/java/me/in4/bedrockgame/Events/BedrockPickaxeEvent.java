package me.in4.bedrockgame.Events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import java.util.Arrays;

public class BedrockPickaxeEvent implements Listener {

    @EventHandler
    public void Drop(PlayerInteractEvent event) {
        Player player = (Player) event.getPlayer();
        Action act = event.getAction();

        int xp = player.getLevel();
        Block block = (Block) event.getClickedBlock();
        Material material = (Material) block.getType();
        Location loc = block.getLocation();
        World world = block.getWorld();
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Bedrock pickaxe!");
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();

        if (act == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
                if (!material.equals(Material.BEDROCK)){
                    Material to_check = material;
                    double needed_xp = xp;
                    if (Arrays.asList(prestige).contains(to_check)) {
                        needed_xp = 2;
                    }
                    else if (Arrays.asList(middle).contains(to_check)) {
                        needed_xp = 1;
                    }
                    else if (Arrays.asList(trash).contains(to_check)) {
                        needed_xp = 0.5;
                    }
                    else {
                        needed_xp = 0;
                    }
                    if (xp >= needed_xp) {
                        Random randomGen = new Random();
                        int locX = randomGen.nextInt((x + 5 - (x - 5)) + 1) + (x - 5);
                        int locY = randomGen.nextInt((y + 5 - (y - 3)) + 1) + (y - 3);
                        int locZ = randomGen.nextInt((z + 5 - (z - 5)) + 1) + (z - 5);
                        Location location = new Location(world, locX, locY, locZ);
                        location.getBlock().setType(material);
                        player.setLevel((int) (xp - needed_xp));
                    }
                    else if (xp < needed_xp) {
                        player.sendMessage(ChatColor.DARK_RED + "You need more xp!");
                    }
                }
                else {
                    player.sendMessage(ChatColor.DARK_RED + "You can't dupe bedrock!");
                }

            }
        }
        if (act == Action.LEFT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
                if (material.equals(Material.BEDROCK)) {
                    if (xp >= 5) {
                        world.dropItemNaturally(loc, new ItemStack(Material.BEDROCK));
                        block.setBlockData(Material.AIR.createBlockData());
                        player.setLevel(xp - 5);

                    } else if (xp < 5) {
                        player.sendMessage(ChatColor.DARK_RED + "You need more xp!");
                    }
                }
            }
        }
    }
    private Material[] prestige = new Material[] {
            Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.DIAMOND_BLOCK, Material.OBSIDIAN, Material.ANCIENT_DEBRIS,
            Material.NETHERITE_BLOCK, Material.LAPIS_BLOCK, Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE, Material.EMERALD_ORE,
            Material.DEEPSLATE_EMERALD_ORE, Material.EMERALD_BLOCK
    };
    private Material[] middle = new Material[] {
            Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE, Material.IRON_BLOCK, Material.RAW_IRON_BLOCK, Material.GOLD_BLOCK, Material.GOLD_ORE,
            Material.RAW_GOLD_BLOCK, Material.DEEPSLATE_GOLD_ORE, Material.ANVIL, Material.ENCHANTING_TABLE
    };
    private Material[] trash = new Material[] {
            Material.COAL_BLOCK, Material.COAL_ORE, Material.REDSTONE_ORE, Material.REDSTONE_BLOCK, Material.DEEPSLATE_REDSTONE_ORE,
            Material.DEEPSLATE_COAL_ORE, Material.COPPER_BLOCK, Material.DEEPSLATE_COPPER_ORE, Material.COPPER_ORE, Material.RAW_COPPER_BLOCK
    };
}

