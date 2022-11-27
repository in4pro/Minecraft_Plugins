package me.in4.bows.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BowsCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            TNTBow();
            SuperJumpBow();
            EnchantBow();
            XRayBow();
            DashBow();

            player.sendMessage("You can now craft your bows");
        }

        return true;
    }

    private void TNTBow() {
        ItemStack tnt_bow = new ItemStack(Material.BOW);
        tnt_bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 3636);
        ItemMeta meta = tnt_bow.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "TNT BOW");
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "This bow shoots tnt!");
        meta.setLore(lore);
        tnt_bow.setItemMeta(meta);
        ShapedRecipe sr1 = new ShapedRecipe(NamespacedKey.minecraft("tntbow"), tnt_bow);
        sr1.shape(new String[]{"TSS", "TBS", "TTS"});
        sr1.setIngredient('T', Material.TNT);
        sr1.setIngredient('S', Material.STRING);
        sr1.setIngredient('B', Material.BOW);
        Bukkit.getServer().addRecipe(sr1);
    }
    private void SuperJumpBow() {
        ItemStack super_jump_bow = new ItemStack(Material.BOW);
        super_jump_bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 3636);
        ItemMeta meta = super_jump_bow.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Super Jump Bow");
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "This bow allows you to jump high!");
        meta.setLore(lore);
        super_jump_bow.setItemMeta(meta);
        ShapedRecipe sr2 = new ShapedRecipe(NamespacedKey.minecraft("superjumpbow"), super_jump_bow);
        sr2.shape(new String[]{"RSS", "RBS", "RRS"});
        sr2.setIngredient('R', Material.RABBIT_FOOT);
        sr2.setIngredient('S', Material.STRING);
        sr2.setIngredient('B', Material.BOW);
        Bukkit.getServer().addRecipe(sr2);
    }
    private void EnchantBow() {
        ItemStack enchant_bow = new ItemStack(Material.BOW);
        enchant_bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 3636);
        ItemMeta meta = enchant_bow.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Enchant Bow");
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "This bow gives you random enchanted item!");
        meta.setLore(lore);
        enchant_bow.setItemMeta(meta);
        ShapedRecipe sr3 = new ShapedRecipe(NamespacedKey.minecraft("enchantbow"), enchant_bow);
        sr3.shape(new String[]{"LSS", "LBS", "LLS"});
        sr3.setIngredient('L', Material.LAPIS_LAZULI);
        sr3.setIngredient('S', Material.STRING);
        sr3.setIngredient('B', Material.BOW);
        Bukkit.getServer().addRecipe(sr3);
    }
    private void XRayBow() {
        ItemStack enchant_bow = new ItemStack(Material.BOW);
        enchant_bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 3636);
        ItemMeta meta = enchant_bow.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "X-ray Bow");
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "This bow allows you to x-ray!");
        meta.setLore(lore);
        enchant_bow.setItemMeta(meta);
        ShapedRecipe sr4 = new ShapedRecipe(NamespacedKey.minecraft("xraybow"), enchant_bow);
        sr4.shape(new String[]{"GSS", "GBS", "GGS"});
        sr4.setIngredient('G', Material.GLASS);
        sr4.setIngredient('S', Material.STRING);
        sr4.setIngredient('B', Material.BOW);
        Bukkit.getServer().addRecipe(sr4);
    }
    private void  DashBow() {
        ItemStack enchant_bow = new ItemStack(Material.BOW);
        enchant_bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 3636);
        ItemMeta meta = enchant_bow.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Dash Bow");
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "This bow allows you to dash and do damage to mobs/players!");
        meta.setLore(lore);
        enchant_bow.setItemMeta(meta);
        ShapedRecipe sr5 = new ShapedRecipe(NamespacedKey.minecraft("dashbow"), enchant_bow);
        sr5.shape(new String[]{"RSS", "RBS", "RRS"});
        sr5.setIngredient('R', Material.BLAZE_ROD);
        sr5.setIngredient('S', Material.STRING);
        sr5.setIngredient('B', Material.BOW);
        Bukkit.getServer().addRecipe(sr5);
    }
}
