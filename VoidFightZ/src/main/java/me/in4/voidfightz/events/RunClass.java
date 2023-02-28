package me.in4.voidfightz.events;

import me.in4.voidfightz.WorldsRunClass;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import static me.in4.voidfightz.VoidFightZ.*;
import static me.in4.voidfightz.WorldsRunClass.spawn_loc;
import static me.in4.voidfightz.WorldsRunClass.world1;
import static me.in4.voidfightz.commands.QueueCommand.main_location;


public class RunClass extends BukkitRunnable {

    int next_time = 40;
    int count;
    double size;

    ArrayList<Player> alive_players = new ArrayList<Player>();
    ArrayList<Player> dead_players = new ArrayList<Player>();

    public RunClass (int count, double size) {
        this.count = count;
        this.size = size;
    }

    public void setPeriod (int time) {
        this.next_time = time;
    }

    public int getPeriod () {
        return this.next_time;
    }

    @Override
    public void run() {
        if (world1.getPlayers().size() == 0) {
            new RunClass(this.count, this.size).runTaskLater(plugin, this.getPeriod());
        }

        else {
            int random_loc_x = ThreadLocalRandom.current().nextInt((int) (spawn_loc.getBlockX() - world1.getWorldBorder().getSize() / 2), (int) (spawn_loc.getBlockX() + world1.getWorldBorder().getSize() / 2 + 1));
            int random_loc_z = ThreadLocalRandom.current().nextInt((int) (spawn_loc.getBlockZ() - world1.getWorldBorder().getSize() / 2), (int) (spawn_loc.getBlockZ() + world1.getWorldBorder().getSize() / 2 + 1));
            for (int y = 320; y >= -64; y--) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        Location delete_loc = new Location(world1, random_loc_x + dx, y, random_loc_z + dz);
                        if (delete_loc.getBlock().getType().equals(Material.PLAYER_HEAD)) {
                            continue;
                        } else {
                            delete_loc.getBlock().setType(Material.AIR);
                        }
                    }
                }
            }
            if (world1.getPlayers().size() >= 5) {
                for (Player player : world1.getPlayers()) {
                    if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                        this.alive_players.add(player);
                    }
                    if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                        this.dead_players.add(player);
                    }

                    if (this.alive_players.size() == 1 && this.dead_players.size() != 0) {
                        player.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "VoidFightZ >>>    " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + ChatColor.MAGIC + "POSOSI " +
                                ChatColor.GOLD + "" + ChatColor.BOLD + "" + this.alive_players.get(0).getName() + " HAS WON THE GAME!! LETS FUCKING GOOOOOOOOO!!!!" +
                                ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + ChatColor.MAGIC + " ISOSOP");
                        player.teleport(new Location(main_location.getWorld(), main_location.getX(), main_location.getY(), main_location.getZ()));
                        Bukkit.unloadWorld("VoidWorld", false);
                        this.cancel();
                        this.count = 1301;
                        world1.getWorldFolder().deleteOnExit();
                        deleteWorld(world1.getWorldFolder());
                        player.getInventory().clear();
                        new WorldsRunClass().runTaskLater(plugin, 1);
                    }

                }
            }

            if (this.count >= 278 && this.count < 556) {
                this.setPeriod(20);
                world1.setGameRule(GameRule.KEEP_INVENTORY, false);

                if (this.count < 279) {
                    for (Player player : world1.getPlayers()) {
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You won't be able to respawn from now on! The world will begin to delete faster!");

                    }
                }
                if (this.size > 75.0) {
                    this.size -= 0.5;

                }
                else if (this.count > 417 && this.size > 50){
                    this.size -= 1;

                }
            }
            else if (this.count >= 556 && this.count < 1111) {
                this.setPeriod(10);
                if (this.size <= 50 && this.size > 25) {
                    this.size -= 0.5;
                }
            }
            else if (this.count >= 1111 && this.count < 1300) {
                this.setPeriod(5);
                if (this.size > 10 && this.count < 1250) {
                    this.size -= 0.5;
                }

            }
            if (this.count < 1300) {
                this.count++;
                world1.getWorldBorder().setSize(this.size);
                new RunClass(this.count, this.size).runTaskLater(plugin, this.getPeriod());

            }
            else {
                cancel();
            }

        }


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

