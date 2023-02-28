package me.in4.voidfightz.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

import static me.in4.voidfightz.WorldsRunClass.spawn_loc;
import static me.in4.voidfightz.WorldsRunClass.world1;


public class Kills implements Listener {

    private HashMap<ItemMeta, Long> basalts_time;
    private HashMap<UUID, Long> lastprint;
    private HashMap<UUID, Long> boats_time;
    private HashMap<Location, Location> portals;
    private HashMap<Location, ArrayList> portals_arrays;
    private HashMap<Location, ArrayList> portals_arrays1;
    private HashMap<Location, ArrayList> portals_arrays2;
    private HashMap<Location, Location> location_;
    private HashMap<Location, Location> location_1;

    Block lucky_block;
    Boolean check_block = true;

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
    }

    @EventHandler
    public void playersDeaths(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Entity slayer = event.getEntity().getKiller();
        Location loc = player.getLocation();
        if (!(slayer instanceof Player)) {
            return;
        } else {
            int size = (int) player.getWorld().getWorldBorder().getSize();
            Random random = new Random();
            int random_array = arrays_types[random.nextInt(arrays_types.length)];
            if (random_array == 0) {
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
            } else if (random_array == 1) {
                Material random_enchanted_item = lucky_enchanted_items[random.nextInt(lucky_enchanted_items.length)];
                if (Arrays.asList(lucky_enchanted_items).contains(random_enchanted_item)) {
                    ItemStack lucky_item = new ItemStack(random_enchanted_item);
                    if (lucky_item.getType().equals(Material.GOLDEN_SWORD)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 11);

                    } else if (lucky_item.getType().equals(Material.IRON_CHESTPLATE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                    } else if (lucky_item.getType().equals(Material.IRON_AXE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
                        lucky_item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
                        lucky_item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 7);
                    } else if (lucky_item.getType().equals(Material.NETHERITE_LEGGINGS)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.THORNS, 4);
                        lucky_item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);

                    } else if (lucky_item.getType().equals(Material.DIAMOND_SWORD)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 4);
                        lucky_item.setDurability((short) 1556);
                    } else if (lucky_item.getType().equals(Material.TRIDENT)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.RIPTIDE, 5);
                        lucky_item.setDurability((short) 240);
                    } else if (lucky_item.getType().equals(Material.CROSSBOW)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.PIERCING, 2);
                    } else if (lucky_item.getType().equals(Material.DIAMOND_PICKAXE)) {
                        lucky_item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
                        lucky_item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
                        lucky_item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 7);
                        lucky_item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                    }

                    loc.getWorld().dropItemNaturally(loc, lucky_item);
                }
            }
            if (random_array == 2) {
                String occurrence = random_occurrences[random.nextInt(random_occurrences.length)];
                if (Arrays.asList(random_occurrences).contains(occurrence)) {
                    if (occurrence.equalsIgnoreCase("summon lightning")) {
                        for (Player players : world1.getPlayers()) {
                            players.getWorld().strikeLightning(players.getLocation());

                        }
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
                        if (((Player) slayer).getInventory().contains(item)) {
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
                        int x1 = spawn_loc.getBlockX();
                        int z1 = spawn_loc.getBlockZ();
                        int y1 = spawn_loc.getBlockY();

                        int value = random.nextInt(((size / 2) - (-size / 2)) + 1) + (-size / 2);
                        Location location1 = new Location(slayer.getWorld(), x1 + value, y1 + value, z1 + value);

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
                                    this.portals.put(location10, location1);
                                    this.portals_arrays1.put(location10, portal_arrays1);
                                    this.portals_arrays2.put(location10, portal_arrays2);

                                }
                                else if (portal[dy][dx] == 1) {
                                    material = Material.DIRT;
                                    portal_arrays1.add(location10);

                                }
                                else {
                                    material = Material.STONE;
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

                        for (int dy = 0; dy < portal.length; dy++) {
                            for (int dx = 0; dx < portal[0].length; dx++) {
                                Location location21 = new Location(slayer.getWorld(), portal_x + dx, portal_y + dy, portal_z);
                                Material material;
                                if (portal[dy][dx] == 0) {
                                    material = Material.AIR;
                                    this.portals_locs.add(location21);
                                    this.portals.put(location21, p_l);
                                    this.portals_arrays1.put(location21, portal_arrays3);
                                    this.portals_arrays2.put(location21, portal_arrays4);

                                }
                                else if (portal[dy][dx] == 1) {
                                    material = Material.DIRT;
                                    portal_arrays3.add(location21);
                                }
                                else {
                                    material = Material.STONE;
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
    @EventHandler
    public void BoatOrHayBaleSpawned (ItemSpawnEvent event) {
        if (this.flying_boats.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Flying Boat");
        }
        else if (this.flying_boats.contains(event.getEntity().getItemStack())) {
            event.getEntity().setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Bridge Hay Bale");
        }
    }


    @EventHandler
    public void playerHasDecidedToMove (PlayerMoveEvent event) {
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
                if (type != Material.DIRT) {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "The portal is broken! Repair it using correct blocks, by placing them in a shape similar to a nether portal.");
                    this.check_block = false;
                }

            }
            for (int cnt = 0; cnt < this.portals_arrays2.get(location).size(); cnt++) {
                Location block_loc = (Location) this.portals_arrays2.get(location).get(cnt);
                Material type = block_loc.getBlock().getType();
                if (type != Material.STONE) {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "The portal is broken! Repair it using correct blocks, by placing them in a shape similar to a nether portal.");
                    this.check_block = false;
                }
            }

            if (!this.check_block) {
                this.check_block = true;
            }
            else {
                player.teleport(this.portals.get(location));
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
            Location block_loc2 = new Location(player.getWorld(), location.getDirection().getBlockX() + 1, location.getBlockY() - 1, location.getDirection().getBlockZ() + 1);

            Block block = block_loc.getBlock();
            Block block2 = block_loc2.getBlock();
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
                            block2.setType(Material.HAY_BLOCK);
                            if (timeLeft <= 5 && timeLeft >= 0) {
                                if ( !(this.lastprint.containsKey(player.getUniqueId())) || (System.currentTimeMillis() - this.lastprint.get(player.getUniqueId()) >= 1000) ) {
                                    player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "YOUR BRIDGE HAY BALE WILL DISAPPEAR IN " + timeLeft +
                                            " SECONDS!!!");
                                    this.lastprint.put(player.getUniqueId(), System.currentTimeMillis());
                                }
                            }
                        }
                        else {
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
    public void playerRespawn (PlayerRespawnEvent event) {
        event.setRespawnLocation(spawn_loc);
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
                if (value >= 25 && value <= 50) {
                    Entity blaze = location.getWorld().spawnEntity(location, EntityType.BLAZE);
                    blaze.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Liferuiner/leader to victory...");
                    blaze.setCustomNameVisible(true);
                }
                else if (value < 25) {
                    Entity enderman = location.getWorld().spawnEntity(location, EntityType.ENDERMAN);
                    enderman.setCustomName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Liferuiner/leader to victory...");
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
            Material.DIAMOND_BLOCK, Material.GOLDEN_SWORD, Material.SMITHING_TABLE, Material.CROSSBOW, Material.FISHING_ROD, Material.IRON_CHESTPLATE,
            Material.WATER_BUCKET, Material.BEDROCK, Material.ARROW, Material.RAIL, Material.DIAMOND_HELMET,
            Material.HAY_BLOCK, Material.SLIME_BLOCK, Material.SADDLE, Material.SKELETON_HORSE_SPAWN_EGG, Material.DRAGON_HEAD,
            Material.ELYTRA, Material.BARRIER, Material.SPAWNER, Material.OBSIDIAN,
            Material.POWDER_SNOW_BUCKET, Material.TOTEM_OF_UNDYING, Material.GOLDEN_APPLE, Material.IRON_BOOTS,
            Material.FIRE_CHARGE,
    };

    private Material[] lucky_enchanted_items = new Material[] {
            Material.NETHERITE_LEGGINGS, Material.TRIDENT, Material.IRON_CHESTPLATE, Material.IRON_AXE, Material.DIAMOND_SWORD,
            Material.CROSSBOW, Material.GOLDEN_SWORD, Material.DIAMOND_PICKAXE
    };

    ArrayList jumping_boots = new ArrayList();
    ArrayList flying_boats_locs = new ArrayList();
    ArrayList flying_boats = new ArrayList();
    ArrayList vehicle_flying_boats = new ArrayList();
    ArrayList tanky_skeletons = new ArrayList();
    ArrayList bridge_basalts = new ArrayList();
    ArrayList fireballs = new ArrayList();
    ArrayList portals_locs = new ArrayList();

    private String[] random_occurrences = new String[] {
            "build the rainbow penis", "spawn a tanky skeleton", "make a portal",
            "spawn a wool bridge", "give player a fly boat",
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
            0, 2, 0, 1, 2
    };
}