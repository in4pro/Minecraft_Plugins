package me.in4.voidfightz.runnables;

import me.in4.voidfightz.Leaderboard;
import me.in4.voidfightz.VoidFightZ;
import me.in4.voidfightz.events.ItemsEvents;
import me.in4.voidfightz.events.Kills;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static me.in4.voidfightz.VoidFightZ.*;
import static me.in4.voidfightz.runnables.WorldsRunClass.spawn_loc;
import static me.in4.voidfightz.runnables.WorldsRunClass.world1;
import static me.in4.voidfightz.commands.QueueCommand.main_location;


public class RunClass extends BukkitRunnable {

    int next_time = 40;
    int count;
    double size;

    ItemsEvents itemsEvents = null;
    Kills kills = null;

    ArrayList<Player> alive_players = new ArrayList<>();
    ArrayList<Player> dead_players = new ArrayList<>();

    public RunClass (int count, double size) {
        this.count = count;
        this.size = size;
        this.itemsEvents = new ItemsEvents();
        this.kills = new Kills();
    }
    public RunClass (int count, double size, ItemsEvents event, Kills kills) {
        this.count = count;
        this.size = size;
        this.itemsEvents = event;
        this.kills = kills;
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
            new RunClass(this.count, this.size, this.itemsEvents, this.kills).runTaskLater(plugin, this.getPeriod());
        }

