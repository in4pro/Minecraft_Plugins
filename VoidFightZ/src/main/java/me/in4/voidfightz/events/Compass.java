package me.in4.voidfightz.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Compass {
    public static ItemStack tracking_compass;

    public static void init () {
        createTrackingCompass();
    }
    private static void createTrackingCompass() {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Closest Player");
        compass.setItemMeta(meta);
        tracking_compass = compass;
    }
}
