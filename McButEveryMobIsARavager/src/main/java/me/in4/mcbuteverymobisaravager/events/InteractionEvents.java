package me.in4.mcbuteverymobisaravager.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

import static me.in4.mcbuteverymobisaravager.McButEveryMobIsARavager.plugin;

public class InteractionEvents implements Listener {

    private final HashMap<UUID, Long> cooldown;
    Fireball fireball;
    Ravager ravager;
    int ammo = 0;

    public InteractionEvents() {
        this.cooldown = new HashMap<>();
    }

    public void throwFireball (Location eye_loc, Vector direction) {
        Fireball fireball = eye_loc.getWorld().spawn(eye_loc, Fireball.class);
        this.fireball = fireball;
        fireball.setVelocity(direction.multiply(7));


    }
    @EventHandler
    public void projectileHit (ProjectileHitEvent event) {
        if (event.getEntity().getUniqueId() == this.fireball.getUniqueId()) {
            this.fireball.getWorld().createExplosion(this.fireball.getLocation(), 10F);
        }
    }

    @EventHandler
    public void kill (EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) {
            return;
        }
        if (event.getEntity().hasMetadata("isRavager")) {
            this.ammo++;
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        else {
            Location location = player.getLocation();
            Location eye_loc = player.getEyeLocation();
            Vector direction = location.getDirection().normalize();

            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD
                    + "" + ChatColor.BOLD + "Sniper Gun")) {

                if (!(this.cooldown.containsKey(player.getUniqueId()))) {
                    this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    throwFireball(eye_loc, direction);

                }
                else {
                    long timeElapsed = System.currentTimeMillis() - this.cooldown.get(player.getUniqueId());
                    if (timeElapsed >= 5000) {
                        this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                        throwFireball(eye_loc, direction);

                    }
                }

            }
            else if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(
                    ChatColor.GRAY + "" + ChatColor.BOLD + "Ravager Cannon")) {
                if (this.ammo >= 1) {
                    Ravager ravager = (Ravager) player.getWorld().spawnEntity(eye_loc, EntityType.RAVAGER);
                    ravager.setMetadata("isAmmoRavager", new FixedMetadataValue(plugin, Boolean.valueOf(true)));
                    ravager.setVelocity(direction);

                }
            }
        }
    }

    /*@EventHandler
    public void playerMove(PlayerMoveEvent event) {
        if (this.ravager == null) {
            return;
        }
        else if (this.ravager.hasMetadata("isAmmoRavager")) {
            Location location = this.ravager.getLocation();
            for (int cnt = 0; cnt <= 3; cnt++) {
                int x_block = location.getBlockX() - cnt;
                int x_block1 = location.getBlockX() + cnt;
                int y_block = location.getBlockY() - cnt;
                int y_block1 = location.getBlockY() + cnt;
                int z_block = location.getBlockZ() - cnt;
                int z_block1 = location.getBlockZ() + cnt;
                Location b_loc = new Location(location.getWorld(), x_block, y_block, z_block);
                Location b_loc1 = new Location(location.getWorld(), x_block1, y_block1, z_block1);
                b_loc.getBlock().setType(Material.AIR);
                b_loc1.getBlock().setType(Material.AIR);
            }
        }
    }*/

}
