package me.in4.voidfightz.events;

import me.in4.voidfightz.items.Items;
import me.in4.voidfightz.runnables.CheatArrowsRunnable;
import me.in4.voidfightz.runnables.InvulnerableClass;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

import static me.in4.voidfightz.VoidFightZ.plugin;
import static me.in4.voidfightz.runnables.WorldsRunClass.spawn_loc;
import static me.in4.voidfightz.runnables.WorldsRunClass.world1;


public class Kills implements Listener {

    private final HashMap<ItemMeta, Long> basalts_time;
    private final HashMap<UUID, Long> lastprint;
    private final HashMap<UUID, Long> boats_time;
    private final HashMap<Location, Location> portals;
    private final HashMap<Location, ArrayList> portals_arrays;
    private final HashMap<Location, ArrayList> portals_arrays1;
    private final HashMap<Location, ArrayList> portals_arrays2;
    private final HashMap<Location, Location> location_;
    private final HashMap<Location, Location> location_1;
    private final HashMap<Entity, Integer> kill_streak;
    private final HashMap<UUID, Long> cheat_swords_cooldown;

    public HashMap<Player, Integer> op_loot_count = new HashMap<>();
    public HashMap<Player, Integer> good_loot_count = new HashMap<>();
    public HashMap<Player, Integer> total_kills_count = new HashMap<>();

    Boolean check_block = true;
    public int counter = 0;

    ArrayList tnt_bows = new ArrayList();
    ArrayList tp_bows = new ArrayList();
    int k = 0;

    public Kills() {
        this.basalts_time = new HashMap<>();
        this.lastprint = new HashMap<>();
        this.boats_time = new HashMap<>();
        this.portals = new HashMap<>();
        this.portals_arrays = new HashMap<>();
        this.portals_arrays1 = new HashMap<>();
        this.portals_arrays2 = new HashMap<>();
        this.location_ = new HashMap<>();
        this.location_1 = new HashMap<>();
        this.kill_streak = new HashMap<>();
        this.cheat_swords_cooldown = new HashMap<>();
    }

    public int opLootCount (Player player) {
        op_loot_count.putIfAbsent(player, 0);
        return op_loot_count.get(player);
    }

    public int goodLootCount (Player player) {
        good_loot_count.putIfAbsent(player, 0);
        return good_loot_count.get(player);
    }

    public int totalKillsCount (Player player) {
        total_kills_count.putIfAbsent(player, 0);
        return total_kills_count.get(player);
    }

    public void getCount (HashMap<Player, Integer> hashMap, Player player) {
        if (!hashMap.containsKey(player)) {
            hashMap.put(player, 1);
            counter = 1;
        }
        else {
            hashMap.put(player, hashMap.get(player) + 1);
            counter++;
        }
    }

    private void setKillStreak (Player player, int kill_streak) {
        if (!this.kill_streak.containsKey(player)) {
            this.kill_streak.put(player, 1);
        }
        else {
            this.kill_streak.put(player, kill_streak);
        }
    }

    private int getKillStreak (Player player) {
        return this.kill_streak.getOrDefault(player, 0);
    }

    ArrayList<Location> no_portal_locs = new ArrayList<>();

    @EventHandler
    public void playerMoveEventsss (PlayerMoveEvent event) {
        if (k == 0) {
            int x = spawn_loc.getBlockX();
            int y = spawn_loc.getBlockY();
            int z = spawn_loc.getBlockZ();
            for (int dy = -8; dy <= 8; dy++) {
                for (int dx = -10; dx <= 10; dx++) {
                    for (int dz = -10; dz <= 10; dz++) {
                        Location ploc = new Location(world1, x + dx, y + dy, z + dz);
                        no_portal_locs.add(ploc);
                        k = 1;
                    }
                }
            }
        }
    }

