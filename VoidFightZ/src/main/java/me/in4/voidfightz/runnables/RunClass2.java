package me.in4.voidfightz.runnables;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static me.in4.voidfightz.runnables.WorldsRunClass.world1;

public class RunClass2 extends BukkitRunnable {

    int count = 0;

    @Override
    public void run() {
        if (world1.getPlayers().size() == 0) {
            return;
        }
        else {
            for (Player player : world1.getPlayers()) {
                player.getInventory().addItem(new ItemStack(Material.OAK_PLANKS));
                player.getInventory().addItem(new ItemStack(Material.COBBLESTONE));
            }
            this.count++;
        }

    }

}
