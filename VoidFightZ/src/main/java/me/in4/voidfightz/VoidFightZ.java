package me.in4.voidfightz;

import me.in4.voidfightz.events.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class VoidFightZ extends JavaPlugin {
    
    public static Location spawn_loc;
    public static World world1;
    public static VoidFightZ plugin;
    int counter = 0;
    int period = 40;

    @Override
    public void onEnable() {

        plugin = this;
        Compass.init();
        Bukkit.getServer().getPluginManager().registerEvents(new DeletingWorldEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemsEvents(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Kills(), this);

        World world = Bukkit.createWorld(new WorldCreator("VoidWorld"));
        world1 = world;
        Location spawn_loc1 = world.getSpawnLocation();
        spawn_loc = spawn_loc1;
        world.getWorldBorder().setSize(100.0);
        world.setGameRule(GameRule.KEEP_INVENTORY, true);
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

        if (world.getPlayers().size() == 0) {
            return;
        }
        else {

            new BukkitRunnable() {
                int count = 0;

                @Override
                public void run() {
                    int random_loc_x = ThreadLocalRandom.current().nextInt((int) (spawn_loc.getBlockX() - world.getWorldBorder().getSize()/2), (int) (spawn_loc.getBlockX() + world.getWorldBorder().getSize()/2 + 1));
                    int random_loc_z = ThreadLocalRandom.current().nextInt((int) (spawn_loc.getBlockZ() - world.getWorldBorder().getSize()/2), (int) (spawn_loc.getBlockZ() + world.getWorldBorder().getSize()/2 + 1));
                    for (int y = 320; y >= -64; y--) {
                        for (int dx = -1; dx <= 1; dx++) {
                            for (int dz = -1; dz <= 1; dz++) {
                                Location delete_loc = new Location(world, random_loc_x + dx, y, random_loc_z + dz);
                                delete_loc.getBlock().setType(Material.AIR);
                                if (this.count >= 300 && world.getWorldBorder().getSize() >= 75) {

                                }
                            }
                        }
                        this.count++;

                    }

                }
            }.runTaskTimer(plugin, 1, this.period);
            new BukkitRunnable() {
                int count = 0;
                double size = 100.0;
                @Override
                public void run() {
                    WorldBorder worldBorder = world.getWorldBorder();
                    Location spawn_loc1 = world.getSpawnLocation();
                    spawn_loc = spawn_loc1;
                    worldBorder.setCenter(spawn_loc);
                    if (this.count / 2 < 2) {
                        return;
                    }
                    if (this.count / 2 >= 3 && this.size > 75.0) {
                        worldBorder.setSize(this.size);
                    }
                    else if (this.count / 2 >= 7 && this.size <= 75) {
                        worldBorder.setSize(this.size);
                    }
                    else if (this.count / 2 >= 870 && this.size <= 50) {
                        worldBorder.setSize(this.size);
                    }
                    else if (this.count / 2 >= 900 && this.size > 10.0) {
                        worldBorder.setSize(this.size);
                    }
                    else if (this.size <= 10) {
                        cancel();
                    }
                    this.size -= 0.5;
                    this.count++;

                }
            }.runTaskTimer(plugin, 1, 10);

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                    this.counter++;
                }
                if (this.counter >= world.getPlayers().size()) {
                    deleteWorld(world.getWorldFolder());
                }
            }
        }
    }
    public boolean deleteWorld(File path) {
        if(path.exists()) {
            File files[] = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteWorld(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return true;
    }

}
