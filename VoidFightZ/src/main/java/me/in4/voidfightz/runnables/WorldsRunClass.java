package me.in4.voidfightz.runnables;

import me.in4.voidfightz.events.ItemsEvents;
import me.in4.voidfightz.events.Kills;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Random;

import static me.in4.voidfightz.VoidFightZ.plugin;

public class WorldsRunClass extends BukkitRunnable {

    public static Location spawn_loc;
    public static World world1;
    ItemsEvents items_event;
    Kills kill;

    public WorldsRunClass (ItemsEvents events, Kills kills) {
        this.items_event = events;
        this.kill = kills;
    }

    @Override
    public void run() {
        World world = Bukkit.createWorld(new WorldCreator("VoidWorld"));
        world1 = world;
        assert world != null;
        world.setSpawnLocation(0, world.getSpawnLocation().getBlockY(), 0);
        spawn_loc = world.getSpawnLocation();

        world.getWorldBorder().setSize(100.0);
        world.setGameRule(GameRule.KEEP_INVENTORY, true);
        world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        new PlatformRunnable().runTaskLater(plugin, 1);

        Random random = new Random();
        for (int y = -64; y <= 320; y++) {
            for (int x = spawn_loc.getBlockX() - 50; x <= spawn_loc.getBlockX() + 50; x++) {
                for (int z = spawn_loc.getBlockZ() - 50; z <= spawn_loc.getBlockZ() + 50; z++) {
                    int value = random.nextInt(360) + 1;
                    Location lucky_loc = new Location(world, x, y, z);
                    if (Arrays.asList(blocks).contains(lucky_loc.getBlock().getType())) {
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
        RunClass runClass = new RunClass(0, 100.0, this.items_event, this.kill);
        runClass.runTaskLater(plugin, 1);
    }
    private Material[] blocks = new Material[] {
        Material.STONE, Material.DEEPSLATE, Material.TUFF, Material.GRANITE, Material.ANDESITE, Material.DIORITE
    };

}
