package me.in4.minecraftmadness.Events;

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
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Random;

public class SuperHoeEvent implements Listener {

    @EventHandler
    public void interact (PlayerInteractEvent event) {
        if (event == null) {
            return;
        }
        Player player = (Player) event.getPlayer();
        Action act = event.getAction();
        Block block = (Block) event.getClickedBlock();
        Location loc = (Location) block.getLocation();
        World world = (World) block.getWorld();
        Material material = (Material) block.getType();

        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Where it all begins...");

        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();

        if (act == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
                if (material.equals(Material.DIRT) || material.equals(Material.GRASS_BLOCK)) {
                    Random randomGen = new Random();
                    Random random = new Random();
                    int locX = randomGen.nextInt((x + 2 - (x - 2)) + 1) + (x - 2);
                    int locY = randomGen.nextInt((y + 2 - (y)) + 1) + (y);
                    int locZ = randomGen.nextInt((z + 2 - (z - 2)) + 1) + (z - 2);
                    Location location = new Location(world, locX, locY, locZ);
                    Material random_blocks = blocks[random.nextInt(blocks.length)];
                    location.getBlock().setType(random_blocks);

                }
            }
        }

    }
    private Material[] blocks = new Material[] {
            Material.IRON_ORE, Material.COPPER_BLOCK, Material.IRON_BLOCK, Material.ANVIL, Material.DIAMOND_ORE,
            Material.OBSIDIAN, Material.COPPER_ORE, Material.COAL_BLOCK, Material.COPPER_ORE, Material.COAL_ORE, Material.ENCHANTING_TABLE,
            Material.RAW_IRON_BLOCK, Material.GOLD_BLOCK, Material.GOLD_ORE, Material.AMETHYST_BLOCK, Material.AMETHYST_CLUSTER, Material.AMETHYST_SHARD,
            Material.BOOKSHELF, Material.BLAST_FURNACE, Material.SHULKER_BOX, Material.NETHERITE_BLOCK, Material.ANCIENT_DEBRIS, Material.BEDROCK,
            Material.LAPIS_ORE, Material.LAPIS_BLOCK, Material.DEEPSLATE_IRON_ORE
    };
}
