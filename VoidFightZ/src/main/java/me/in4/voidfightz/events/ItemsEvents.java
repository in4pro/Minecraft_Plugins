package me.in4.voidfightz.events;

import me.in4.voidfightz.runnables.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

import static me.in4.voidfightz.items.PotionOfLevitation.potion_of_levitation;
import static me.in4.voidfightz.VoidFightZ.plugin;
import static me.in4.voidfightz.runnables.WorldsRunClass.spawn_loc;
import static me.in4.voidfightz.runnables.WorldsRunClass.world1;



public class ItemsEvents implements Listener {

    private HashMap<ItemMeta, Long> basalts_time;
    private HashMap<UUID, Long> lastprint;
    private HashMap<UUID, Long> boats_time;
    private HashMap<Location, Location> portals;
    private HashMap<Location, ArrayList> portals_arrays1;
    private HashMap<Location, ArrayList> portals_arrays2;
    private HashMap<Player, Projectile> tp_map;
    public HashMap<Player, Integer> op_loot_count = new HashMap<>();
    public HashMap<Player, Integer> good_loot_count = new HashMap<>();
    public HashMap<Player, Integer> lucky_blocks_broken_count = new HashMap<>();

    Block lucky_block;
    Boolean check_block = true;
    Boolean check_inventory = false;
    int k = 0;
    public int counter = 0;

    public ItemsEvents() {
        this.basalts_time = new HashMap<>();
        this.lastprint = new HashMap<>();
        this.boats_time = new HashMap<>();
        this.portals = new HashMap<>();
        this.portals_arrays1 = new HashMap<>();
        this.portals_arrays2 = new HashMap<>();
        this.tp_map = new HashMap<>();
    }

    public int opLootCount (Player player) {
        op_loot_count.putIfAbsent(player, 0);
        return op_loot_count.get(player);
    }

    public int goodLootCount (Player player) {
        good_loot_count.putIfAbsent(player, 0);
        return good_loot_count.get(player);
    }

