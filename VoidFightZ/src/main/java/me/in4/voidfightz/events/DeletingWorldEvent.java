package me.in4.voidfightz.events;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import static me.in4.voidfightz.VoidFightZ.spawn_loc;
import static me.in4.voidfightz.events.Compass.tracking_compass;

public class DeletingWorldEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        else {
            Player player = event.getPlayer();
            if (event.getClickedBlock().getType().equals(Material.BEDROCK)) {
                if (Bukkit.getWorld("VoidWorld") == null) {
                    return;
                }
                else {
                    player.teleport(spawn_loc);
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (!(players.getWorld().getName().equalsIgnoreCase("VoidWorld"))) {
                            return;
                        } else {
                            players.getInventory().addItem(new ItemStack(Material.IRON_AXE));
                            players.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
                            players.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
                            players.getInventory().addItem(new ItemStack(Material.SHIELD));
                            players.getInventory().addItem(tracking_compass);
                            for (int cnt = 0; cnt < 16; cnt++) {
                                players.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT));
                            }
                        }
                    }
                }
            }

        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double distance = Double.POSITIVE_INFINITY;
        for (Entity target : player.getNearbyEntities(100, 1000, 100)) {
            if (target == null) {
                return;
            }
            else if (!(target instanceof Player)) {
                continue;
            }
            else {
                double closest_distance = target.getLocation().distance(player.getLocation());
                if (distance < closest_distance) {
                    continue;
                }
                else {
                    distance = closest_distance;
                    ((Player) target).setCompassTarget(target.getLocation());
                }
            }

        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        event.getPlayer().setGameMode(GameMode.SPECTATOR);
        event.getPlayer().teleport(spawn_loc);
    }

}
