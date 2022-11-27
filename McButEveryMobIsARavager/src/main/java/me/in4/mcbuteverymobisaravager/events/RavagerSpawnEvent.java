package me.in4.mcbuteverymobisaravager.events;

import me.in4.mcbuteverymobisaravager.classes.items.weapons.Guns;
import me.in4.mcbuteverymobisaravager.nms.TntRavager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.PrimedTnt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftRavager;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftTNTPrimed;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import static me.in4.mcbuteverymobisaravager.McButEveryMobIsARavager.plugin;

public class RavagerSpawnEvent implements Listener {

    private final HashMap<UUID, Long> cooldown;
    int count = 0;

    public RavagerSpawnEvent() {
        this.cooldown = new HashMap<>();
    }

    @EventHandler
    public void onSpawn (EntitySpawnEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        if (event.getEntity().getLocation().getBlock().isLiquid()) {
            return;
        }
        if (event.getEntity() instanceof Ravager) {
            return;
        }
        else {
           event.setCancelled(true);
           LivingEntity ravager = (LivingEntity) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.RAVAGER);
           ravager.setMetadata("isRavager", new FixedMetadataValue(plugin, Boolean.valueOf(true)));
           ravager.setRemoveWhenFarAway(true);
            if (event.getEntity() instanceof Blaze) {
                ravager.setMetadata("isBlaze", new FixedMetadataValue(plugin, Boolean.valueOf(true)));

            }
            else if (this.count > 2 && this.count <= 36) {
                event.setCancelled(true);
                TntRavager tntRavager = new TntRavager(event.getEntity().getLocation());
                ServerLevel world = ((CraftWorld) event.getEntity().getWorld()).getHandle();
                world.addFreshEntityWithPassengers(tntRavager);
                tntRavager.getBukkitEntity().setMetadata("isTntRavager", new FixedMetadataValue(plugin, Boolean.valueOf(true)));
            }
        }
    }

    @EventHandler
    public void pMove (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        for (Entity tnt_ravager : player.getNearbyEntities(4, 4, 4)) {
            if (!(tnt_ravager instanceof Ravager)) {
                break;
            }
            else {
                net.minecraft.world.entity.monster.Ravager nms_tnt_ravager = ((CraftRavager) tnt_ravager).getHandle();
                if (!(tnt_ravager.hasMetadata("isTntRavager"))) {
                    return;
                }
                else {
                    TNTPrimed tnt = nms_tnt_ravager.getBukkitEntity().getWorld().spawn(tnt_ravager.getLocation(), TNTPrimed.class);
                    PrimedTnt nms_tnt = ((CraftTNTPrimed) tnt).getHandle();
                    nms_tnt.startRiding(nms_tnt_ravager);
                    if (!(this.cooldown.containsKey(nms_tnt.getBukkitEntity().getUniqueId()))) {
                        this.cooldown.put(nms_tnt.getBukkitEntity().getUniqueId(), System.currentTimeMillis());
                        tnt.setVelocity(nms_tnt_ravager.getBukkitEntity().getLocation().getDirection().normalize().add(player.getLocation().getDirection().normalize()));

                    } else {
                        long timeElapsed = System.currentTimeMillis() - this.cooldown.get(nms_tnt.getBukkitEntity().getUniqueId());
                        if (timeElapsed >= 5000) {
                            this.cooldown.put(nms_tnt.getBukkitEntity().getUniqueId(), System.currentTimeMillis());
                            tnt.setVelocity(nms_tnt_ravager.getBukkitEntity().getLocation().getDirection().normalize().add(player.getLocation().getDirection().normalize()));

                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void kill (EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) {
            return;
        }
        if (event.getEntity().hasMetadata("isRavager") || event.getEntity().hasMetadata("isTntRavager")) {
            this.count++;
            Player player = event.getEntity().getKiller();
            Location location = player.getLocation();
            player.sendMessage(this.count + "");
            if (this.count == 2) {
                Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Great job! You have killed 10 ravagers, but it will get even harder...");
                event.setDroppedExp(event.getDroppedExp()*10);
                Location another = new Location(location.getWorld(), location.getBlockX(), location.getBlockY() + 10, location.getBlockZ());
                location.getBlock().setType(Material.CHEST);
                Chest chest = (Chest) location.getBlock().getState();
                Random random = new Random();
                for (int cnt = 0; cnt <= 5; cnt++) {
                    if (random.nextInt(100) <= 50) {
                        chest.getInventory().addItem(Guns.sniper_gun);
                    }
                    else if (random.nextInt(100) > 50){
                        chest.getInventory().addItem(Guns.ravager_cannon);
                    }

                }
            }

        }
    }

    @EventHandler
    public void onKill (EntityDeathEvent event) {
        if (!(event.getEntity().hasMetadata("isBlaze"))) {
            return;
        }

        else {
            Player player = event.getEntity().getKiller();
            Location ra_loc = event.getEntity().getLocation();
            Random random = new Random();
            if (random.nextInt(100) <= 50) {
                event.getEntity().getLocation().getWorld().dropItemNaturally(ra_loc, new ItemStack(Material.BLAZE_ROD));

            }

        }

    }


}
