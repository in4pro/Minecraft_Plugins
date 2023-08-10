package me.in4.voidfightz.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Items {

    public static ItemStack TNT_BOW;
    public static ItemStack TP_BOW;
    public static ItemStack OP_CROSS_BOW;
    public static ItemStack CHEAT_SWORD;
    public static ItemStack DRAGON_HEAD;

    public static void init () {
        createTntBow();
        createTeleportBow();
        createOpCrossbow();
        createCheatSword();
        createDragonHead();
    }

    public static void createTntBow () {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "TNT Bow");
        meta.addEnchant(Enchantment.ARROW_INFINITE, 69, true);
        meta.setUnbreakable(true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "This bow shoots tnt");
        lore.add(ChatColor.GREEN + "which can be obtained from lucky blocks or kills");

        meta.setLore(lore);
        item.setItemMeta(meta);
        TNT_BOW = item;
    }

    public static void createTeleportBow () {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Teleport Bow");
        meta.addEnchant(Enchantment.ARROW_INFINITE, 69, true);
        meta.addEnchant(Enchantment.QUICK_CHARGE, 3, true);
        meta.setUnbreakable(true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "This bow lets you teleport where");
        lore.add(ChatColor.GREEN + "the arrow that you shot has landed (requires e-pearls)");

        meta.setLore(lore);
        item.setItemMeta(meta);
        TP_BOW = item;
    }

    public static void createOpCrossbow() {
        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Cheat Crossbow");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "This crossbow shoots an arrow that follows the closest player");
        meta.addEnchant(Enchantment.ARROW_INFINITE, 69, true);
        meta.addEnchant(Enchantment.QUICK_CHARGE, 3, true);

        item.setItemMeta(meta);
        OP_CROSS_BOW = item;
    }

    public static void createCheatSword () {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Cheat Sword");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "Right click in order to dash to a desired location (16 blocks max)");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 9, true);
        meta.addEnchant(Enchantment.DURABILITY, 3, true);

        item.setItemMeta(meta);
        CHEAT_SWORD = item;
    }

    public static void createDragonHead () {
        ItemStack item = new ItemStack(Material.DRAGON_HEAD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Dragon Head");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "Sneak to shoot dragon breath");
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);

        item.setItemMeta(meta);
        DRAGON_HEAD = item;
    }
}
