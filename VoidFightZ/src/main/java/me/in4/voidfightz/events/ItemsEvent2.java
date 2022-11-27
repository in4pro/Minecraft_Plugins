package me.in4.voidfightz.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Random;

public class ItemsEvent2 implements Listener {

    @EventHandler
    public void blockBreaker(BlockBreakEvent event) {
        Block lucky_block = event.getBlock();
        Player player = event.getPlayer();
        if (!(lucky_block.getType().equals(Material.SPONGE) || lucky_block.getType().equals(Material.WET_SPONGE))) {
            return;
        } else {
            Random random = new Random();
            Location loc = lucky_block.getLocation();
            //Material random_item = lucky_items[random.nextInt()];
            Material random_enchanted_item = lucky_enchanted_items[random.nextInt(lucky_enchanted_items.length)];
            //String occurrence = random_occurrences[random.nextInt()];
            if (Arrays.asList(lucky_enchanted_items).contains(random_enchanted_item)) {
                ItemStack lucky_item = new ItemStack(random_enchanted_item);
                if (lucky_item.getType().equals(Material.GOLDEN_SWORD)) {
                    lucky_item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 11);
                    event.setCancelled(true);
                    loc.getWorld().dropItemNaturally(loc, lucky_item);
                }
            }
        }
    }

    private Material[] lucky_enchanted_items = new Material[] {
            Material.NETHERITE_LEGGINGS, Material.IRON_BOOTS, Material.TRIDENT, Material.IRON_CHESTPLATE, Material.IRON_AXE, Material.DIAMOND_SWORD,
            Material.CROSSBOW, Material.GOLDEN_SWORD, Material.DIAMOND_PICKAXE
    };
}
