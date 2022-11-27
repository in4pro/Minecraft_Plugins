package me.in4.questnpc.models;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemQuest extends Quest{

    private Material material;
    private int amount;

    public ItemQuest(String name, String description, String reward, Material material, int amount) {
        super(name, description, reward);
        this.material = material;
        this.amount = amount;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
