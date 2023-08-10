package me.in4.voidfightz.runnables;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Ravager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class CheatArrowsRunnable extends BukkitRunnable {

    Player ravager;
    Player shooter;
    Arrow cheat_arrow;
    Location shooter_loc;

    public CheatArrowsRunnable (Player rav, Player shooter, Arrow arrow, Location shooter_loc) {
        this.ravager = rav;
        this.shooter = shooter;
        this.cheat_arrow = arrow;
        this.shooter_loc = shooter_loc;
    }

    @Override
    public void run() {
        assert this.ravager != null;
        Location r_loc = this.ravager.getLocation();
        double x = r_loc.getX();
        double y = r_loc.getY();
        double z = r_loc.getZ();
        this.cheat_arrow.setVelocity(new Vector(x - this.shooter_loc.getX(), y - this.shooter_loc.getY(), z - this.shooter_loc.getZ()).normalize());
        this.cheat_arrow.setDamage(this.cheat_arrow.getDamage());
        if (this.ravager.isDead()) {
            this.cancel();
        }

    }
    @EventHandler
    public void cheatHit (EntityDamageByEntityEvent event) {
        if (event.getDamager().equals(this.cheat_arrow)) {
            this.cancel();
        }
    }
    @EventHandler
    public void anotherCheatHit (ProjectileHitEvent event) {
        if (event.getEntity().equals(this.cheat_arrow)) {
            this.cancel();
        }
    }
}
