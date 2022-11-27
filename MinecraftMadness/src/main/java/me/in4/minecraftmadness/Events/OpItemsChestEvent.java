package me.in4.minecraftmadness.Events;

import me.in4.minecraftmadness.commands.Commands;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class OpItemsChestEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block.getType().equals(Material.CHEST)) {
                Chest chest = (Chest) block.getState();

                if (chest.getCustomName().equalsIgnoreCase(ChatColor.YELLOW + "" + ChatColor.BOLD + "Op Items Chest")) {
                    Inventory inv = chest.getBlockInventory();
                    Random random = new Random();
                    Random random1 = new Random();
                    int value = random1.nextInt((36 - 1) + 1) + 1;
                    Material random_mat = op_items[random.nextInt(op_items.length)];
                    ItemStack item = new ItemStack(random_mat);
                    for (int cnt = 0; cnt <= value; cnt++) {
                        inv.addItem(item);
                        if (item.getMaxStackSize() <= value) {
                            break;
                        }
                    }
                }
            }
        }
    }

    private Material[] op_items = new Material[] {
            Material.ELYTRA, Material.OBSIDIAN, Material.FIREWORK_ROCKET, Material.TOTEM_OF_UNDYING, Material.EMERALD_BLOCK, Material.GOLDEN_CARROT,
            Material.BOOKSHELF, Material.TRIDENT, Material.AXOLOTL_BUCKET, Material.ANCIENT_DEBRIS, Material.COOKED_BEEF,
            Material.NETHERITE_HOE, Material.FILLED_MAP, Material.DIAMOND, Material.GOLD_INGOT, Material.SHULKER_BOX, Material.TNT, Material.ARROW,
            Material.ENDER_PEARL, Material.ENDER_CHEST, Material.SADDLE, Material.WARPED_FUNGUS_ON_A_STICK, Material.BOW, Material.CROSSBOW
    };

}
