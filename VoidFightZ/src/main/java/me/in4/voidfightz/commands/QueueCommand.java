package me.in4.voidfightz.commands;

import me.in4.voidfightz.VoidFightZ;
import me.in4.voidfightz.events.RunClass;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static me.in4.voidfightz.VoidFightZ.*;
import static me.in4.voidfightz.WorldsRunClass.spawn_loc;
import static me.in4.voidfightz.WorldsRunClass.world1;

public class QueueCommand implements CommandExecutor {

    public static Location main_location;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            main_location = player.getWorld().getSpawnLocation();
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "You must type /voidfightz join to join the queue, or /voidfightz leave to leave the queue");
            }
            else {
                String word = args[0];

                if (word.equals("join") && !(this.players_in_queue.contains(player))) {
                    this.players_in_queue.add(player);
                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You have joined the queue for VoidFightZ!");
                    if (this.players_in_queue.size() >= 5) {
                        for (int cnt = 0; cnt < this.players_in_queue.size(); cnt++) {
                            Player player_in_queue = (Player) this.players_in_queue.get(cnt);
                            player_in_queue.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "The game has started! Good luck everyone!");
                            player_in_queue.teleport(spawn_loc);
                            player_in_queue.getInventory().addItem(new ItemStack(Material.IRON_AXE));
                            player_in_queue.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
                            player_in_queue.getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
                            player_in_queue.getInventory().addItem(new ItemStack(Material.SHIELD));
                            player_in_queue.getInventory().addItem(new ItemStack(Material.SHEARS));
                            player_in_queue.getInventory().addItem(new ItemStack(Material.SHULKER_BOX));
                            for (int cnt1 = 0; cnt1 < 64; cnt1++) {
                                player_in_queue.getInventory().addItem(new ItemStack(Material.STONE));
                                player_in_queue.getInventory().addItem(new ItemStack(Material.DIRT));
                                player_in_queue.getInventory().addItem(new ItemStack(Material.COBWEB));
                                player_in_queue.getInventory().addItem(new ItemStack(Material.GOLDEN_CARROT));
                            }
                            this.players_in_queue.remove(player_in_queue);
                            this.players_in_game.add(player_in_queue);
                        }

                    }

                }
                else if (this.players_in_queue.contains(player) || this.players_in_game.contains(player)) {
                    player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "You are already in the queue/game!");
                }

                if (word.equals("leave")) {
                    player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "You have left the queue for VoidFightZ");
                    this.players_in_queue.remove(player);
                }

            }
        }

        return true;

    }


    ArrayList players_in_queue = new ArrayList();
    ArrayList players_in_game = new ArrayList();

}
