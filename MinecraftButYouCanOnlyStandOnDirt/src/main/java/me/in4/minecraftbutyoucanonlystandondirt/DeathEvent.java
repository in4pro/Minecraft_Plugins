package me.in4.minecraftbutyoucanonlystandondirt;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.abs;


public class DeathEvent implements Listener {

    @EventHandler
    public void onMove (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        Location block_loc = new Location(player.getWorld(), location.getBlockX(), location.getBlockY() - 1, location.getBlockZ());
        Block block = block_loc.getBlock();
        Location anotha_loc = player.getWorld().getSpawnLocation();
        Location spawn_loc = new Location(player.getWorld(), anotha_loc.getBlockX(), anotha_loc.getBlockY(), anotha_loc.getBlockZ());
        Location p_spawn_loc = new Location(player.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
        if (!(this.placed_blocks.contains(block)) && block.getType() != Material.AIR && block.getType() != Material.WATER && block.getType() != Material.LAVA) {
            if (abs(spawn_loc.getBlockX() - p_spawn_loc.getBlockX()) >= 1 && abs(spawn_loc.getBlockZ() - p_spawn_loc.getBlockZ()) >= 1) {
                player.setHealth(0);

            }
        }
    }

    @EventHandler
    public void onRespawn (PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        event.setRespawnLocation(player.getWorld().getSpawnLocation());
    }

    @EventHandler
    public void onJoin (PlayerJoinEvent event) {
        event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
        event.getPlayer().setBedSpawnLocation(event.getPlayer().getWorld().getSpawnLocation());
    }

    @EventHandler
    public void onPlace (BlockPlaceEvent event) {
        this.placed_blocks.add(event.getBlockPlaced());
    }

    ArrayList<Block> placed_blocks = new ArrayList<>();
}
