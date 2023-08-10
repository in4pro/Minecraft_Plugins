package me.in4.voidfightz.runnables;

import org.bukkit.entity.TNTPrimed;
import org.bukkit.scheduler.BukkitRunnable;

public class TNTRunClass extends BukkitRunnable {

    TNTPrimed tnt;

    public TNTRunClass (TNTPrimed tntPrimed) {
        this.tnt = tntPrimed;
    }

    @Override
    public void run() {
        if (this.tnt.isOnGround()) {
            tnt.setFuseTicks(0);
        }
    }
}
