/*package me.in4.bows.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.ArrayList;

public class TteleportBowEvent implements Listener {

    @EventHandler
    public void onBowShot(ProjectileHitEvent event) {

        Player player = (Player) event.getEntity().getShooter();
        Location location = event.getEntity().getLocation();
        ArrayList<String> tp_bow_lore = new ArrayList();
        tp_bow_lore.add(ChatColor.YELLOW + "This bow lets you teleport where");
        tp_bow_lore.add(ChatColor.YELLOW + "the arrow that you shot has landed");
        if (event.getEntity() instanceof Arrow) {
            if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(tp_bow_lore)) {
                player.setInvulnerable(true);
                player.teleport(location);
                player.setInvulnerable(false);
                player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1.0F, 1.0F);
            }
        }
    }
}*/


