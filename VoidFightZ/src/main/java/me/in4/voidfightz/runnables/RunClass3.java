package me.in4.voidfightz.runnables;

import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Objects;

import static me.in4.voidfightz.items.PotionOfLevitation.potion_of_levitation;
import static me.in4.voidfightz.runnables.WorldsRunClass.world1;

public class RunClass3 extends BukkitRunnable {

    int count = 0;

    @Override
    public void run() {
        if (world1.getPlayers().size() == 0) {
            return;
        }
        else {
            for (Player player : world1.getPlayers()) {
                if (Boolean.FALSE.equals(world1.getGameRuleValue(GameRule.KEEP_INVENTORY))) {
                    player.getInventory().addItem(potion_of_levitation);
                }
            }
            this.count++;
        }
    }

}
