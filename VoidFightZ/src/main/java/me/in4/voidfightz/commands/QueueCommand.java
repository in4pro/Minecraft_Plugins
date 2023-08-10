package me.in4.voidfightz.commands;

import me.in4.voidfightz.Leaderboard;
import me.in4.voidfightz.items.Items;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static me.in4.voidfightz.runnables.WorldsRunClass.spawn_loc;
import static me.in4.voidfightz.runnables.WorldsRunClass.world1;

public class QueueCommand implements CommandExecutor {

    public static Location main_location;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "You must type /voidfightz join to join the queue, or /voidfightz leave to leave the queue");
            }
            else {
                String word = args[0];

                if (word.equals("join") && (!(this.players_in_queue.contains(player)) && !(this.players_in_game.contains(player)))) {
                    this.players_in_queue.add(player);
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You have joined the queue for VoidFightZ!");
                    if (this.players_in_queue.size() >= 2 || world1.getPlayers().size() > 0) {
                        for (Player player_in_queue : this.players_in_queue) {
                            player_in_queue.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "The game has started! Good luck everyone!");
                            queueFunction(player_in_queue);
                        }
                        if (!player.getWorld().equals(world1) && players_in_queue.contains(player)) {
                            queueFunction(player);

                        }
                        this.players_in_queue.remove(player);
                    }
                }
                else if ((this.players_in_queue.contains(player) || this.players_in_game.contains(player)) && word.equals("join")) {
                    player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "You are already in the queue/game!");
                }

                if (word.equals("leave")) {
                    if (player.getWorld().equals(world1)) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You can't leave while in game!");
                    }
                    else if (this.players_in_queue.contains(player) || this.players_in_game.contains(player)) {
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You have left the queue for VoidFightZ");
                        this.players_in_queue.remove(player);
                        this.players_in_game.remove(player);
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "You are not in queue!");
                    }

                }

            }
        }

        return true;

    }

    public void queueFunction (Player player) {
        player.getInventory().clear();
        player.setLevel(0);
        player.setExp(0);
        player.setFoodLevel(20);
        player.setHealth(20);
        player.teleport(spawn_loc);
        player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
        player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
        player.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
        player.getInventory().addItem(new ItemStack(Material.SHIELD));
        for (int cnt1 = 0; cnt1 < 64; cnt1++) {
            player.getInventory().addItem(new ItemStack(Material.COBBLESTONE));
        }
        for (int cnt2 = 0; cnt2 < 16; cnt2++) {
            player.getInventory().addItem(new ItemStack(Material.SLIME_BLOCK));
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT));
        }
        new Leaderboard(player).createScoreboard();

        this.players_in_game.add(player);
    }


    ArrayList<Player> players_in_queue = new ArrayList<>();
    ArrayList<Player> players_in_game = new ArrayList<Player>();

}
