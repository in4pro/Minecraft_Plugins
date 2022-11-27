package me.in4.minecraftmadness.Events;
import org.bukkit.*;
import org.bukkit.block.BlastFurnace;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Random;

public class EchantsChestEvent implements Listener {

    @EventHandler
    public void XpMachine(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            if (block.getType().equals(Material.CHEST)) {
                Chest chest = (Chest) block.getState();
                if (chest.getCustomName().equalsIgnoreCase(ChatColor.BLUE + "" + ChatColor.BOLD + "Chest-Enchanter")) {
                    Inventory inv = chest.getBlockInventory();
                    for (ItemStack item: inv.getContents()) {
                        if (item != null) {
                            Material mat = item.getType();
                            Random random = new Random();

                            if (Arrays.asList(melee).contains(mat)) {
                                Enchantment r_m_e = melee_enchants[random.nextInt(melee_enchants.length)];
                                item.addUnsafeEnchantment(r_m_e, 42);
                            }
                            if (Arrays.asList(tools).contains(mat)) {
                                Enchantment r_t_e = tools_enchants[random.nextInt(tools_enchants.length)];
                                item.addUnsafeEnchantment(r_t_e, 36);
                            }
                            if (Arrays.asList(armor).contains(mat)) {
                                Enchantment r_a_e = armor_enchants[random.nextInt(armor_enchants.length)];
                                item.addUnsafeEnchantment(r_a_e, 36);
                            }
                            if (Arrays.asList(boots).contains(mat)) {
                                Enchantment r_b_e = boots_enchants[random.nextInt(boots_enchants.length)];
                                item.addUnsafeEnchantment(r_b_e, 36);
                            }
                            if (Arrays.asList(helmet).contains(mat)) {
                                Enchantment r_h_e = helmet_enchants[random.nextInt(helmet_enchants.length)];
                                item.addUnsafeEnchantment(r_h_e, 36);
                            }
                            if (Arrays.asList(trident).contains(mat)) {
                                Enchantment r_tr_e = trident_enchants[random.nextInt(trident_enchants.length)];
                                item.addUnsafeEnchantment(r_tr_e, 36);
                            }
                            if (Arrays.asList(shield).contains(mat)) {
                                Enchantment r_s_e = shield_enchants[random.nextInt(shield_enchants.length)];
                                item.addUnsafeEnchantment(r_s_e, 42);
                            }
                            if (Arrays.asList(bow).contains(mat)) {
                                Enchantment r_bo_e = bow_enchants[random.nextInt(bow_enchants.length)];
                                item.addUnsafeEnchantment(r_bo_e, 42);
                            }
                            if (Arrays.asList(crossbow).contains(mat)) {
                                Enchantment r_c_e = crossbow_enchants[random.nextInt(crossbow_enchants.length)];
                                if (r_c_e.equals(Enchantment.QUICK_CHARGE)) {
                                    item.addUnsafeEnchantment(r_c_e, 3);
                                    break;
                                }
                                item.addUnsafeEnchantment(r_c_e, 42d);
                            }
                        }
                    }
                }
            }

        }
    }

    private Material[] melee = new Material[] {
            Material.WOODEN_SWORD, Material.STONE_SWORD, Material.GOLDEN_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
            Material.FISHING_ROD
    };
    private Material[] armor = new Material[] {
            Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.GOLDEN_HELMET,
            Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS, Material.IRON_HELMET, Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS, Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS
    };
    private Material[] tools = new Material[] {
            Material.WOODEN_AXE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL, Material.WOODEN_HOE, Material.STONE_AXE, Material.STONE_PICKAXE,
            Material.STONE_SHOVEL, Material.STONE_HOE, Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL,
            Material.IRON_HOE, Material.IRON_SHOVEL, Material.IRON_PICKAXE, Material.IRON_AXE, Material.DIAMOND_SHOVEL,  Material.DIAMOND_HOE,
            Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_HOE, Material.NETHERITE_SHOVEL, Material.NETHERITE_PICKAXE,
            Material.NETHERITE_AXE, Material.FLINT_AND_STEEL, Material.SHEARS
    };
    private Material[] boots = new Material[] {
            Material.LEATHER_BOOTS, Material.GOLDEN_BOOTS, Material.IRON_BOOTS, Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS
    };
    private Material[] helmet = new Material[] {
            Material.LEATHER_HELMET, Material.GOLDEN_HELMET, Material.IRON_HELMET, Material.DIAMOND_HELMET, Material.NETHERITE_HELMET
    };
    private Material[] trident = new Material[] {
            Material.TRIDENT
    };
    private Material[] shield = new Material[] {
            Material.SHIELD, Material.ELYTRA
    };
    private Material[] bow = new Material[] {
            Material.BOW
    };
    private Material[] crossbow = new Material[] {
            Material.CROSSBOW
    };

    private Enchantment[] melee_enchants = new Enchantment[] {
            Enchantment.DURABILITY, Enchantment.MENDING, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD, Enchantment.DAMAGE_ARTHROPODS,
            Enchantment.FIRE_ASPECT, Enchantment.LOOT_BONUS_MOBS, Enchantment.KNOCKBACK, Enchantment.SWEEPING_EDGE
    };
    private Enchantment[] tools_enchants = new Enchantment[] {
            Enchantment.DURABILITY, Enchantment.MENDING, Enchantment.LOOT_BONUS_BLOCKS, Enchantment.SILK_TOUCH, Enchantment.DIG_SPEED,
    };
    private Enchantment[] armor_enchants = new Enchantment[] {
            Enchantment.DURABILITY, Enchantment.MENDING, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_PROJECTILE,
            Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.THORNS
    };
    private Enchantment[] boots_enchants = new Enchantment[]{
            Enchantment.DURABILITY, Enchantment.MENDING, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_PROJECTILE,
            Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.THORNS, Enchantment.PROTECTION_FALL,
            Enchantment.DEPTH_STRIDER, Enchantment.FROST_WALKER, Enchantment.SOUL_SPEED
    };
    private Enchantment[] helmet_enchants = new Enchantment[] {
            Enchantment.DURABILITY, Enchantment.MENDING, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_PROJECTILE,
            Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.THORNS, Enchantment.WATER_WORKER,
            Enchantment.OXYGEN
    };
    private Enchantment[] trident_enchants = new Enchantment[] {
            Enchantment.LOYALTY, Enchantment.RIPTIDE, Enchantment.DURABILITY, Enchantment.MENDING, Enchantment.IMPALING, Enchantment.CHANNELING
    };
    private Enchantment[] shield_enchants = new Enchantment[] {
            Enchantment.DURABILITY, Enchantment.MENDING, Enchantment.PROTECTION_ENVIRONMENTAL
    };
    private Enchantment[] bow_enchants = new Enchantment[] {
            Enchantment.DURABILITY, Enchantment.MENDING, Enchantment.ARROW_DAMAGE, Enchantment.ARROW_FIRE, Enchantment.ARROW_KNOCKBACK,
            Enchantment.ARROW_INFINITE
    };
    private Enchantment[] crossbow_enchants = new Enchantment[] {
            Enchantment.DURABILITY, Enchantment.MENDING, Enchantment.QUICK_CHARGE, Enchantment.PIERCING, Enchantment.MULTISHOT
    };
}
