package me.in4.bows.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Locale;

public class DashBowEvent implements Listener {

    @EventHandler
    public void onHit (ProjectileHitEvent e) {
        Arrow arrow = (Arrow) e.getEntity();
        Player player = (Player) arrow.getShooter();
        ArrayList<String> dash_bow_lore = new ArrayList<>();
        dash_bow_lore.add(ChatColor.YELLOW + "This bow allows you to dash and do damage to mobs/players!");
        if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(dash_bow_lore)){
             player.setVelocity(arrow.getLocation().getDirection());
        }
    }
}
