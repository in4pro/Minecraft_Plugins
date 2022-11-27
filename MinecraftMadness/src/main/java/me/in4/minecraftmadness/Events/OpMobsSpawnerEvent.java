package me.in4.minecraftmadness.Events;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class OpMobsSpawnerEvent implements Listener {

    @EventHandler
    public void onSpawn(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();
            Location loc = block.getLocation();
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "Right-click a block to spawn an op mob.");
            if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
                Creature entity = (Creature) Bukkit.getWorld("world").spawnEntity(loc, EntityType.ZOMBIE);
                entity.setCustomName(ChatColor.GOLD + "" + ChatColor.BOLD + "Strong guy");
                ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
                item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1000);
                entity.getEquipment().setItemInMainHand(item);
                entity.getEquipment().setItemInMainHandDropChance(0.1F);
                entity.setMaxHealth(36.0);
                entity.setHealth(36.0);
                entity.setCanPickupItems(true);
            }
        }
    }
}
