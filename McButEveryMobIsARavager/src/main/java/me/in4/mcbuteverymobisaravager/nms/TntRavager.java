package me.in4.mcbuteverymobisaravager.nms;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Ravager;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;

import java.util.Random;

public class TntRavager extends Ravager {

    public TntRavager(Location location) {
        super(EntityType.RAVAGER, ((CraftWorld) location.getWorld()).getHandle());
        this.setPos(location.getX(), location.getY(), location.getZ());
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(250.0F);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.5F);
    }
}
