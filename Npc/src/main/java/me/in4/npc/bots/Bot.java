package me.in4.npc.bots;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;
import org.bukkit.util.Vector;

public class Bot extends Zombie {


    public Bot(Location loc) {
        super(EntityType.ZOMBIE, (((CraftWorld) loc.getWorld()).getHandle()));
        this.setPos(loc.getX(), loc.getY(), loc.getZ());
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 8.0D, true));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 8.0D));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
    }

    public boolean isStuck () {
        return (Math.abs(this.getBukkitEntity().getVelocity().getX()) <= 0.03) && (Math.abs(this.getBukkitEntity().getVelocity().getZ()) <= 0.03);
    }
}
