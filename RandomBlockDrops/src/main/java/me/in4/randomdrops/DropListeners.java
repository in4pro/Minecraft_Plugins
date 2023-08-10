package me.in4.randomdrops;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class DropListeners implements Listener {

    private final HashMap<Material, Material> materialMap;

    List<Material> materials = Arrays.stream(Material.values()).collect(Collectors.toList());



    public DropListeners() {
        materialMap = new HashMap<>();
    }

    @EventHandler
    public void breakEvent (BlockBreakEvent event) {
        Block block = event.getBlock();
        Material material = block.getType();
        if (!materialMap.containsKey(material)) {
            Random random = new Random();
            Material random_material = materials.get(random.nextInt(materials.size()));
            materialMap.put(material, random_material);
        }
        Random random_int = new Random();
        int random_value = random_int.nextInt((512 - 1) + 1) + 1;
        ItemStack item = new ItemStack(materialMap.get(material));
        for (int cnt = 0; cnt <= random_value; cnt++) {
            block.getWorld().dropItem(block.getLocation(), item);
        }
    }

    @EventHandler
    public void dropEvent (BlockDropItemEvent event) {
        event.setCancelled(true);
    }



}
