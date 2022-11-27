package me.in4.minecraftmadness.Events;

import me.in4.minecraftmadness.MinecraftMadness;
import me.in4.minecraftmadness.classes.RunClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static me.in4.minecraftmadness.MinecraftMadness.instance;

public class CopyPasterHoeEvent implements Listener {
    LivingEntity pray;
    Location loc;
    Location location;
    boolean hit_registered = false;
    boolean teleport_registered = false;
    Map<String, Long> cooldown = new HashMap<>();

    @EventHandler
    public void hit(EntityDamageByEntityEvent event) {
        if (event == null) {
            return;
        }
        Player player = (Player) event.getDamager();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_RED + "Hit an entity to copy ");
        lore.add(ChatColor.DARK_RED + "Right-click a block to paste.");
        if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
            this.pray = (LivingEntity) event.getEntity();
            this.loc = this.pray.getLocation();
            this.hit_registered = true;
        }
    }
    @EventHandler
    public void rightCLick(PlayerInteractEvent event) {
        if (event == null) {
            return;
        }
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();
            World world = block.getWorld();
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_RED + "Hit an entity to copy ");
            lore.add(ChatColor.DARK_RED + "Right-click a block to paste.");
            if (this.hit_registered) {
                if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
                    if (cooldown.containsKey(player.getName())) {
                        if (cooldown.get(player.getName()) > System.currentTimeMillis()) {
                            long time = (cooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
                            player.sendMessage(ChatColor.DARK_RED + "You can reuse the ability in " + time + " second(s)!");
                            return;
                        }

                    }
                    int x = block.getX();
                    int y = block.getY() + 1;
                    int z = block.getZ();
                    this.location = new Location(world, x, y, z);
                    this.pray.teleport(location);
                    this.teleport_registered = true;

                    this.cooldown.put(player.getName(), System.currentTimeMillis() + 45 * 1000);
                }
            }
        }
        if (this.teleport_registered) {
            RunClass delayed = new RunClass(this.pray, this.loc);
            delayed.runTaskLater(instance, 300L);
            this.loc = this.location;
            this.teleport_registered = false;
        }

    }

}