    public int luckyBlocksCount (Player player) {
        lucky_blocks_broken_count.putIfAbsent(player, 0);
        return lucky_blocks_broken_count.get(player);
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

    ArrayList<Location> no_portal_locs = new ArrayList<>();

    @EventHandler
    public void playerMoveEventsss (PlayerMoveEvent event) {
        if (k == 0) {
            Player player = event.getPlayer();
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
    public void onPJoin (PlayerJoinEvent event) {
        if (event.getPlayer().getWorld().equals(world1)) {
            return;
        }
        else {
            event.getPlayer().setInvulnerable(true);
        }
    }

    @EventHandler
    public void blockBreaker(BlockBreakEvent event) {
        Block lucky_block = event.getBlock();
        Player player = event.getPlayer();
        if (!(lucky_block.getType().equals(Material.SPONGE) || lucky_block.getType().equals(Material.WET_SPONGE))) {
            return;
        } else {
            if (Boolean.TRUE.equals(world1.getGameRuleValue(GameRule.KEEP_INVENTORY))) {
                getCount(lucky_blocks_broken_count, player);
            }
            player.sendMessage(lucky_blocks_broken_count.get(player) + "l");
            int size = (int) player.getWorld().getWorldBorder().getSize();
            this.lucky_block = lucky_block;
            Random random = new Random();
            Location loc = lucky_block.getLocation();
            int random_int = arrays_types[random.nextInt(arrays_types.length)];
            if (random_int == 0) {
                Material random_item = lucky_items[random.nextInt(lucky_items.length)];
                if (Arrays.asList(lucky_items).contains(random_item)) {
                    ItemStack r_lucky_item = new ItemStack(random_item, 1);
                    if (r_lucky_item.getType().equals(Material.BEDROCK) || r_lucky_item.getType().equals(Material.REDSTONE_BLOCK)) {
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
                            r_lucky_item.getType().equals(Material.ACACIA_DOOR) || r_lucky_item.getType().equals(Material.OAK_TRAPDOOR) ||
                            r_lucky_item.getType().equals(Material.IRON_DOOR) || r_lucky_item.getType().equals(Material.BARRIER) ||
                            r_lucky_item.getType().equals(Material.BARREL) ||
                            r_lucky_item.getType().equals(Material.OBSIDIAN) || r_lucky_item.getType().equals(Material.EXPERIENCE_BOTTLE) ||
                            r_lucky_item.getType().equals(Material.AMETHYST_BLOCK)) {
                        for (int cnt = 0; cnt <= 14; cnt++) {
                            loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                        }
                    }

                    else if (r_lucky_item.getType().equals(Material.RAIL)) {
                        for (int cnt = 0; cnt <= 3; cnt++) {
                            loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                            loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.TNT_MINECART));
                        }
                    }
                    else if (r_lucky_item.getType().equals(Material.SLIME_SPAWN_EGG) || r_lucky_item.getType().equals(Material.SKELETON_HORSE_SPAWN_EGG)
                            || r_lucky_item.getType().equals(Material.END_PORTAL_FRAME)) {
                        for (int cnt = 0; cnt <= 4; cnt++) {
                            loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                        }
                    } else if (r_lucky_item.getType().equals(Material.ELYTRA)) {
                        r_lucky_item.setDurability((short) 427);
                        loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                        loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.FIREWORK_ROCKET, 4));

                    } else if (r_lucky_item.getType().equals(Material.IRON_BOOTS)) {
                        r_lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
                        r_lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
                        ItemMeta meta = r_lucky_item.getItemMeta();
                        meta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Jumping Boots");
                        r_lucky_item.setItemMeta(meta);
                        this.jumping_boots.add(r_lucky_item);
                        loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                    }
                    loc.getWorld().dropItemNaturally(loc, r_lucky_item);
                }
            } else if (random_int == 1) {
                if (Boolean.TRUE.equals(world1.getGameRuleValue(GameRule.KEEP_INVENTORY))) {
                    getCount(op_loot_count, player);
                }

                player.sendMessage(op_loot_count.get(player) + "o");
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

                    loc.getWorld().dropItemNaturally(loc, lucky_item);
                }
            }
            if (random_int == 2) {
                if (Boolean.TRUE.equals(world1.getGameRuleValue(GameRule.KEEP_INVENTORY))) {
                    getCount(good_loot_count, player);
                }

                player.sendMessage(good_loot_count.get(player) + "g");

                String occurrence = random_occurrences[random.nextInt(random_occurrences.length)];
                if (Arrays.asList(random_occurrences).contains(occurrence)) {
                    if (occurrence.equalsIgnoreCase("summon lightning")) {
                        for (Player players : world1.getPlayers()) {
                            players.getWorld().strikeLightning(players.getLocation());

                        }
                    } else if (occurrence.equalsIgnoreCase("make an arrow rain")) {
                        int x = player.getWorld().getWorldBorder().getCenter().getBlockX();
                        int z = player.getWorld().getWorldBorder().getCenter().getBlockZ();
                        int point1 = (int) (player.getWorld().getWorldBorder().getSize() / -2);
                        int point2 = (int) (player.getWorld().getWorldBorder().getSize() / 2);
                        for (int dx = point1; dx <= point2; dx++) {
                            for (int dz = point1; dz <= point2; dz++) {
                                Location delete_loc = new Location(player.getWorld(), x + dx, player.getLocation().getBlockY() + 64, z + dz);
                                int value = random.nextInt((100 - 1) + 1) + 1;
                                if (value >= 75) {
                                    player.getWorld().spawnArrow(delete_loc, new Vector(0, -1, 0), 2, 0);
                                }
                            }
                        }
                    } else if (occurrence.equalsIgnoreCase("build the rainbow penis")) {
                        int x = player.getLocation().getBlockX() - 1;
                        int y = player.getLocation().getBlockY() + 2;
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
                        Skeleton skeleton = (Skeleton) player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
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
                        if (player.getInventory().contains(item)) {
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
                    else if (occurrence.equalsIgnoreCase("bedwars fireball")) {
                        ItemStack item = new ItemStack(Material.FIRE_CHARGE, 1);
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Bedwars Fireball");
                        item.setItemMeta(meta);
                        this.fireballs.add(item);
                        for (int i = 0; i <= 2; i++) {
                            world1.dropItemNaturally(loc, item);
                        }
                    }
                    else if (occurrence.equalsIgnoreCase("make a portal")) {
                        int x = player.getLocation().getBlockX() - 1;
                        int y = player.getLocation().getBlockY();
                        int z = player.getLocation().getBlockZ() - 2;
                        Location p_l = player.getLocation();
                        Location location = new Location(player.getWorld(), x - 2, y - 1, z - 4);
                        if (!no_portal_locs.contains(location)) {
                            int x1 = spawn_loc.getBlockX();
                            int z1 = spawn_loc.getBlockZ();
                            int y1 = spawn_loc.getBlockY();

                            int value = random.nextInt(((size / 2) - (-size / 2)) + 1) + (-size / 2);
                            Location location1 = new Location(player.getWorld(), x1 + value, y1 + value, z1 + value);
                            if (no_portal_locs.contains(location1)) {
                                while (no_portal_locs.contains(location1)) {
                                    value = random.nextInt(((size / 2) - (-size / 2)) + 1) + (-size / 2);
                                    location1 = new Location(player.getWorld(), x1 + value, y1 + value, z1 + value);
                                }
                            } else {
                                Location location2 = new Location(world1, location1.getBlockX() + 2, location1.getBlockY() + 1, location1.getBlockZ());
                                ArrayList portal_arrays1 = new ArrayList();
                                ArrayList portal_arrays2 = new ArrayList();
                                ArrayList portal_arrays3 = new ArrayList();
                                ArrayList portal_arrays4 = new ArrayList();

                                for (int dy = 0; dy < portal.length; dy++) {
                                    for (int dx = 0; dx < portal[0].length; dx++) {
                                        Location location10 = new Location(player.getWorld(), x + dx, y + dy, z);
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

                                Location second_location = new Location(player.getWorld(), portal_x - 2, portal_y - 1, portal_z - 4);
                                Location second_tp_location = new Location(world1, p_l.getBlockX() + 2, p_l.getBlockY() + 1, p_l.getBlockZ() - 2);

                                for (int dy = 0; dy < portal.length; dy++) {
                                    for (int dx = 0; dx < portal[0].length; dx++) {
                                        Location location21 = new Location(player.getWorld(), portal_x + dx, portal_y + dy, portal_z);
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
        }
    }
    @EventHandler
    public void itemSpawned (ItemSpawnEvent event) {
        if (this.flying_boats.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Flying Boat");
            event.getEntity().setCustomNameVisible(true);
        }
        else if (this.bridge_basalts.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Bridge Hay Bale");
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
        else if (this.fireballs.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Bedwars Fireball");
            event.getEntity().setCustomNameVisible(true);
        }
        else if (event.getEntity().getItemStack().getType().equals(Material.PLAYER_HEAD)) {
            event.getEntity().remove();
        }
    }

    @EventHandler
    public void playerWasMoving (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        Location location = new Location(player.getWorld(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
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
    public void playerMove (PlayerMoveEvent event) {
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
    public void onBlockFromTo(BlockFromToEvent event) {
        Block water = event.getBlock();
        if (water.getType().equals(Material.WATER) || event.getToBlock().getType().equals(Material.AIR)) {
            event.setCancelled(true);
        }
        else if (water.getType().equals(Material.LAVA) || event.getToBlock().getType().equals(Material.AIR)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void placeBlock (BlockPlaceEvent event) {
        if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "Bridge Hay Bale")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void TNT_BOW_EVENT (EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Entity arrow = event.getProjectile();
            Player player = (Player) event.getEntity();
            if (event.getBow().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "" + ChatColor.BOLD + "TNT Bow")) {
                Material material = Material.TNT;
                if (player.getInventory().contains(material)) {
                    TNTPrimed tnt = arrow.getWorld().spawn(arrow.getLocation(), TNTPrimed.class);
                    tnt.setVelocity(arrow.getVelocity().normalize());
                    event.setProjectile(tnt);
                    new TNTRunClass(tnt).runTaskTimer(plugin, 1, 1);
                    for (int cnt = 0; cnt < player.getInventory().getSize(); cnt++) {
                        if (player.getInventory().getItem(cnt) == null) {
                            continue;
                        }
                        else {
                            ItemStack item = player.getInventory().getItem(cnt);

                            if (item.getType().equals(material)) {
                                item.setAmount(item.getAmount() - 1);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void TP_BOW_EVENT (EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getBow().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Teleport Bow")) {
                if (player.getInventory().contains(Material.ENDER_PEARL)) {
                    this.check_inventory = true;

                    this.tp_map.put(player, (Projectile) event.getProjectile());
                    this.tp_bow_players.add(player);
                    for (int cnt = 0; cnt < player.getInventory().getSize(); cnt++) {
                        if (player.getInventory().getItem(cnt) == null) {
                            continue;
                        } else {
                            ItemStack item = player.getInventory().getItem(cnt);

                            if (item.getType().equals(Material.ENDER_PEARL)) {
                                item.setAmount(item.getAmount() - 1);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void TP_BOW_HIT_EVENT (ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Arrow) && !(event.getEntity().getShooter() instanceof Player)) {
            return;
        }
        else {
            if (this.check_inventory) {
                Player player = (Player) event.getEntity().getShooter();
                assert player != null;
                Location arrow_loc = event.getEntity().getLocation();
                Location location = player.getLocation();

                double size = world1.getWorldBorder().getSize() / 2;
                int x = world1.getWorldBorder().getCenter().getBlockX();
                int z = world1.getWorldBorder().getCenter().getBlockZ();
                int x1 = arrow_loc.getBlockX();
                int z1 = arrow_loc.getBlockZ();
                Location world_loc = new Location(world1, x + size, location.getBlockY(), z + size);

                if ((Math.abs(world_loc.getBlockX()) <= Math.abs(x1)) || Math.abs(world_loc.getBlockZ()) <= Math.abs(z1)) {
                    if (this.tp_bow_players.contains(player)) {
                        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
                        player.sendMessage(ChatColor.RED + "You can't teleport outside the border!");
                    }
                } else {
                    player.setInvulnerable(true);
                    new InvulnerableClass(player).runTaskLater(plugin, 20);
                    player.teleport(this.tp_map.get(player).getLocation());
                    Location new_loc = player.getLocation();
                    new_loc.setYaw(arrow_loc.getYaw()*(-1));
                    new_loc.setPitch(arrow_loc.getPitch()*(-1));
                    player.teleport(new_loc);
                    this.check_inventory = false;
                }
            }
        }
    }

    @EventHandler
    public void move(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getEquipment().getBoots() != null) {
            if (!(player.getEquipment().getBoots().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "" + ChatColor.BOLD + "Jumping Boots"))) {
                player.removePotionEffect(PotionEffectType.JUMP);
            } else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2));
            }
        }
        else {
            player.removePotionEffect(PotionEffectType.JUMP);
        }
    }

    @EventHandler
    public void interacting (PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().equals(new ItemStack(Material.AIR))) {
            return;
        }
        if (player.getInventory().getItemInMainHand().getItemMeta() == null) {
            return;
        }
        else if (!(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Bedwars Fireball"))) {
            return;
        }
        else {
            Action action = event.getAction();
            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                return;
            }
            else if (action == Action.RIGHT_CLICK_AIR) {
                Fireball fireball = player.getWorld().spawn(player.getEyeLocation(), Fireball.class);
                fireball.setVelocity(fireball.getDirection().normalize().multiply(2));
                ItemStack item = event.getItem();
                if (item.equals(player.getInventory().getItemInMainHand())) {
                    item.setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                    player.setInvulnerable(true);
                    new InvulnerableClass(player).runTaskLater(plugin, 20);
                }
            }
            else if (action == Action.RIGHT_CLICK_BLOCK) {
                Fireball fireball = player.getWorld().spawn(player.getLocation(), Fireball.class);
                fireball.setVelocity(fireball.getDirection().normalize().multiply(2));
                player.setInvulnerable(true);
                new InvulnerableClass(player).runTaskLater(plugin, 20);
            }

        }
    }

    @EventHandler
    public void onTp (PlayerTeleportEvent event) {
        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) && event.getPlayer().getWorld().equals(world1)) {
            event.setCancelled(true);
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
            for (int cnt = 0; cnt <= 10; cnt++) {
                entity.getWorld().dropItemNaturally(entity_location, new ItemStack(Material.END_PORTAL_FRAME));
                entity.getWorld().dropItemNaturally(entity_location, new ItemStack(Material.TNT));
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
    public void endermanDied (EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (!(entity.getType().equals(EntityType.ENDERMAN))) {
            return;
        }
        else if (!(entity.getName().equals(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Pearl Giver"))) {
            return;
        }
        else {
            for (int cnt = 0; cnt <= 2; cnt++) {
                entity.getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.ENDER_PEARL));
            }
        }
    }


    @EventHandler
    public void playerDrankPotion (PlayerItemConsumeEvent event) {
        if (event.getItem().equals(potion_of_levitation)) {
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 400, 9));
        }
    }

    @EventHandler
    public void playerResistance (PlayerJoinEvent event) {
        if (!event.getPlayer().getWorld().equals(world1)) {
            event.getPlayer().setInvulnerable(true);
        }
    }

    @EventHandler
    public void itemDrop (BlockDropItemEvent event) {
        if (event.getBlock().equals(this.lucky_block)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void worldBorderPrevent (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double size = world1.getWorldBorder().getSize();
        Location loc = player.getLocation();
        if (player.getGameMode().equals(GameMode.SPECTATOR)) {
            if (Math.abs(loc.distance(spawn_loc)) > size/1.5) {
                player.teleport(spawn_loc);
            }
        }
    }

    @EventHandler
    public void playerInteracting (PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        else {
            if (!(this.flying_boats.contains(player.getInventory().getItemInMainHand()))) {
                return;
            }
            else {
                Location location = event.getClickedBlock().getLocation();
                Location new_loc = new Location(location.getWorld(), location.getBlockX(), location.getBlockY() + 1, location.getBlockZ());
                this.flying_boats_locs.add(new_loc);
            }
        }
    }

    @EventHandler
    public void EntitySpawn(VehicleCreateEvent event) {
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
    public void anotherMove(PlayerMoveEvent event) {
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
    public void arrowHitTarget (ProjectileHitEvent event) {
        if (event.getEntityType() == EntityType.ARROW && event.getEntity().getShooter() == null) {
            if (!(event.getHitEntity() instanceof LivingEntity)) {
                event.getEntity().remove();
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
            Material.AMETHYST_BLOCK, Material.IRON_AXE, Material.ANVIL, Material.TRIDENT, Material.NETHERITE_CHESTPLATE,
            Material.DIAMOND_BLOCK, Material.GOLDEN_SWORD, Material.CROSSBOW, Material.FISHING_ROD, Material.IRON_CHESTPLATE,
            Material.WATER_BUCKET, Material.BEDROCK, Material.REDSTONE_BLOCK, Material.YELLOW_WOOL,  Material.ARROW, Material.RAIL, Material.DIAMOND_HELMET,
            Material.IRON_HELMET, Material.IRON_HOE, Material.DEEPSLATE, Material.SLIME_SPAWN_EGG, Material.SLIME_BLOCK, Material.SADDLE,
            Material.SKELETON_HORSE_SPAWN_EGG, Material.ELYTRA, Material.SPAWNER, Material.OBSIDIAN, Material.POWDER_SNOW_BUCKET,
            Material.GOLDEN_APPLE, Material.IRON_BOOTS, Material.ANDESITE, Material.AMETHYST_SHARD, Material.BROWN_STAINED_GLASS, Material.BROWN_BANNER,
            Material.PIGLIN_BRUTE_SPAWN_EGG, Material.CHARCOAL
    };

    private Material[] lucky_enchanted_items = new Material[] {
            Material.NETHERITE_LEGGINGS, Material.TRIDENT, Material.IRON_CHESTPLATE, Material.IRON_AXE, Material.DIAMOND_SWORD,
            Material.CROSSBOW, Material.GOLDEN_SWORD, Material.DIAMOND_PICKAXE, Material.DIAMOND_AXE, Material.GOLDEN_LEGGINGS, Material.NETHERITE_HOE,
            Material.DIAMOND_CHESTPLATE, Material.NETHERITE_HELMET, Material.CHAINMAIL_BOOTS, Material.WOODEN_SHOVEL, Material.GOLDEN_HOE,
            Material.WOODEN_AXE, Material.GOLDEN_PICKAXE, Material.CHAINMAIL_CHESTPLATE, Material.ENCHANTED_GOLDEN_APPLE
    };

    ArrayList<ItemStack> jumping_boots = new ArrayList<>();
    ArrayList flying_boats_locs = new ArrayList();
    ArrayList flying_boats = new ArrayList<>();
    ArrayList vehicle_flying_boats = new ArrayList<>();
    ArrayList tanky_skeletons = new ArrayList();
    ArrayList bridge_basalts = new ArrayList();
    ArrayList fireballs = new ArrayList();
    ArrayList portals_locs = new ArrayList();
    ArrayList tnt_bows = new ArrayList();
    ArrayList tp_bows = new ArrayList();
    ArrayList<Player> tp_bow_players = new ArrayList<>();

    private String[] random_occurrences = new String[] {
            "spawn a wool bridge", "bedwars fireball", "build the rainbow penis", "spawn a tanky skeleton", "give player a fly boat",
            "make a portal",
    };

    private Material[] armor = new Material[] {
            Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS, Material.BOW
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

    private Integer[] arrays_types = new Integer[] {
            0, 0, 0, 0, 0, 0, 1, 2, 2,
    };
}