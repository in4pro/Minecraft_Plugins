package me.in4.npc.runnables;

import me.in4.npc.bots.Bot;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import org.bukkit.scheduler.BukkitRunnable;

public class OneRun extends BukkitRunnable {

    Bot mob;

    public OneRun (Bot c_mob) {
        mob = c_mob;
    }

    @Override
    public void run() {
        mob.setInvisible(true);
        mob.setSilent(true);
        mob.setHealth(1000F);
        mob.setCustomName(Component.nullToEmpty("Gagloev"));
        mob.setCustomNameVisible(false);
        cancel();
    }
}
