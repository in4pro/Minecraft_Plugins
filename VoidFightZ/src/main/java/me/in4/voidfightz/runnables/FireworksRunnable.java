package me.in4.voidfightz.runnables;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import static me.in4.voidfightz.runnables.WorldsRunClass.world1;

public class FireworksRunnable extends BukkitRunnable {

    Player player;
    int count = 0;

    public FireworksRunnable (Player player) {
        this.player = player;
    }



    @Override
    public void run() {
        Firework firework = world1.spawn(this.player.getLocation(), Firework.class);
        FireworkMeta fm = firework.getFireworkMeta();
        fm.addEffect(FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.BALL_LARGE).withColor(Color.YELLOW, Color.RED).build());
        count++;
        if (count >= 5) {
            cancel();
        }
    }
}
