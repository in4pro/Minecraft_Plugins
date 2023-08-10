package me.in4.voidfightz.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PotionOfLevitation {

    public static ItemStack potion_of_levitation;

    public static void init() {
        createPotion();
    }

    private static void createPotion () {
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE  + "Potion Of Levitation");
        item.setItemMeta(meta);
        potion_of_levitation = item;
    }

}
