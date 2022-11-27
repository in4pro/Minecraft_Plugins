package me.in4.bows.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.ArrayList;

public class TNTBowEvent implements Listener {

    @EventHandler
    public void onBowShot (ProjectileHitEvent e){

        Arrow arrow = (Arrow) e.getEntity();
        Player player = (Player) arrow.getShooter();
        Location loc = arrow.getLocation();
        World world = player.getWorld();
        ArrayList<String> tnt_bow_lore = new ArrayList<>();
        tnt_bow_lore.add(ChatColor.YELLOW + "This bow shoots tnt!");
        if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(tnt_bow_lore)) {

            world.createExplosion(loc, 4, true, true);

        }
    }
}
