package me.in4.mcbuteverymobisaravager.classes.items.weapons;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Guns {

    public static ItemStack sniper_gun;
    public static ItemStack ravager_cannon;

    public static void init() {
        createSniperGun();
        createRavagerCannon();
    }

    public static void createSniperGun() {
        ItemStack item = new ItemStack(Material.SPYGLASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Sniper Gun");
        item.setItemMeta(meta);
        sniper_gun = item;
    }

    public static void createRavagerCannon() {
        ItemStack item = new ItemStack(Material.SPYGLASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Ravager Cannon");
        item.setItemMeta(meta);
        ravager_cannon = item;
    }
}
