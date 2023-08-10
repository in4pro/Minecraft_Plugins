package me.in4.voidfightz.runnables;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class InvulnerableClass extends BukkitRunnable {

    Player player;

    public InvulnerableClass (Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        this.player.setInvulnerable(false);
    }
}
