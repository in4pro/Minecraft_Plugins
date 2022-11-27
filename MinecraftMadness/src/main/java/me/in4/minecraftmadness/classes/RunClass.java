package me.in4.minecraftmadness.classes;

import me.in4.minecraftmadness.Events.CopyPasterHoeEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class RunClass extends BukkitRunnable {
    LivingEntity pray1;
    Location loc1;

    public RunClass(LivingEntity pray, Location loc) {
        this.pray1 = pray;
        this.loc1 = loc;
    }

    @Override
    public void run() {
        this.pray1.teleport(this.loc1);
    }
}
