package me.in4.minecraftmadness.classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpawnEggs {

    public static ItemStack wither_spawn_egg;
    public static ItemStack iron_golem_spawn_egg;
    public static ItemStack ender_dragon_spawn_egg;
    public static ItemStack king_skeleton_spawn_egg;

    public static void init() {
        witherSpawnEgg();
        ironGolemEgg();
        enderDragonSpawnEgg();
        kingSkeletonSpawnEgg();
    }

    private static void witherSpawnEgg() {
        ItemStack item = new ItemStack(Material.WITHER_SKELETON_SPAWN_EGG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Wither Spawn Egg");
        item.setItemMeta(meta);
        wither_spawn_egg = item;
    }
    private static void ironGolemEgg() {
        ItemStack item = new ItemStack(Material.GHAST_SPAWN_EGG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Iron Golem Spawn Egg");
        item.setItemMeta(meta);
        iron_golem_spawn_egg = item;
    }
    private static void enderDragonSpawnEgg() {
        ItemStack item = new ItemStack(Material.ENDERMAN_SPAWN_EGG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Ender Dragon Spawn Egg");
        item.setItemMeta(meta);
        ender_dragon_spawn_egg = item;
    }
    private static void kingSkeletonSpawnEgg() {
        ItemStack item = new ItemStack(Material.SKELETON_SPAWN_EGG);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "King Skeleton Spawn Egg");
        item.setItemMeta(meta);
        king_skeleton_spawn_egg = item;
    }
}
