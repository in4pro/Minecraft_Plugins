package me.in4.voidfightz.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static me.in4.voidfightz.runnables.WorldsRunClass.spawn_loc;
import static me.in4.voidfightz.runnables.WorldsRunClass.world1;

public class PlatformRunnable extends BukkitRunnable {

    ArrayList<Location> platform_locs = new ArrayList<>();
    ArrayList<Location> platform_locs1 = new ArrayList<>();
    int num = 0;

    @Override
    public void run() {
        for (int dy = 0; dy <= 3; dy++) {
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    Location spawn_platform_loc = new Location(world1, spawn_loc.getBlockX() + dx, spawn_loc.getBlockY(), spawn_loc.getBlockZ() + dz);
                    Location spawn_platform_loc1 = new Location(world1, spawn_loc.getBlockX() + dx, spawn_loc.getBlockY() + dy, spawn_loc.getBlockZ() + dz);
                    if (num == 0) {
                        spawn_platform_loc.getBlock().setType(Material.PLAYER_HEAD);
                        num = 1;
                    }
                    if (!this.platform_locs.contains(spawn_platform_loc)) {
                        this.platform_locs.add(spawn_platform_loc);
                    }
                    this.platform_locs1.add(spawn_platform_loc1);
                }
            }
        }
        for (Location loc : this.platform_locs) {
            if (!loc.getBlock().getType().equals(Material.PLAYER_HEAD)) {
                loc.getBlock().setType(Material.PLAYER_HEAD);
            }
        }
        for (Location loc1 : this.platform_locs1) {
            if (!loc1.getBlock().getType().equals(Material.AIR)) {
                if (!this.platform_locs.contains(loc1)) {
                    loc1.getBlock().setType(Material.AIR);
                }
            }
        }
    }
}
