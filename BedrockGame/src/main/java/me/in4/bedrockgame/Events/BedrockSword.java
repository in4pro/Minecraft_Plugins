package me.in4.bedrockgame.Events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BedrockSword implements Listener {

    Map<String, Long> cooldown = new HashMap<String, Long>();

    @EventHandler
    public void interact (EntityDamageByEntityEvent event) {
        Entity target = (Entity) event.getEntity();
        Location location = (Location) event.getEntity().getLocation();
        //Player target = (Player) event.getEntity();
        Player player = (Player) event.getDamager();
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Bedrock Sword!");
        if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
            if (cooldown.containsKey(player.getName())) {
                if (cooldown.get(player.getName()) > System.currentTimeMillis()) {
                    long time = (cooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
                    player.sendMessage(ChatColor.GOLD + "You can reuse the ability in " + time + " seconds!");
                    return;
                }
            }
            target.getWorld().strikeLightning(location);
            this.cooldown.put(player.getName(), System.currentTimeMillis() + 10 * 1000);
        }


    }
}
