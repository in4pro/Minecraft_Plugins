package me.in4.bows.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.GlassPane;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.ArrayList;

public class XRayBowEvent implements Listener {

    @EventHandler
    public void lavaBow(ProjectileHitEvent event) {
        Arrow arrow = (Arrow) event.getEntity();
        Player player = (Player) arrow.getShooter();
        ArrayList<String> xray_bow_lore = new ArrayList();
        xray_bow_lore.add(ChatColor.YELLOW + "This bow allows you to x-ray!");
        if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(xray_bow_lore)) {
            Location location = (Location) arrow.getLocation();
            Block block = (Block) arrow.getLocation().getBlock();
            if (location.getX() - block.getX() < 1 & location.getY() - block.getY() < 1 & location.getZ() - block.getZ() < 1) {
                block.setType(Material.AIR);
            }

        }
    }
}
