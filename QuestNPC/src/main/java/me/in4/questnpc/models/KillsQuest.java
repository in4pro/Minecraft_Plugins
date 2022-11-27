package me.in4.questnpc.models;

import org.bukkit.entity.EntityType;

public class KillsQuest extends Quest{

    private EntityType entityType;
    private int kill_amount;

    public KillsQuest(String name, String description, String reward, int reward_amount, EntityType entityType, Integer kill_amount) {
        super(name, description, reward);
        this.entityType = entityType;
        this.kill_amount = kill_amount;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setKill_amount(int kill_amount) {
        this.kill_amount = kill_amount;
    }

    public int getKill_amount() {
        return kill_amount;
    }
}
