package me.in4.voidfightz.runnables;

import me.in4.voidfightz.events.ItemsEvents;
import me.in4.voidfightz.events.Kills;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

import static me.in4.voidfightz.VoidFightZ.plugin;
import static me.in4.voidfightz.runnables.WorldsRunClass.spawn_loc;
import static me.in4.voidfightz.runnables.WorldsRunClass.world1;

public class EndGameRunnable extends BukkitRunnable {

    ItemsEvents items_e;
    Kills kills_e;
    public EndGameRunnable (ItemsEvents events, Kills kills) {
        this.items_e = events;
        this.kills_e = kills;
    }

    @Override
    public void run() {
        Location location = Bukkit.getWorlds().get(0).getSpawnLocation();
        for (Player player : world1.getPlayers()) {
            player.teleport(location);
        }
        Bukkit.unloadWorld("VoidWorld", false);
        //world1.getWorldFolder().deleteOnExit();
        deleteWorld(world1.getWorldFolder());
        new WorldsRunClass(this.items_e, this.kills_e).runTaskLater(plugin, 1);

    }
    public void deleteWorld(File path) {
        if(path.exists()) {
            File files[] = path.listFiles();
            for(int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteWorld(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
    }
}
