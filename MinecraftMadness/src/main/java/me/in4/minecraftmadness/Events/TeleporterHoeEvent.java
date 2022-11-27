package me.in4.minecraftmadness.Events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class TeleporterHoeEvent implements Listener {

    @EventHandler
    public void teleport(PlayerInteractEvent event) {
        if (event == null) {
            return;
        }
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();
            Block block = player.getTargetBlockExact(360);

            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_PURPLE + "Right-click to teleport.");
            if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
                Location location = block.getLocation();
                player.teleport(location);
            }
        }
    }
}
