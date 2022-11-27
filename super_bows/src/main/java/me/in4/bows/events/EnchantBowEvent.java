package me.in4.bows.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class EnchantBowEvent implements Listener {

    private Material[] melee = new Material[]{
            Material.DIAMOND_SWORD, Material.NETHERITE_SWORD, Material.IRON_SWORD, Material.IRON_SWORD, Material.STONE_SWORD, Material.WOODEN_SWORD,
            Material.TRIDENT, Material.ENCHANTED_BOOK
    };
    private Material[] ranged = new Material[]{
            Material.BOW, Material.CROSSBOW, Material.ENCHANTED_BOOK
    };
    private Material[] tools = new Material[]{
            Material.NETHERITE_AXE, Material.NETHERITE_PICKAXE, Material.NETHERITE_SHOVEL, Material.NETHERITE_HOE, Material.SHEARS,
            Material.GOLDEN_CARROT, Material.TOTEM_OF_UNDYING, Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL,
            Material.FLINT_AND_STEEL, Material.IRON_AXE, Material.IRON_PICKAXE, Material.IRON_SHOVEL, Material.STONE_AXE, Material.STONE_PICKAXE,
            Material.STONE_SHOVEL, Material.WOODEN_AXE, Material.WOODEN_SHOVEL, Material.WOODEN_HOE, Material.GOLDEN_APPLE,
            Material.ENCHANTED_GOLDEN_APPLE, Material.GOLDEN_SWORD, Material.GOLDEN_SHOVEL, Material.GOLDEN_AXE, Material.GOLDEN_PICKAXE,
            Material.ENCHANTED_BOOK
    };
    private Material[] armour = new Material[]{
            Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_HELMET, Material.SHIELD,
            Material.DIAMOND_LEGGINGS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.IRON_HELMET,
            Material.IRON_LEGGINGS, Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS,
            Material.GOLDEN_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Material.ENCHANTED_BOOK
    };
    private Material[] boots = new Material[] {
            Material.NETHERITE_BOOTS, Material.DIAMOND_BOOTS, Material.IRON_BOOTS, Material.GOLDEN_BOOTS, Material.LEATHER_BOOTS,
    };
    private Enchantment[] e_melee = new Enchantment[]{
            Enchantment.DAMAGE_ALL, Enchantment.FIRE_ASPECT, Enchantment.KNOCKBACK, Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.LOOT_BONUS_MOBS,
            Enchantment.LOYALTY, Enchantment.RIPTIDE, Enchantment.VANISHING_CURSE
    };
    private Enchantment[] e_ranged = new Enchantment[]{
            Enchantment.ARROW_INFINITE, Enchantment.ARROW_KNOCKBACK, Enchantment.ARROW_FIRE, Enchantment.MENDING, Enchantment.DURABILITY,
            Enchantment.ARROW_DAMAGE, Enchantment.PIERCING, Enchantment.MULTISHOT, Enchantment.QUICK_CHARGE, Enchantment.VANISHING_CURSE
    };
    private Enchantment[] e_tools = new Enchantment[]{
            Enchantment.DAMAGE_ALL, Enchantment.FIRE_ASPECT, Enchantment.KNOCKBACK, Enchantment.VANISHING_CURSE,
            Enchantment.DIG_SPEED, Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.LOOT_BONUS_BLOCKS,
            Enchantment.SILK_TOUCH, Enchantment.LOOT_BONUS_MOBS
    };
    private Enchantment[] e_armour = new Enchantment[]{
            Enchantment.BINDING_CURSE, Enchantment.VANISHING_CURSE, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS,
            Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.MENDING, Enchantment.DURABILITY
    };
    private Enchantment[] e_boots = new Enchantment[] {
            Enchantment.BINDING_CURSE, Enchantment.VANISHING_CURSE, Enchantment.SOUL_SPEED, Enchantment.FROST_WALKER, Enchantment.DEPTH_STRIDER,
            Enchantment.PROTECTION_FALL, Enchantment.MENDING, Enchantment.DURABILITY
    };

    private Integer[] random_values = new Integer[]{
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 36
    };

    private Integer[] ints = new Integer[]{
            1, 2, 3, 4, 5
    };

    @EventHandler
    public void onEnchantBowShot(ProjectileHitEvent event) {
        Arrow arrow = (Arrow) event.getEntity();
        Player player = (Player) arrow.getShooter();
        Location loc = (Location) arrow.getLocation();
        World world = arrow.getWorld();
        ArrayList<String> enchant_bow_lore = new ArrayList<>();
        enchant_bow_lore.add(ChatColor.YELLOW + "This bow gives you random enchanted item!");
        if (player.getInventory().getItemInMainHand().getItemMeta().getLore().equals(enchant_bow_lore)) {
            Random random = new Random();
            Integer r = ints[random.nextInt(ints.length)];
            if (r == 1) {
                Material random_item = melee[random.nextInt(melee.length)];
                Enchantment random_enchant = e_melee[random.nextInt(e_melee.length)];
                Enchantment random_enchant1 = e_melee[random.nextInt(e_melee.length)];
                Enchantment random_enchant2 = e_melee[random.nextInt(e_melee.length)];
                Integer random_value = random_values[random.nextInt(random_values.length)];
                ItemStack random_item1 = new ItemStack(random_item);
                random_item1.addUnsafeEnchantment(random_enchant, random_value);
                random_item1.addUnsafeEnchantment(random_enchant1, random_value);
                random_item1.addUnsafeEnchantment(random_enchant2, random_value);
                world.dropItemNaturally(loc, random_item1);
            }
            if (r == 2) {
                Material random_item = ranged[random.nextInt(ranged.length)];
                Enchantment random_enchant = e_ranged[random.nextInt(e_ranged.length)];
                Enchantment random_enchant1 = e_ranged[random.nextInt(e_ranged.length)];
                Integer random_value = random_values[random.nextInt(random_values.length)];
                ItemStack random_item1 = new ItemStack(random_item);
                random_item1.addUnsafeEnchantment(random_enchant, random_value);
                random_item1.addUnsafeEnchantment(random_enchant1, random_value);
                world.dropItemNaturally(loc, random_item1);
            }
            if (r == 3) {
                Material random_item = tools[random.nextInt(tools.length)];
                Enchantment random_enchant = e_tools[random.nextInt(e_tools.length)];
                Enchantment random_enchant1 = e_tools[random.nextInt(e_tools.length)];
                Integer random_value = random_values[random.nextInt(random_values.length)];
                ItemStack random_item1 = new ItemStack(random_item);
                random_item1.addUnsafeEnchantment(random_enchant, random_value);
                random_item1.addUnsafeEnchantment(random_enchant1, random_value);
                world.dropItemNaturally(loc, random_item1);
            }
            if (r == 4) {
                Material random_item = armour[random.nextInt(armour.length)];
                Enchantment random_enchant = e_armour[random.nextInt(e_armour.length)];
                Integer random_value = random_values[random.nextInt(random_values.length)];
                ItemStack random_item1 = new ItemStack(random_item);
                random_item1.addUnsafeEnchantment(random_enchant, random_value);
                world.dropItemNaturally(loc, random_item1);
            }
            if (r == 5) {
                Material random_item = boots[random.nextInt(boots.length)];
                Enchantment random_enchant = e_boots[random.nextInt(e_boots.length)];
                Enchantment random_enchant1 = e_boots[random.nextInt(e_boots.length)];
                Integer random_value = random_values[random.nextInt(random_values.length)];
                ItemStack random_item1 = new ItemStack(random_item);
                random_item1.addUnsafeEnchantment(random_enchant, random_value);
                random_item1.addUnsafeEnchantment(random_enchant1, random_value);
                world.dropItemNaturally(loc, random_item1);
            }
        }


    }

}
