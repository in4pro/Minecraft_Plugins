package me.in4.minecraftmadness.commands;

import me.in4.minecraftmadness.classes.Hoes;
import me.in4.minecraftmadness.classes.SpawnEggs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            SuperHoe();
            DumbPick();
            NewIronPick();
            EnchantsChest();
            Hoes.init();
            SpawnEggs.init();


        }
        return true;
    }

    private void SuperHoe() {
        ItemStack super_hoe = new ItemStack(Material.WOODEN_HOE);
        super_hoe.addUnsafeEnchantment(Enchantment.DURABILITY, 36);

        ItemMeta meta = super_hoe.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Whore!");
        meta.setUnbreakable(false);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Where it all begins...");
        meta.setLore(lore);
        super_hoe.setItemMeta(meta);
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("superhoe"), super_hoe);
        sr.shape(new String[]{"LL ", " S ", " S "});
        sr.setIngredient('L', Material.OAK_LOG);
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }
    private void DumbPick() {
        ItemStack dumb_pick = new ItemStack(Material.STONE_PICKAXE);
        dumb_pick.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
        dumb_pick.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);

        ItemMeta meta = dumb_pick.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Cool pick");
        meta.setUnbreakable(false);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Op Hoes miner");
        meta.setLore(lore);
        dumb_pick.setItemMeta(meta);
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("dumbpick"), dumb_pick);
        sr.shape(new String[]{"FFF", " S ", " S "});
        sr.setIngredient('F', Material.FURNACE);
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }
    private void NewIronPick() {
        ItemStack advanced_pick = new ItemStack(Material.IRON_PICKAXE);
        advanced_pick.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
        advanced_pick.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);

        ItemMeta meta = advanced_pick.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "A better iron pickaxe");
        meta.setUnbreakable(false);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.DARK_AQUA + "Interesting crafting recipe");
        meta.setLore(lore);
        advanced_pick.setItemMeta(meta);
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("advancedpick"), advanced_pick);
        sr.shape(new String[]{"RRR", " S ", " S "});
        sr.setIngredient('R', Material.RAW_IRON_BLOCK);
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }
    private void EnchantsChest() {
        ItemStack xp_block = new ItemStack(Material.GOLD_BLOCK);
        xp_block.addUnsafeEnchantment(Enchantment.LUCK, 36);
        ItemMeta meta = xp_block.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Xp Block");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        xp_block.setItemMeta(meta);
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("xpblock"), xp_block);
        sr.shape(new String[]{"XXX", "XXX", "XXX"});
        sr.setIngredient('X', Material.EXPERIENCE_BOTTLE);

        ItemStack blaze_block = new ItemStack(Material.YELLOW_CONCRETE);
        ItemMeta meta1 = blaze_block.getItemMeta();
        meta1.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Blazerod Block");
        meta1.addEnchant(Enchantment.LUCK, 1, false);
        meta1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        blaze_block.setItemMeta(meta1);
        ShapedRecipe sr1 = new ShapedRecipe(NamespacedKey.minecraft("blazeblock"), blaze_block);
        sr1.shape(new String[]{"PPP", "PPP", "PPP"});
        sr1.setIngredient('P', Material.BLAZE_POWDER);

        ItemStack enchants_chest = new ItemStack(Material.CHEST);
        ItemMeta meta2 = enchants_chest.getItemMeta();
        meta2.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Chest-Enchanter");
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.DARK_GREEN + "Just place a weapon inside!");
        meta2.setLore(lore);
        enchants_chest.setItemMeta(meta2);
        ShapedRecipe sr2 = new ShapedRecipe(NamespacedKey.minecraft("chestenchater"), enchants_chest);
        sr2.shape(new String[]{"BPB", "BCB", "BPB"});
        sr2.setIngredient('C', Material.CHEST);
        sr2.setIngredient('B', new RecipeChoice.ExactChoice(xp_block));
        sr2.setIngredient('P', new RecipeChoice.ExactChoice(blaze_block));

        ItemStack op_items_chest = new ItemStack(Material.CHEST);
        ItemMeta meta3 = op_items_chest.getItemMeta();
        meta3.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Op Items Chest");
        ArrayList<String> lore1 = new ArrayList();
        lore1.add(ChatColor.DARK_RED + "Just open it...");
        meta3.setLore(lore1);
        op_items_chest.setItemMeta(meta3);
        ShapedRecipe sr3 = new ShapedRecipe(NamespacedKey.minecraft("opitemschest"), op_items_chest);
        sr3.shape(new String[]{"ROR", "OCO", "ROR"});
        sr3.setIngredient('C', Material.CHEST);
        sr3.setIngredient('O', Material.OBSIDIAN);
        sr3.setIngredient('R', new RecipeChoice.ExactChoice(blaze_block));
        Bukkit.getServer().addRecipe(sr);
        Bukkit.getServer().addRecipe(sr1);
        Bukkit.getServer().addRecipe(sr2);
        Bukkit.getServer().addRecipe(sr3);
    }
}
