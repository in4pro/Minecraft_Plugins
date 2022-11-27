package me.in4.bedrockgame.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BedrockBootsEvent implements Listener {

    Map<String, Long> cooldown = new HashMap<>();

    @EventHandler
    public void playerShift(PlayerToggleSneakEvent event) {
        Player player = (Player) event.getPlayer();
        ArrayList<String> lore = new ArrayList();
        lore.add(ChatColor.YELLOW + "Bedrock Boots!");
        if (player.getInventory().getBoots().getItemMeta().getLore().equals(lore)) {
            if (player.isSneaking()) {
                if (cooldown.containsKey(player.getName())) {
                    if (cooldown.get(player.getName()) > System.currentTimeMillis()) {
                        long time = (cooldown.get(player.getName()) - System.currentTimeMillis()) / 1000;
                        player.sendMessage(ChatColor.GOLD + "You can get a new potion effect in " + time + " seconds!");
                        return;
                    }
                }
                Random random = new Random();
                PotionEffect random_effect = potion_effects[random.nextInt(potion_effects.length)];
                player.addPotionEffect(random_effect);
                this.cooldown.put(player.getName(), System.currentTimeMillis() + 30 * 1000);
            }
        }
    }

    private PotionEffect[] potion_effects = new PotionEffect[] {
            PotionEffectType.JUMP.createEffect(580, 7), PotionEffectType.LEVITATION.createEffect(580, 7),
            PotionEffectType.SLOW_FALLING.createEffect(580, 7), PotionEffectType.SPEED.createEffect(580, 7),
            PotionEffectType.DAMAGE_RESISTANCE.createEffect(580, 7), PotionEffectType.DOLPHINS_GRACE.createEffect(580, 7),
    };
}
