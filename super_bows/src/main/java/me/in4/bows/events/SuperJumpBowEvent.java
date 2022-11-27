package me.in4.bows.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class SuperJumpBowEvent implements Listener {

    @EventHandler
    public void onProjectileHit (ProjectileHitEvent event) {
        Arrow arrow = (Arrow) event.getEntity();
        Player player = (Player) arrow.getShooter();
        Block block = (Block) arrow.getLocation().getBlock();
        Location loc = (Location) player.getLocation();
        ArrayList<String> super_jump_bow_lore = new ArrayList<>();
        super_jump_bow_lore.add(ChatColor.YELLOW + "This bow allows you to jump high!");
        if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(super_jump_bow_lore)) {
            if (loc.getX() - block.getX() < 1 & loc.getY() - block.getY() < 1 & loc.getZ() - block.getZ() < 1) {
                player.setVelocity(new Vector(0, 36, 0));
                player.playSound(player.getLocation(), Sound.BLOCK_SLIME_BLOCK_HIT, 1.0F, 1.0F);
            }
            else if (loc.getX() - block.getX() > 1 & loc.getZ() - block.getZ() > 1) {
                player.setVelocity(new Vector(0, 0, 0));
            }
        }

    }
}
