package me.in4.minecraftmadness.Events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class LightningHoeEvent implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.WHITE + "Right-click to spawn lightning bolts around you.");
            if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {

            }
        }
    }
}
