package me.in4.bedrockgame.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

import static org.bukkit.inventory.ItemFlag.HIDE_ENCHANTS;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            GodPickaxe();
            BedrockPickaxe();
            BedrockSword();
            BedrockShovel();
            BedrockBoots();
            player.sendMessage("The game has started!");
        }

        return true;
    }

    private void GodPickaxe() {
        ItemStack god_pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        god_pickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, 6);
        god_pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 16);

        ItemMeta meta = god_pickaxe.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "God Pickaxe");
        meta.setUnbreakable(false);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Allows you to mine bedrock!!!");
        meta.setLore(lore);
        god_pickaxe.setItemMeta(meta);
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("godpickaxe"), god_pickaxe);
        sr.shape(new String[]{"OOO", " S ", " S "});
        sr.setIngredient('O', Material.OBSIDIAN);
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }
    private void BedrockPickaxe() {
        ItemStack bedrock_pickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
        bedrock_pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 7);
        bedrock_pickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        ItemMeta meta = bedrock_pickaxe.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Bedrock Pickaxe");
        meta.addItemFlags(HIDE_ENCHANTS);
        
        meta.setUnbreakable(false);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Bedrock pickaxe!");
        meta.setLore(lore);
        bedrock_pickaxe.setItemMeta(meta);
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("bedrockpickaxe"), bedrock_pickaxe);
        sr.shape(new String[]{"BBB", " S ", " S "});
        sr.setIngredient('B', Material.BEDROCK);
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }
    private void BedrockShovel() {
        ItemStack bedrock_shovel = new ItemStack(Material.NETHERITE_SHOVEL);
        bedrock_shovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, 7);
        bedrock_shovel.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        ItemMeta meta = bedrock_shovel.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Bedrock Shovel");
        meta.addItemFlags(HIDE_ENCHANTS);

        meta.setUnbreakable(false);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Bedrock sword!");
        meta.setLore(lore);
        bedrock_shovel.setItemMeta(meta);
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("bedrockshovel"), bedrock_shovel);
        sr.shape(new String[]{" B ", " S ", " S "});
        sr.setIngredient('B', Material.BEDROCK);
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }
    private void BedrockSword() {
        ItemStack bedrock_sword = new ItemStack(Material.NETHERITE_SWORD);
        bedrock_sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 7);
        bedrock_sword.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        ItemMeta meta = bedrock_sword.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Bedrock Sword");
        meta.addItemFlags(HIDE_ENCHANTS);

        meta.setUnbreakable(false);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Bedrock Sword!");
        meta.setLore(lore);
        bedrock_sword.setItemMeta(meta);
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("bedrocksword"), bedrock_sword);
        sr.shape(new String[]{" B ", " B ", " S "});
        sr.setIngredient('B', Material.BEDROCK);
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }
    private void BedrockBoots() {
        ItemStack bedrock_boots = new ItemStack(Material.NETHERITE_BOOTS);
        bedrock_boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        bedrock_boots.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        ItemMeta meta = bedrock_boots.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Bedrock Boots");
        meta.addItemFlags(HIDE_ENCHANTS);

        meta.setUnbreakable(false);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Bedrock Boots!");
        meta.setLore(lore);
        bedrock_boots.setItemMeta(meta);
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("bedrockboots"), bedrock_boots);
        sr.shape(new String[]{"   ", "B B", "B B"});
        sr.setIngredient('B', Material.BEDROCK);
        Bukkit.getServer().addRecipe(sr);
    }


}