        else {
            new PlatformRunnable().runTaskLater(plugin, 1);
            int random_loc_x = ThreadLocalRandom.current().nextInt((int) (spawn_loc.getBlockX() - world1.getWorldBorder().getSize() / 2), (int) (spawn_loc.getBlockX() + world1.getWorldBorder().getSize() / 2 + 1));
            int random_loc_z = ThreadLocalRandom.current().nextInt((int) (spawn_loc.getBlockZ() - world1.getWorldBorder().getSize() / 2), (int) (spawn_loc.getBlockZ() + world1.getWorldBorder().getSize() / 2 + 1));
            for (int y = 320; y >= -64; y--) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        Location delete_loc = new Location(world1, random_loc_x + dx, y, random_loc_z + dz);
                        if (delete_loc.getBlock().getType().equals(Material.PLAYER_HEAD)) {
                            continue;
                        } else if (count <= 1300) {
                            delete_loc.getBlock().setType(Material.AIR);
                        }
                    }
                }
            }
            if (world1.getPlayers().size() >= 2) {
                for (Player player : world1.getPlayers()) {
                    if (player.isInvulnerable()) {
                        for (Player players: Bukkit.getOnlinePlayers()) {
                            players.setInvulnerable(false);
                        }
                    }
                    if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                        if (!(this.alive_players.contains(player))) {
                            this.alive_players.add(player);
                        }
                        this.dead_players.remove(player);
                    }
                    else if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                        if (!(this.dead_players.contains(player))) {
                            this.dead_players.add(player);
                        }
                        this.alive_players.remove(player);
                    }

                    if (this.alive_players.size() == 1 && this.dead_players.size() > 0) {
                        Bukkit.broadcastMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "VoidFightZ >>>    " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + ChatColor.MAGIC + "POSOSI " +
                                ChatColor.GOLD + "" + ChatColor.BOLD + "" + this.alive_players.get(0).getName() + " HAS WON THE GAME!! LETS FUCKING GOOOOOOOOO!!!!" +
                                ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + ChatColor.MAGIC + " ISOSOP");
                        new EndGameRunnable(this.itemsEvents, this.kills).runTaskLater(plugin, 100);
                        new FireworksRunnable(player).runTaskTimer(plugin, 1, 10);
                        for (Player players : world1.getPlayers()) {
                            if (players.hasPotionEffect(PotionEffectType.GLOWING)) {
                                players.removePotionEffect(PotionEffectType.GLOWING);
                            }
                            players.setGameMode(GameMode.SURVIVAL);
                            players.setInvulnerable(true);
                            this.dead_players.remove(players);
                            this.alive_players.remove(players);
                            players.getInventory().clear();
                            players.setLevel(0);
                            players.setExp(0);
                            players.setHealth(20);
                            players.setFoodLevel(20);
                            player.setStatistic(Statistic.MOB_KILLS, 0);
                            player.setStatistic(Statistic.PLAYER_KILLS, 0);
                        }
                        this.cancel();
                        this.count = 1301;

                    }

                }
            }
            if (this.count == 273) {
                for (Player player : world1.getPlayers()) {
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "The game will go into hardcore in 10 seconds... Everyone " +
                            "will be teleported to a random location! Get ready!");
                }
            }

            if (this.count >= 278 && this.count < 678) {
                this.setPeriod(20);
                world1.setGameRule(GameRule.KEEP_INVENTORY, false);

                if (this.count < 279) {
                    for (Player player : world1.getPlayers()) {
                        player.playSound(player.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_7, 2.0F, 2.0F);
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You won't be able to respawn from now on! The world will begin to " +
                                "delete faster and the border will shrink!");
                        while (true) {
                            int x = (int) ((Math.random() * (50 - (-50))) + (-50));
                            int z = (int) ((Math.random() * (50 - (-50))) + (-50));
                            int y = (int) ((Math.random() * (320 - (-64))) + (-64));
                            Location loc = new Location(world1, x, y, z);
                            Location b_loc = new Location(world1, x, y - 1, z);
                            Block block_below = b_loc.getBlock();
                            if (block_below.getType().isAir() || loc.getBlock().getType().isSolid()) {
                                while (y >= -63 && y <= 320) {
                                    if (!(block_below.getType().isAir()) || !(loc.getBlock().getType().isSolid())) {
                                        break;
                                    }
                                    y--;
                                }
                            }
                            else {
                                player.setInvulnerable(true);
                                new InvulnerableClass(player).runTaskLater(plugin, 40);
                                player.teleport(loc);
                                break;
                            }
                        }
                        int op_loot_count = itemsEvents.opLootCount(player);
                        int good_loot_count = itemsEvents.goodLootCount(player);
                        int lucky_blocks_broken_count = itemsEvents.luckyBlocksCount(player);
                        int op_loot_count1 = kills.opLootCount(player);
                        int good_loot_count1 = kills.goodLootCount(player);
                        int total_kills_count = kills.totalKillsCount(player);
                        int lucky_count = 64 - 64 * (op_loot_count + good_loot_count + op_loot_count1 + good_loot_count1) / (lucky_blocks_broken_count + total_kills_count);
                        for (int cnt = 0; cnt < lucky_count; cnt++) {
                            player.getInventory().addItem(new ItemStack(Material.SPONGE));
                        }
                        if (lucky_count > 0) {
                            player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "You were granted " + lucky_count + " lucky blocks!");
                        }
                        itemsEvents.lucky_blocks_broken_count.remove(player);
                        itemsEvents.op_loot_count.remove(player);
                        itemsEvents.good_loot_count.remove(player);
                        kills.total_kills_count.remove(player);
                        kills.op_loot_count.remove(player);
                        kills.good_loot_count.remove(player);
                    }
                }
                if (this.size > 87.5 && (this.count >= 400 && this.count < 500)) {
                    this.size -= 0.25;

                }
                else if (this.count >= 550 && this.size > 50){
                    this.size -= 0.36;
                }
            }
            else if (this.count >= 678 && this.count < 1048) {
                this.setPeriod(10);
                if (this.count >= 800 && (this.size <= 50 && this.size > 25)) {
                    this.size -= 0.25;
                }
            }
            else if (this.count >= 1048 && this.count < 1300) {
                this.setPeriod(5);
                if ((this.size > 10 && this.size <= 25) && this.count < 1290) {
                    this.size -= 0.125;
                }

            }
            if (count < 1300) {
                this.count++;
                world1.getWorldBorder().setSize(this.size);
                new RunClass(this.count, this.size, this.itemsEvents, this.kills).runTaskLater(plugin, this.getPeriod());
            }
        }


    }





}

