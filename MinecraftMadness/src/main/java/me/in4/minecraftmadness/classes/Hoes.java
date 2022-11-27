package me.in4.minecraftmadness.classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class Hoes {

    public static ItemStack entity_copy_paster;
    public static ItemStack op_mobs_spawner;
    public static ItemStack teleporter_hoe;
    public static ItemStack lightning_hoe;

    public static void init() {
        entityCopyPaster();
        opMobsSpawner();
        teleporter();
        lightningHoe();
    }

    private static void entityCopyPaster() {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Entity Copy Paster");
        meta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_RED + "Hit an entity to copy ");
        lore.add(ChatColor.DARK_RED + "Right-click a block to paste.");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        item.setItemMeta(meta);
        entity_copy_paster = item;
    }
    private static void opMobsSpawner() {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Op Mob's Spawner");
        meta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "Right-click a block to spawn an op mob.");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        item.setItemMeta(meta);
        op_mobs_spawner = item;
    }
    private static void teleporter() {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Op Teleporter");
        meta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "Right-click to teleport.");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        item.setItemMeta(meta);
        teleporter_hoe = item;
    }
    private static void lightningHoe() {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Lightning Hoe");
        meta.setUnbreakable(true);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "Right-click to spawn lightning bolts around you.");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setLore(lore);
        item.setItemMeta(meta);
        lightning_hoe = item;
    }
}
