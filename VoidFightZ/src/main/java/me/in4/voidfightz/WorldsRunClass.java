package me.in4.voidfightz;

import me.in4.voidfightz.events.RunClass;
import me.in4.voidfightz.events.RunClass2;
import me.in4.voidfightz.events.RunClass3;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static me.in4.voidfightz.VoidFightZ.plugin;

public class WorldsRunClass extends BukkitRunnable {

    public static Location spawn_loc;
    public static World world1;

    @Override
    public void run() {
        World world = Bukkit.createWorld(new WorldCreator("VoidWorld"));
        world1 = world;
        world.setSpawnLocation(0, world.getSpawnLocation().getBlockY(), 0);
        Location spawn_loc1 = world.getSpawnLocation();
        spawn_loc = spawn_loc1;

        world.getWorldBorder().setSize(100.0);
        world.setGameRule(GameRule.KEEP_INVENTORY, true);

        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                Location spawn_platform_loc = new Location(world, spawn_loc1.getBlockX() + dx, spawn_loc1.getBlockY(), spawn_loc1.getBlockZ() + dz);
                spawn_platform_loc.getBlock().setType(Material.PLAYER_HEAD);
            }
        }

        Random random = new Random();
        for (int y = -64; y <= 56; y++) {
            for (int x = spawn_loc.getBlockX() - 50; x <= spawn_loc.getBlockX() + 50; x++) {
                for (int z = spawn_loc.getBlockZ() - 50; z <= spawn_loc.getBlockZ() + 50; z++) {
                    int value = random.nextInt(360) + 1;
                    Location lucky_loc = new Location(world, x, y, z);
                    if (lucky_loc.getBlock().getType().equals(Material.STONE) || lucky_loc.getBlock().getType().equals(Material.DEEPSLATE)) {
                        if (y == 36 || y == -36) {
                            if (value >= 336) {
                                lucky_loc.getBlock().setType(Material.SPONGE);
                            }
                        } else {
                            if (value <= 5) {
                                lucky_loc.getBlock().setType(Material.SPONGE);
                            }
                        }
                    }
                }
            }
        }
        RunClass runClass = new RunClass(0, 100.0);
        runClass.runTaskLater(plugin, 1);

    }

}