    @EventHandler
    public void playersDeaths(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player slayer = event.getEntity().getKiller();
        setKillStreak(player, 0);
        int max_ks = 0;
        for (Player killers : world1.getPlayers()) {
            if (getKillStreak(killers) > max_ks) {
                max_ks = getKillStreak(killers);
                killers.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1));
            }
            else {
                if (killers.hasPotionEffect(PotionEffectType.GLOWING)) {
                    killers.removePotionEffect(PotionEffectType.GLOWING);
                }
            }
        }
        if (player.hasPotionEffect(PotionEffectType.GLOWING)) {
            player.removePotionEffect(PotionEffectType.GLOWING);
        }
        if (!(slayer instanceof Player)) {
            return;
        } else {
            if (Boolean.TRUE.equals(world1.getGameRuleValue(GameRule.KEEP_INVENTORY))) {
                getCount(total_kills_count, player);
            }
            Location loc = slayer.getLocation();
            this.setKillStreak(slayer, this.getKillStreak(slayer) + 1);
            int size = (int) player.getWorld().getWorldBorder().getSize();
            Random random = new Random();
            int random_int = arrays_types[random.nextInt(arrays_types.length)];
            if (random_int == 0) {
                Material random_item = lucky_items[random.nextInt(lucky_items.length)];
                if (Arrays.asList(lucky_items).contains(random_item)) {
                    ItemStack r_lucky_item = new ItemStack(random_item, 1);
                    if (r_lucky_item.getType().equals(Material.REDSTONE_BLOCK)) {
                        for (int cnt = 0; cnt <= 62; cnt++) {
                            loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                        }
                    } else if (r_lucky_item.getType().equals(Material.YELLOW_WOOL)) {
                        for (int cnt = 0; cnt <= 36; cnt++) {
                            loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                        }
                    } else if (r_lucky_item.getType().equals(Material.SLIME_BLOCK)) {
                        for (int cnt = 0; cnt <= 8; cnt++) {
                            loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                        }
                    } else if (r_lucky_item.getType().equals(Material.ARROW) || r_lucky_item.getType().equals(Material.HAY_BLOCK) ||
                            r_lucky_item.getType().equals(Material.OBSIDIAN) || r_lucky_item.getType().equals(Material.EXPERIENCE_BOTTLE) ||
                            r_lucky_item.getType().equals(Material.AMETHYST_BLOCK)) {
                        for (int cnt = 0; cnt <= 14; cnt++) {
                            loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                        }
                    }
                    else if (r_lucky_item.getType().equals(Material.SLIME_SPAWN_EGG) || r_lucky_item.getType().equals(Material.SKELETON_HORSE_SPAWN_EGG)) {
                        for (int cnt = 0; cnt <= 4; cnt++) {
                            loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                        }
                    }
                    else if (r_lucky_item.getType().equals(Material.ELYTRA)) {
                        r_lucky_item.setDurability((short) 427);
                        loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                        loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.FIREWORK_ROCKET, 4));
                    }
                    else if (r_lucky_item.getType().equals(Material.RAIL)) {
                        for (int cnt = 0; cnt < 3; cnt++) {
                            loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                            loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.TNT_MINECART));
                        }
                    }
                    else if (r_lucky_item.getType().equals(Material.FIRE_CHARGE)) {
                        ItemMeta meta = r_lucky_item.getItemMeta();
                        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Bedwars Fireball");
                        r_lucky_item.setItemMeta(meta);
                        this.fireballs.add(r_lucky_item);
                    } else if (r_lucky_item.getType().equals(Material.IRON_BOOTS)) {
                        r_lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                        r_lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
                        ItemMeta meta = r_lucky_item.getItemMeta();
                        meta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Jumping Boots");
                        r_lucky_item.setItemMeta(meta);
                        this.jumping_boots.add(r_lucky_item);
                    }
                    loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                }
            } else if (random_int == 1) {
                if (Boolean.TRUE.equals(world1.getGameRuleValue(GameRule.KEEP_INVENTORY))) {
                    getCount(op_loot_count, player);
                }
                Material random_enchanted_item = lucky_enchanted_items[random.nextInt(lucky_enchanted_items.length)];
                if (Arrays.asList(lucky_enchanted_items).contains(random_enchanted_item)) {
                    ItemStack lucky_item = new ItemStack(random_enchanted_item);
                    if (lucky_item.getType().equals(Material.GOLDEN_SWORD)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 11);

                    } else if (lucky_item.getType().equals(Material.IRON_CHESTPLATE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
                    } else if (lucky_item.getType().equals(Material.IRON_AXE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
                        lucky_item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
                        lucky_item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
                    } else if (lucky_item.getType().equals(Material.NETHERITE_LEGGINGS)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.THORNS, 4);
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

                    } else if (lucky_item.getType().equals(Material.DIAMOND_SWORD)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 4);
                        lucky_item.setDurability((short) 1500);
                    } else if (lucky_item.getType().equals(Material.TRIDENT)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.RIPTIDE, 5);
                        lucky_item.setDurability((short) 230);
                    } else if (lucky_item.getType().equals(Material.CROSSBOW)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.PIERCING, 1);
                        lucky_item.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 1);
                        for (int cnt = 0; cnt <= 16; cnt++) {
                            world1.dropItemNaturally(loc, new ItemStack(Material.ARROW));
                        }
                    } else if (lucky_item.getType().equals(Material.DIAMOND_PICKAXE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
                        lucky_item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
                        lucky_item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 7);

                    } else if (lucky_item.getType().equals(Material.CHAINMAIL_CHESTPLATE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.THORNS, 7);
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                    }
                    else if (lucky_item.getType().equals(Material.GOLDEN_LEGGINGS)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                    }
                    else if (lucky_item.getType().equals(Material.NETHERITE_HOE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 9);
                    }
                    else if (lucky_item.getType().equals(Material.DIAMOND_CHESTPLATE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 3);
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 7);
                    }
                    else if (lucky_item.getType().equals(Material.NETHERITE_HELMET)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.WATER_WORKER, 3);
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
                        lucky_item.addUnsafeEnchantment(Enchantment.OXYGEN, 5);
                    }
                    else if (lucky_item.getType().equals(Material.CHAINMAIL_BOOTS)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
                        lucky_item.addUnsafeEnchantment(Enchantment.SWIFT_SNEAK, 4);
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 5);
                    }
                    else if (lucky_item.getType().equals(Material.DIAMOND_AXE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 4);
                        lucky_item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 4);
                        lucky_item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
                    }
                    else if (lucky_item.getType().equals(Material.WOODEN_SHOVEL)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
                        lucky_item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                    }
                    else if (lucky_item.getType().equals(Material.WOODEN_AXE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
                        lucky_item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 5);
                    }
                    else if (lucky_item.getType().equals(Material.GOLDEN_HOE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
                        lucky_item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);

                    }
                    else if (lucky_item.getType().equals(Material.GOLDEN_PICKAXE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
                        lucky_item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 15);
                    }
                    loc.getWorld().dropItem(loc, lucky_item);

                }
            }
            if (random_int == 2) {
                if (Boolean.TRUE.equals(world1.getGameRuleValue(GameRule.KEEP_INVENTORY))) {
                    getCount(good_loot_count, player);
                }
                String occurrence = random_occurrences[random.nextInt(random_occurrences.length)];
                if (Arrays.asList(random_occurrences).contains(occurrence)) {
                    if (occurrence.equalsIgnoreCase("summon lightning")) {
                        world1.strikeLightning(slayer.getLocation());
                    } else if (occurrence.equalsIgnoreCase("make an arrow rain")) {
                        int x = slayer.getWorld().getWorldBorder().getCenter().getBlockX();
                        int z = slayer.getWorld().getWorldBorder().getCenter().getBlockZ();
                        int point1 = (int) (slayer.getWorld().getWorldBorder().getSize() / -2);
                        int point2 = (int) (slayer.getWorld().getWorldBorder().getSize() / 2);
                        for (int dx = point1; dx <= point2; dx++) {
                            for (int dz = point1; dz <= point2; dz++) {
                                Location delete_loc = new Location(slayer.getWorld(), x + dx, slayer.getLocation().getBlockY() + 64, z + dz);
                                int value = random.nextInt((100 - 1) + 1) + 1;
                                if (value >= 75) {
                                    player.getWorld().spawnArrow(delete_loc, new Vector(0, -1, 0), 2, 0);
                                }
                            }
                        }
                    } else if (occurrence.equalsIgnoreCase("build the rainbow penis")) {
                        int x = slayer.getLocation().getBlockX() - 1;
                        int y = slayer.getLocation().getBlockY() + 2;
                        for (int dy = 0; dy < rainbow_dick.length; dy++) {
                            for (int dx = 0; dx < rainbow_dick[dy].length; dx++) {
                                Location location = new Location(player.getWorld(), x + dx, y + dy, player.getLocation().getBlockZ());
                                Material material;
                                if (rainbow_dick[dy][dx] == 0) {
                                    material = Material.AIR;
                                } else if (rainbow_dick[dy][dx] == 1) {
                                    material = Material.COBWEB;
                                } else if (rainbow_dick[dy][dx] == 2) {
                                    material = Material.NETHERITE_BLOCK;
                                } else if (rainbow_dick[dy][dx] == 3) {
                                    material = Material.DIAMOND_BLOCK;
                                } else if (rainbow_dick[dy][dx] == 4) {
                                    material = Material.GOLD_BLOCK;
                                } else if (rainbow_dick[dy][dx] == 5) {
                                    material = Material.IRON_BLOCK;
                                } else {
                                    material = Material.SPONGE;
                                }
                                location.getBlock().setType(material);
                            }
                        }
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "SUS");
                    } else if (occurrence.equalsIgnoreCase("spawn a tanky skeleton")) {
                        Skeleton skeleton = (Skeleton) player.getWorld().spawnEntity(slayer.getLocation(), EntityType.SKELETON);
                        skeleton.getEquipment().setHelmet(skeleton_function(armor[0]));
                        skeleton.getEquipment().setChestplate(skeleton_function(armor[1]));
                        skeleton.getEquipment().setLeggings(skeleton_function(armor[2]));
                        skeleton.getEquipment().setBoots(skeleton_function(armor[3]));
                        skeleton.getEquipment().setItemInMainHand(skeleton_function(armor[4]));
                        skeleton.setCustomName(ChatColor.GRAY + "" + ChatColor.BOLD + "Tanky Skele");
                        this.tanky_skeletons.add(skeleton);
                    } else if (occurrence.equalsIgnoreCase("spawn a wool bridge")) {
                        ItemStack item = new ItemStack(Material.HAY_BLOCK, 1);
                        ItemMeta meta = item.getItemMeta();
                        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
                        meta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Bridge Hay Bale");
                        item.setItemMeta(meta);
                        if (slayer.getInventory().contains(item)) {
                            return;
                        } else {
                            this.bridge_basalts.add(item);
                            world1.dropItemNaturally(loc, item);
                        }
                    } else if (occurrence.equalsIgnoreCase("give player a fly boat")) {
                        ItemStack item = new ItemStack(Material.OAK_BOAT, 1);
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Flying Boat");
                        item.setItemMeta(meta);
                        this.flying_boats.add(item);
                        world1.dropItemNaturally(loc, item);
                    }

                    else if (occurrence.equalsIgnoreCase("make a portal")) {
                        int x = slayer.getLocation().getBlockX() - 1;
                        int y = slayer.getLocation().getBlockY();
                        int z = slayer.getLocation().getBlockZ() - 2;
                        Location p_l = slayer.getLocation();
                        Location location = new Location(slayer.getWorld(), x - 2, y - 1, z - 4);
                        if (!no_portal_locs.contains(location)) {
                            int x1 = spawn_loc.getBlockX();
                            int z1 = spawn_loc.getBlockZ();
                            int y1 = spawn_loc.getBlockY();

                            int value = random.nextInt(((size / 2) - (-size / 2)) + 1) + (-size / 2);
                            Location location1 = new Location(slayer.getWorld(), x1 + value, y1 + value, z1 + value);
                            if (no_portal_locs.contains(location1)) {
                                while (no_portal_locs.contains(location1)) {
                                    value = random.nextInt(((size / 2) - (-size / 2)) + 1) + (-size / 2);
                                    location1 = new Location(slayer.getWorld(), x1 + value, y1 + value, z1 + value);
                                }
                            } else {
                                Location location2 = new Location(world1, location1.getBlockX() + 2, location1.getBlockY() + 1, location1.getBlockZ());
                                ArrayList portal_arrays1 = new ArrayList();
                                ArrayList portal_arrays2 = new ArrayList();
                                ArrayList portal_arrays3 = new ArrayList();
                                ArrayList portal_arrays4 = new ArrayList();

                                for (int dy = 0; dy < portal.length; dy++) {
                                    for (int dx = 0; dx < portal[0].length; dx++) {
                                        Location location10 = new Location(slayer.getWorld(), x + dx, y + dy, z);
                                        Material material;
                                        if (portal[dy][dx] == 0) {
                                            material = Material.AIR;
                                            this.portals_locs.add(location10);
                                            this.portals.put(location10, location2);
                                            this.portals_arrays1.put(location10, portal_arrays1);
                                            this.portals_arrays2.put(location10, portal_arrays2);

                                        } else if (portal[dy][dx] == 1) {
                                            material = Material.COBBLESTONE;
                                            portal_arrays1.add(location10);

                                        } else {
                                            material = Material.OBSIDIAN;
                                            portal_arrays2.add(location10);

                                        }
                                        location10.getBlock().setType(material);

                                    }

                                }
                                portal_platform_loc(location);

                                int portal_x = location1.getBlockX();
                                int portal_y = location1.getBlockY();
                                int portal_z = location1.getBlockZ();

                                Location second_location = new Location(slayer.getWorld(), portal_x - 2, portal_y - 1, portal_z - 4);
                                Location second_tp_location = new Location(world1, p_l.getBlockX() + 2, p_l.getBlockY() + 1, p_l.getBlockZ() - 2);

                                for (int dy = 0; dy < portal.length; dy++) {
                                    for (int dx = 0; dx < portal[0].length; dx++) {
                                        Location location21 = new Location(slayer.getWorld(), portal_x + dx, portal_y + dy, portal_z);
                                        Material material;
                                        if (portal[dy][dx] == 0) {
                                            material = Material.AIR;
                                            this.portals_locs.add(location21);
                                            this.portals.put(location21, second_tp_location);
                                            this.portals_arrays1.put(location21, portal_arrays3);
                                            this.portals_arrays2.put(location21, portal_arrays4);

                                        } else if (portal[dy][dx] == 1) {
                                            material = Material.COBBLESTONE;
                                            portal_arrays3.add(location21);
                                        } else {
                                            material = Material.OBSIDIAN;
                                            portal_arrays4.add(location21);
                                        }

                                        location21.getBlock().setType(material);
                                    }
                                }
                                portal_platform_loc(second_location);
                            }
                        }
                    }
                }
            }
            if (this.kill_streak.get(slayer) == 5) {
                 if (!slayer.getInventory().contains(Items.TNT_BOW) && !slayer.getInventory().contains(Items.TP_BOW)) {
                    ItemStack tnt_bow = Items.TNT_BOW;
                    ItemStack tp_bow = Items.TP_BOW;
                    slayer.getInventory().addItem(tnt_bow);
                    slayer.getInventory().addItem(tp_bow);
                    this.tp_bows.add(tp_bow);
                    this.tnt_bows.add(tnt_bow);
                    for (int cnt = 0; cnt <= 6; cnt++) {
                        slayer.getInventory().addItem(new ItemStack(Material.TNT));
                        slayer.getInventory().addItem(new ItemStack(Material.ARROW));
                    }
                    for (int cnt = 0; cnt <= 8; cnt++) {
                        slayer.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
                    }
                }
            }
            else if (this.kill_streak.get(slayer) == 10) {
                if (!slayer.getInventory().contains(Items.OP_CROSS_BOW)) {
                    slayer.getInventory().addItem(Items.OP_CROSS_BOW);
                    for (int cnt = 0; cnt <= 16; cnt++) {
                        slayer.getInventory().addItem(new ItemStack(Material.ARROW));
                    }
                    this.cheat_crossbows.add(Items.OP_CROSS_BOW);
                }
            }
            else if (this.kill_streak.get(slayer) == 15) {
                if (!slayer.getInventory().contains(Items.CHEAT_SWORD)) {
                    slayer.getInventory().addItem(Items.CHEAT_SWORD);
                    slayer.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING));
                    this.cheat_swords.add(Items.CHEAT_SWORD);
                }
            }
            else if (this.kill_streak.get(slayer) == 20) {
                Location location = slayer.getLocation();
                Location another_location = new Location(world1, location.getBlockX(), location.getBlockY() + 15, location.getBlockZ());
                ArmorStand armorStand = (ArmorStand) world1.spawnEntity(another_location, EntityType.ARMOR_STAND);
                armorStand.getEquipment().setHelmet(Items.DRAGON_HEAD);
                this.dragon_heads.add(Items.DRAGON_HEAD);
                slayer.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "Good job for reaching kill streak 20! Your are granted with an extra " +
                        "life when the game will go into hardcore!");
                this.ks20_players.add(slayer);

            }
            if (this.kill_streak.get(slayer) % 5 == 0) {
                Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "" + slayer.getName() + " is on a " +
                        this.getKillStreak(slayer) + " kill streak!");
            }

        }
    }

    ArrayList<ItemStack> cheat_crossbows = new ArrayList<>();
    ArrayList<ItemStack> cheat_swords = new ArrayList<>();
    ArrayList<ItemStack> dragon_heads = new ArrayList<>();

    @EventHandler
    public void pDied (PlayerDeathEvent event) {
        if (this.kill_streak.containsKey(event.getEntity())) {
            if (this.ks20_players.contains(event.getEntity())) {
                if (Boolean.FALSE.equals(world1.getGameRuleValue(GameRule.KEEP_INVENTORY))) {
                    Bukkit.broadcastMessage(ChatColor.RED + "" + event.getEntity().getName() + " has used their extra life!");
                    event.setKeepInventory(true);
                    event.getDrops().clear();
                    event.setKeepLevel(true);
                }
                else {
                    setKillStreak(event.getEntity(), 0);
                }
            }
            else {
                setKillStreak(event.getEntity(), 0);
            }
        }
    }

    @EventHandler
    public void deathEvent (PlayerRespawnEvent event) {

        if (Boolean.FALSE.equals(world1.getGameRuleValue(GameRule.KEEP_INVENTORY))) {
            if (this.ks20_players.contains(event.getPlayer())) {
                this.ks20_players.remove(event.getPlayer());
                setKillStreak(event.getPlayer(), 0);
            }
            else {
                event.getPlayer().sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "You have been eliminated...");
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Player p = event.getPlayer();
                        p.setGameMode(GameMode.SPECTATOR);
                    }
                }.runTaskLater(plugin, 1);
            }
            event.setRespawnLocation(spawn_loc);
        }
        else {
            while (true) {
                int x = (int) ((Math.random() * (50 - (-50))) + (-50));
                int z = (int) ((Math.random() * (50 - (-50))) + (-50));
                int y = (int) ((Math.random() * (320 - (-64))) + (-64));
                Location loc = new Location(world1, x, y, z);
                Location b_loc = new Location(world1, x, y - 1, z);
                Block block_below = b_loc.getBlock();
                if (block_below.getType().isAir() || loc.getBlock().getType().isSolid()) {
                    while (y >= -63 && y <= 320) {
                        if (!(block_below.getType().isAir()) || !(loc.getBlock().getType().isSolid())) {
                            break;
                        }
                        y--;
                    }
                }
                else {
                    event.setRespawnLocation(loc);
                    break;
                }
            }
        }
    }

    @EventHandler
    public void OpCrossbowEvent (EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        else {
            if (event.getProjectile() instanceof Arrow) {
                Arrow arrow = (Arrow) event.getProjectile();
                Location shooter_loc = player.getLocation();
                if (Objects.requireNonNull(event.getBow()).getItemMeta().getDisplayName().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Cheat Crossbow")) {
                    double lowest_distance = Double.MAX_VALUE;
                    Player pray = null;
                    CheatArrowsRunnable cheatArrowsRunnable = null;
                    for (Entity target : player.getNearbyEntities(50, 320, 50)) {
                        if (target instanceof Player) {
                            Location target_loc = target.getLocation();
                            double distance = shooter_loc.distance(target_loc);
                            if (distance < lowest_distance) {
                                lowest_distance = distance;
                                pray = (Player) target;
                                cheatArrowsRunnable = new CheatArrowsRunnable(pray, player, arrow, shooter_loc);
                            }
                        }
                    }
                    if (pray == null) {
                        return;
                    } else {
                        cheatArrowsRunnable.runTaskTimer(plugin, 1, 1);
                    }
                }
            }
        }
    }

    @EventHandler
    public void CHEAT_SWORD_EVENT (PlayerInteractEvent event) {
        Action a = event.getAction();
        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            Location p_l = player.getLocation();
            if (player.getInventory().getItemInMainHand().getItemMeta() == null) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "Cheat Sword")) {
                if (player.getTargetBlockExact(16) != null) {
                    Location desired_l = Objects.requireNonNull(player.getTargetBlockExact(16)).getLocation();
                    int x = desired_l.getBlockX();
                    int y = desired_l.getBlockY();
                    int z = desired_l.getBlockZ();
                    if (!(this.cheat_swords_cooldown.containsKey(player.getUniqueId()))) {
                        player.setVelocity(new Vector(x - p_l.getBlockX(), y - p_l.getBlockY(), z - p_l.getBlockZ()).normalize().multiply(1.5));
                        this.cheat_swords_cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    }
                    else {
                        Long timeElapsed = (System.currentTimeMillis() - this.cheat_swords_cooldown.get(player.getUniqueId())) / 1000;
                        if (timeElapsed <= 0.5) {
                            return;
                        } else {
                            player.setVelocity(new Vector(x - p_l.getBlockX(), y - p_l.getBlockY(), z - p_l.getBlockZ()).normalize().multiply(1.5));
                            this.cheat_swords_cooldown.remove(player.getUniqueId());
                        }
                    }
                }

            }
        }
    }

    @EventHandler
    public void dHeadEvent (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getEquipment().getHelmet() == null) {
            return;
        }
        else if (!Objects.equals(player.getEquipment().getHelmet(), Items.DRAGON_HEAD)) {
            return;
        }
        else {
            if (player.isSneaking()) {
                if (player.getCooldown(Items.DRAGON_HEAD.getType()) == 0) {
                    world1.spawn(player.getEyeLocation(), DragonFireball.class);
                    player.setCooldown(Items.DRAGON_HEAD.getType(), 200);
                }
            }
        }
    }

    @EventHandler
    public void BoatOrHayBaleSpawned (ItemSpawnEvent event) {
        if (this.flying_boats.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Flying Boat");
            event.getEntity().setCustomNameVisible(true);
        }
        else if (this.flying_boats.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Bridge Hay Bale");
            event.getEntity().setCustomNameVisible(true);
        }
        else if (this.fireballs.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Bedwars Fireball");
            event.getEntity().setCustomNameVisible(true);
        }
        else if (this.tnt_bows.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "TNT Bow");
            event.getEntity().setCustomNameVisible(true);
        }
        else if (this.tp_bows.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Teleport Bow");
            event.getEntity().setCustomNameVisible(true);
        }
        else if (this.cheat_crossbows.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.GOLD + "" + ChatColor.BOLD + "Cheat Crossbow");
            event.getEntity().setCustomNameVisible(true);
        }
        else if (this.cheat_swords.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.GOLD + "" + ChatColor.BOLD + "Cheat Sword");
            event.getEntity().setCustomNameVisible(true);
        }
        else if (this.cheat_swords.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Dragon Head");
            event.getEntity().setCustomNameVisible(true);
        }
    }

    @EventHandler
    public void playerHasDecidedToMove (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        Location location = new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        Location location_below = new Location(world1, location.getBlockX(), location.getBlockY() - 1, location.getBlockZ());
        if (!(this.portals_locs.contains(location))) {
            return;
        }

        else {
            for (int cnt = 0; cnt < this.portals_arrays1.get(location).size(); cnt++) {
                Location block_loc = (Location) this.portals_arrays1.get(location).get(cnt);
                Material type = block_loc.getBlock().getType();
                if (type != Material.COBBLESTONE) {
                    if (!(this.lastprint.containsKey(player.getUniqueId())) || (System.currentTimeMillis() - this.lastprint.get(player.getUniqueId()) >= 5000)) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "The portal is broken! Repair it using correct blocks, " +
                                "by placing them in a shape similar to a nether portal.");

                        this.lastprint.put(player.getUniqueId(), System.currentTimeMillis());
                    }
                    this.check_block = false;

                }

            }
            for (int cnt = 0; cnt < this.portals_arrays2.get(location).size(); cnt++) {
                Location block_loc = (Location) this.portals_arrays2.get(location).get(cnt);
                Material type = block_loc.getBlock().getType();
                if (type != Material.OBSIDIAN) {
                    if (!(this.lastprint.containsKey(player.getUniqueId())) || (System.currentTimeMillis() - this.lastprint.get(player.getUniqueId()) >= 5000)) {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "The portal is broken! Repair it using correct blocks, " +
                                "by placing them in a shape similar to a nether portal.");

                        this.lastprint.put(player.getUniqueId(), System.currentTimeMillis());
                    }
                    this.check_block = false;
                }
            }

            if (!this.check_block) {
                this.check_block = true;
            }
            else {
                if (!(this.lastprint.containsKey(player.getUniqueId())) || (System.currentTimeMillis() - this.lastprint.get(player.getUniqueId()) >= 3000)) {
                    player.teleport(this.portals.get(location));
                    this.lastprint.put(player.getUniqueId(), System.currentTimeMillis());
                }
            }


        }
    }

    @EventHandler
    public void playerHaveDecidedToMove (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ItemStack bridge_basalt = player.getInventory().getItemInMainHand();
        if (bridge_basalt == null) {
            return;
        }
        else if (!(this.bridge_basalts.contains(bridge_basalt))) {
            return;
        }

        else {
            Location location = player.getLocation();
            Location block_loc = new Location(player.getWorld(), location.getBlockX(), location.getBlockY() - 1, location.getBlockZ());
            Block block = block_loc.getBlock();
            if (!(block.getType().equals(Material.AIR))) {
                if (this.basalts_time.containsKey(bridge_basalt.getItemMeta())) {
                    this.basalts_time.remove(bridge_basalt.getItemMeta(), System.currentTimeMillis());
                }
                return;
            }
            else {
                if (!(this.basalts_time.containsKey(bridge_basalt.getItemMeta()))) {
                    this.basalts_time.put(bridge_basalt.getItemMeta(), System.currentTimeMillis());
                }
                else {
                    if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "Bridge Hay Bale")) {
                        long timeElapsed = (System.currentTimeMillis() - this.basalts_time.get(bridge_basalt.getItemMeta())) / 1000;
                        long timeLeft = 30 - timeElapsed;
                        if (timeElapsed <= 30) {
                            block.setType(Material.HAY_BLOCK);
                            if (timeLeft <= 5 && timeLeft >= 0) {
                                if (!(this.lastprint.containsKey(player.getUniqueId())) || (System.currentTimeMillis() - this.lastprint.get(player.getUniqueId()) >= 1000)) {
                                    player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "YOUR BRIDGE HAY BALE WILL DISAPPEAR IN " + timeLeft +
                                            " SECONDS!!!");
                                    this.lastprint.put(player.getUniqueId(), System.currentTimeMillis());
                                }
                            }
                        } else {
                            player.getInventory().remove(player.getInventory().getItemInMainHand());
                            this.basalts_time.remove(bridge_basalt.getItemMeta());
                            this.bridge_basalts.remove(bridge_basalt);
                        }
                    }
                }

            }
        }

    }

    @EventHandler
    public void onHit (ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        Entity entity = event.getHitEntity();
        Entity shooter = (Entity) projectile.getShooter();
        if (!(entity instanceof Player)) {
            return;
        }
        else if (!(entity.getType().equals(EntityType.PLAYER))) {
            return;
        }
        else {
            if (!(this.tanky_skeletons.contains(shooter))) {
                return;
            }
            else {
                Location location = projectile.getLocation();
                Random random = new Random();
                int value = random.nextInt((100 - 1) + 1) + 1;

                if (value < 25) {
                    Entity enderman = location.getWorld().spawnEntity(location, EntityType.ENDERMAN);
                    enderman.setCustomName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Pearl Giver");
                    enderman.setCustomNameVisible(true);
                }
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (event.getEntity().getKiller() == null) {
            return;
        }
        if (!(this.tanky_skeletons.contains(entity))) {
            return;
        }
        else {
            Location entity_location = entity.getLocation();
            for (int cnt = 0; cnt <= 2; cnt++) {
                entity.getWorld().dropItemNaturally(entity_location, new ItemStack(Material.END_PORTAL_FRAME));
            }
        }
    }


    @EventHandler
    public void playerInteracted (PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (!(this.flying_boats.contains(player.getInventory().getItemInMainHand()))) {
            return;
        }
        else {
            Location location = event.getClickedBlock().getLocation();
            Location new_loc = new Location(location.getWorld(), location.getBlockX(), location.getBlockY() + 1, location.getBlockZ());
            this.flying_boats_locs.add(new_loc);
        }
    }

    @EventHandler
    public void VehicleSpawn(VehicleCreateEvent event) {
        Vehicle boat = event.getVehicle();
        Location location = boat.getLocation();
        Location location1 = new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
        if (!(this.flying_boats_locs.contains((location1)))) {
            return;
        }
        else {
            boat.setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Flying Boat");
            boat.setCustomNameVisible(true);
            this.vehicle_flying_boats.add(boat);

        }
    }

    @EventHandler
    public void anotherMoveThatPlayerDid(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.isInsideVehicle()) {
            Vehicle vehicle = (Vehicle) player.getVehicle();
            if (!(this.vehicle_flying_boats.contains(vehicle))) {
                return;
            }
            else {
                if (!(this.boats_time.containsKey(vehicle.getUniqueId()))) {
                    this.boats_time.put(vehicle.getUniqueId(), System.currentTimeMillis());

                }
                else {
                    vehicle.setVelocity(player.getLocation().getDirection());
                    long timeElapsed = (System.currentTimeMillis() - this.boats_time.get(vehicle.getUniqueId())) / 1000;
                    long timeLeft = 30 - timeElapsed;
                    if (timeLeft <= 5 && timeLeft >= 0) {
                        if (!(this.lastprint.containsKey(player.getUniqueId())) || (System.currentTimeMillis() - this.lastprint.get(player.getUniqueId()) >= 1000)) {
                            this.lastprint.put(player.getUniqueId(), System.currentTimeMillis());
                            player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "YOUR BOAT WILL STOP FLYING " + timeLeft +
                                    " SECONDS!!!");
                        }
                    } else if (timeElapsed >= 30) {
                        vehicle.setVelocity(new Vector(0, 0, 0));
                        this.boats_time.remove((vehicle.getUniqueId()));
                        this.vehicle_flying_boats.remove(vehicle);
                    }

                }
            }
        }
    }

    @EventHandler
    public void onExit (VehicleExitEvent event) {
        Vehicle vehicle = event.getVehicle();
        if (vehicle.getCustomName() == null) {
            return;
        }

        else if (vehicle.getCustomName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Flying Boat")){
            vehicle.remove();

        }

    }

    private ItemStack skeleton_function(Material material) {
        ItemStack item = new ItemStack(material);
        if (!(material.equals(Material.BOW))) {
            item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        }
        else {
            item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 4);
        }
        return item;
    }

    private Material[] lucky_items = new Material[] {
            Material.NETHERITE_LEGGINGS, Material.NETHERITE_AXE, Material.DIAMOND_AXE, Material.DIAMOND_SWORD, Material.NETHERITE_BLOCK,
            Material.ENCHANTED_GOLDEN_APPLE, Material.IRON_AXE, Material.ANVIL, Material.TRIDENT, Material.NETHERITE_CHESTPLATE,
            Material.DIAMOND_BLOCK, Material.SMITHING_TABLE, Material.CROSSBOW, Material.FISHING_ROD, Material.IRON_CHESTPLATE,
            Material.WATER_BUCKET, Material.BEDROCK, Material.ARROW, Material.RAIL, Material.DIAMOND_HELMET,
            Material.HAY_BLOCK, Material.SLIME_BLOCK, Material.SADDLE, Material.SKELETON_HORSE_SPAWN_EGG,
            Material.ELYTRA, Material.SPAWNER, Material.OBSIDIAN,
            Material.POWDER_SNOW_BUCKET, Material.GOLDEN_APPLE, Material.IRON_BOOTS,

    };

    private Material[] lucky_enchanted_items = new Material[] {
            Material.NETHERITE_LEGGINGS, Material.TRIDENT, Material.IRON_CHESTPLATE, Material.IRON_AXE, Material.DIAMOND_SWORD,
            Material.CROSSBOW, Material.GOLDEN_SWORD, Material.DIAMOND_PICKAXE, Material.DIAMOND_AXE, Material.GOLDEN_LEGGINGS, Material.NETHERITE_HOE,
            Material.DIAMOND_CHESTPLATE, Material.NETHERITE_HELMET, Material.CHAINMAIL_BOOTS, Material.WOODEN_SHOVEL, Material.GOLDEN_HOE,
            Material.WOODEN_AXE, Material.GOLDEN_PICKAXE, Material.CHAINMAIL_CHESTPLATE, Material.ENCHANTED_GOLDEN_APPLE
    };
    //  Material.GOLDEN_LEGGINGS, Material.NETHERITE_HOE,
    //     Material.DIAMOND_CHESTPLATE, Material.NETHERITE_HELMET, Material.CHAINMAIL_BOOTS, Material.Diamond_AXE, Material.WOODEN_SHOVEL,
    //     Material.Golden_hoe
    //Material.WOODEN_AXE MAterial.Golden_pickaxe

    ArrayList jumping_boots = new ArrayList();
    ArrayList flying_boats_locs = new ArrayList();
    ArrayList flying_boats = new ArrayList();
    ArrayList vehicle_flying_boats = new ArrayList();
    ArrayList tanky_skeletons = new ArrayList();
    ArrayList bridge_basalts = new ArrayList();
    ArrayList fireballs = new ArrayList();
    ArrayList portals_locs = new ArrayList();
    ArrayList<Player> ks20_players = new ArrayList<>();

    private String[] random_occurrences = new String[] {
            "build the rainbow penis", "spawn a tanky skeleton",
            "give player a fly boat", "bedwars fireball", "spawn a wool bridge",
            "make a portal",
    };

    private Material[] armor = new Material[] {
            Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS, Material.BOW
    };

    private Material[] equipment_armor = new Material[] {
            Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.IRON_CHESTPLATE, Material.DIAMOND_HELMET, Material.GOLDEN_LEGGINGS,
            Material.GOLDEN_HELMET, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_HELMET, Material.DIAMOND_BOOTS,
    };


    int[][] rainbow_dick = {
            {0, 1, 0},
            {0, 2, 0},
            {0, 3, 0},
            {0, 4, 0},
            {0, 5, 0},
            {0, 5, 0},
            {0, 5, 0},
            {0, 5, 0},
            {6, 0, 6},
    };

    int[][] portal = {
            {2, 2, 2, 2, 2},
            {2, 0, 0, 0, 2},
            {2, 0, 0, 0, 2},
            {1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1}
    };

    int[][] portal_platform = {
            {0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0}

    };

    private void portal_platform_loc(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        for (int dx = 0; dx < portal_platform.length; dx++) {
            for (int dz = 0; dz < portal_platform[0].length; dz++) {
                Location location10 = new Location(location.getWorld(), x + dx, y, z + dz);
                Material material;
                if (portal_platform[dx][dz] == 0) {
                    material = Material.AIR;
                }
                else {
                    material = Material.BEDROCK;

                }

                location10.getBlock().setType(material);

            }

        }

    }

    private final Integer[] arrays_types = new Integer[] {
            0, 0, 0, 0, 1, 2, 2,
    };
}