package me.in4.bedrockgame.Events;

import net.minecraft.server.v1_16_R3.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BedrockDropEvent implements Listener {

    @EventHandler
    public void Drop(PlayerInteractEvent event) {
        Player player = (Player) event.getPlayer();
        Action act = event.getAction();
        if (act == Action.LEFT_CLICK_BLOCK) {
            int xp = player.getLevel();
            Block block = (Block) event.getClickedBlock();
            Location loc = block.getLocation();
            World world = block.getWorld();
            ArrayList<String> lore = new ArrayList();
            lore.add(ChatColor.YELLOW + "Allows you to mine bedrock!!!");
            if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(lore)) {
                if (block.getType().equals(Material.BEDROCK)) {
                    if (xp >= 5) {
                        world.dropItemNaturally(loc, new ItemStack(Material.BEDROCK));
                        block.setBlockData(Material.AIR.createBlockData());
                        player.setLevel(xp - 5);
                    }
                    else if (xp < 5) {
                        player.sendMessage(ChatColor.DARK_RED + "You need more xp!");
                    }
                }
            }

        }
    }
}