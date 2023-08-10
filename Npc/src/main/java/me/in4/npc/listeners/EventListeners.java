package me.in4.npc.listeners;

import me.in4.npc.Npc;
import me.in4.npc.bots.Bot;
import net.minecraft.network.protocol.game.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

import java.util.HashMap;
import java.util.Optional;

public class EventListeners implements Listener {

    private final HashMap<Player, Integer> damage_absorbed_by_shield = new HashMap<>();
    int damage_absorbed = 0;



    @EventHandler
    public void onNpcDamageByPlayer (EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                CraftPlayer cp = (CraftPlayer) player;
                ServerPlayer sp = cp.getHandle();
                ServerGamePacketListenerImpl packetListener = sp.connection;
                packetListener.send(new ClientboundAnimatePacket(Npc.getBots().get(0), 0));
            }
        }
        if (event.getEntity().getCustomName() != null) {
            if (event.getEntity().getCustomName().equals("Gagloev")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    CraftPlayer cp = (CraftPlayer) player;
                    ServerPlayer sp = cp.getHandle();
                    ServerGamePacketListenerImpl packetListener = sp.connection;
                    packetListener.send(new ClientboundAnimatePacket(Npc.getBots().get(0), 1));
                    packetListener.send(new ClientboundDamageEventPacket(Npc.getBots().get(0).getBukkitEntity().getEntityId(), 1,
                            sp.getBukkitEntity().getEntityId(), sp.getBukkitEntity().getEntityId(), Optional.empty()));
                }
                event.setDamage(0);
            }
        }
    }

    @EventHandler
    public void onBurn (EntityCombustEvent event) {
        if (event.getEntity().getCustomName() != null) {
            if (event.getEntity().getCustomName().equals("Gagloev")) {
                event.setCancelled(true);
            }
        }

    }

    public void facing (ServerPlayer npc, Bot mob) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer cp = (CraftPlayer) player;
            ServerPlayer sp = cp.getHandle();
            ServerGamePacketListenerImpl packetListener = sp.connection;
            packetListener.send(new ClientboundRotateHeadPacket(npc, (byte) ((mob.getBukkitYaw() *  256 / 360))));
            packetListener.send(new ClientboundMoveEntityPacket.Rot(npc.getBukkitEntity().getEntityId(), (byte) (mob.getBukkitEntity().getLocation().getYaw() *  256 / 360), (byte) (mob.getBukkitEntity().getLocation().getPitch() *  256 / 360), false));
        }
    }

}
