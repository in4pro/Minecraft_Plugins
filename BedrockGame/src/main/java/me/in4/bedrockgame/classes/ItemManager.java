package me.in4.bedrockgame.classes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {

    public static ItemStack god_pick;

    public static void init() {
        craftPick();
    }

    private static void craftPick() {
        ItemStack god_pick = new ItemStack(Material.DIAMOND_PICKAXE);
        god_pick.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
        god_pick.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);

        ItemMeta meta = god_pick.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "God Pickaxe");
        meta.setUnbreakable(false);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Lets you mine bedrock!!!!");
        meta.setLore(lore);
        god_pick.setItemMeta(meta);
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("godpickaxe"), god_pick);
        sr.shape(new String[]{"DDD", " S ", " S "});
        sr.setIngredient('D', Material.DIAMOND_BLOCK);
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

}
