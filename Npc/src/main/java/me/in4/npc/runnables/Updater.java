package me.in4.npc.runnables;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.mojang.datafixers.util.Pair;
import me.in4.npc.Npc;
import me.in4.npc.bots.Bot;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_19_R3.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

import static me.in4.npc.Npc.plugin;

public class Updater extends BukkitRunnable implements Listener {

    ServerPlayer bot;
    Bot mob;
    double prevx;
    double prevy;
    double prevz;
    int k;
    int n;

    ArrayList<Block> water_blocks = new ArrayList<>();

    private final HashMap<Bot, Long> stuckTime = new HashMap<>();
    private final HashMap<Bot, Long> jumpTime = new HashMap<>();
    private final HashMap<Block, Long> waterToAirTime = new HashMap<>();
    private final HashMap<Block, Long> blockBreakTime = new HashMap<>();
    private final HashMap<ServerPlayer, Long> npcAttackCooldown = new HashMap<>();

    public Updater(ServerPlayer npc, Bot c_mob, double prev_x, double prev_y, double prev_z) {
        bot = npc;
        mob = c_mob;
        prevx = prev_x;
        prevy = prev_y;
        prevz = prev_z;
    }

    @Override
    public void run() {
        facing(bot, mob);

        double x = mob.getX();
        double y = mob.getY();
        double z = mob.getZ();

        int b_x = mob.getBlockX();
        int b_y = mob.getBlockY();
        int b_z = mob.getBlockZ();

        Location mob_loc = new Location(mob.getBukkitEntity().getWorld(), x, y, z);
        BlockPosition blockPosition = new BlockPosition(b_x, b_y, b_z);
        Location blockLocation = new Location(mob.getBukkitEntity().getWorld(), b_x, b_y, b_z);
        BlockPosition another_pos = new BlockPosition(b_x, b_y - 1, b_z);
        BlockPosition pos = new BlockPosition(b_x, b_y - 2, b_z);
        BlockPosition pos_above = new BlockPosition(b_x, b_y + 2, b_z);
        Block block_below = another_pos.toLocation(mob.getBukkitEntity().getWorld()).getBlock();

        //playerAttackPacket(blockLocation);

        if (x != prevx || y != prevy || z != prevz) {
            double dx = x - prevx;
            double dy = y - prevy;
            double dz = z - prevz;
            moveNpc(bot, dx, dy, dz);
            bot.setPos(x, y, z);
            prevx = x;
            prevy = y;
            prevz = z;
        }
        for (Entity entity : bot.getBukkitEntity().getNearbyEntities(100000, 320, 100000)) {
            if ((entity instanceof Player player)) {
                ServerPlayer serverPlayer = ((CraftPlayer) player).getHandle();
                mob.lookAt(EntityAnchorArgument.Anchor.EYES, serverPlayer.getEyePosition());
                Location respawn_loc = player.getBedSpawnLocation();
                Location p_loc = player.getLocation();
                Location p_block_loc = new Location(p_loc.getWorld(), p_loc.getBlockX(), p_loc.getBlockY(), p_loc.getBlockZ());
                Location spawn_loc = Objects.requireNonNull(p_loc.getWorld()).getSpawnLocation();
                double distance = p_loc.distance(mob.getBukkitEntity().getLocation());
                double distance2d = Math.sqrt(Math.pow(p_loc.getX() - x, 2) + Math.pow(p_loc.getZ() - z, 2));

                PathNavigation navigation = mob.getNavigation();
                if (player.isDead()) {
                    if (respawn_loc == null) {
                        navigation.moveTo(spawn_loc.getX(), spawn_loc.getY(), spawn_loc.getZ(), 8);
                    } else {
                        navigation.moveTo(respawn_loc.getX(), respawn_loc.getY(), respawn_loc.getZ(), 6);
                    }
                }
                //movement
                else if (Math.abs(distance) >= 2) {
                    //mlgs
                    if (mob.isInWater()) {
                        mob.setSwimming(true);
                    }
                    if (block_below.getType().equals(Material.LAVA)) {
                        npcPerformAnMlg(pos);
                    }
                    else if (npcHowFarFromGround(b_y) >= 4 && block_below.getType().isAir()) {
                        k = 1;
                    }
                    if (k == 1) {
                        npcPerformAnMlg(pos);
                    }
                    if (water_blocks.size() >= 1) {
                        if (waterToAirTime.containsKey(water_blocks.get(0))) {
                            long timeElapsed = (System.currentTimeMillis() - waterToAirTime.get(water_blocks.get(0)));
                            if (timeElapsed >= 500) {
                                npcSwingArm(bot, 0);
                                water_blocks.get(0).setType(Material.AIR);
                                waterToAirTime.remove(water_blocks.get(0));
                                water_blocks.remove(0);
                            }
                        }
                    }
                    //if stuck

                    ItemStack itemStack = new ItemStack(Items.COBBLESTONE);
                    npcSetItemInHand(itemStack);
                    Block block_in_front = bot.getBukkitEntity().getTargetBlockExact(1);
                    Block block = player.getTargetBlockExact(1);
                    if (block != null && block.getType().isSolid()) {
                        int height = howHigh(block.getLocation());
                        int thickness = howThick(block, player);
                        player.sendMessage(height + " " + thickness);
                        player.sendMessage(block.getType() + "");
                    }
                    if (block_in_front != null && block_in_front.getType().isSolid()) {
                        int height = howHigh(block_in_front.getLocation());
                        int thickness = howThick(block_in_front, bot.getBukkitEntity());
                        //player.sendMessage(height + " " + thickness);
                        if (height >= thickness) {
                            npcSetItemInHand(new ItemStack(Items.NETHERITE_PICKAXE));
                            int id = net.minecraft.world.level.block.Block.getId(((CraftBlockData) block_in_front.getBlockData()).getState());
                            BlockPos position = new BlockPos(block_in_front.getX(), block_in_front.getY(), block_in_front.getZ());
                            npcBreakBlock(id, position, n);
                            n++;
                            if (n >= 10)  {
                                block_in_front.setType(Material.AIR);
                                blockBreakTime.remove(block_in_front);
                                n = 0;
                            }
                        }
                        else {
                            startJumping(blockPosition, another_pos, pos_above);
                        }
                    }
                    else {
                        navigation.moveTo(p_loc.getX(), p_loc.getY(), p_loc.getZ(), 6);

                    }

                    if (!(mob.isStuck())) {
                        jumpTime.remove(mob);
                    }

                    //navigation.moveTo(p_loc.getX(), p_loc.getY(), p_loc.getZ(), 6);


                }

                //pvp
                if (distance <= 4) {
                    if (player.isBlocking() && !(player.hasCooldown(Material.SHIELD))) {
                        ItemStack itemStack = new ItemStack(Items.NETHERITE_AXE);
                        npcSetItemInHand(itemStack);

                        if (noSolidBlocks(p_block_loc, bot.getBukkitEntity()) && lookingAt(player, bot.getBukkitEntity())) {
                            npcSwingArm(bot, 0);
                            player.setCooldown(Material.SHIELD, 100);
                            npcAttackCooldown.put(bot, System.currentTimeMillis());
                        }
                    }
                    else {
                        ItemStack itemStack = new ItemStack(Items.NETHERITE_SWORD);
                        if ((!(npcAttackCooldown.containsKey(bot)) || (System.currentTimeMillis() - npcAttackCooldown.get(bot) >= 625)) && mob.isOnGround()) {
                            if (!(bot.getBukkitEntity().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD))) {
                                npcSetItemInHand(itemStack);
                            }
                            if (noSolidBlocks(p_block_loc, bot.getBukkitEntity())) {
                                npcSwingArm(bot, 0);
                                if (distance <= 3) {
                                    mob.getBukkitEntity().setVelocity(new Vector(0, 0.4, 0));
                                    critAnimation(serverPlayer, 4);
                                    damagePlayer(player, 12);
                                }
                                else {
                                    damagePlayer(player, 8);
                                }
                                npcAttackCooldown.put(bot, System.currentTimeMillis());

                            }

                        }
                    }
                }
            }
        }
    }

    //Packet listeners

    public int howHigh (Location block_loc) {
        int dy = 0;
        Block block = block_loc.getBlock();
        while (block.getType().isSolid()) {
            Location location = new Location(block.getWorld(), block_loc.getX(), block_loc.getY() + dy, block_loc.getZ());
            block = location.getBlock();
            dy++;
        }
        return dy;
    }

    public int howThick (Block block, Player player) {
        int d = 0;
        while (block != null && block.getType().isSolid()) {
            player.sendMessage(block.getLocation() + "");
            block = player.getTargetBlockExact(d);
            d++;
        }
        return d;
    }

    //npc functions
    public boolean noSolidBlocks (Location block_location, Player bot) {
       // ServerPlayer npc = ((CraftPlayer) bot).getHandle();
        boolean a_boolean = true;
        double distance = Math.abs(mob.getBukkitEntity().getLocation().distance(block_location));
        Block block1 = bot.getLocation().getBlock();
        for (int cnt = 0; cnt <= distance; cnt++) {
            Block block = bot.getTargetBlockExact(cnt);
            if ((block != null) && (block.getType().isSolid() || block1.getType().isSolid())) {
                a_boolean = false;
                break;
            }
        }

        return a_boolean;

    }

    public boolean lookingAt (Player player, Player npc) {
        Vector player_dir = player.getLocation().getDirection();
        Vector npc_dir = npc.getEyeLocation().getDirection();
        double angle = npc_dir.angle(player_dir);
        player.sendMessage(angle + "");
        return angle >= Math.PI / 2;
    }

    public void facing (ServerPlayer npc, Bot mob) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer cp = (CraftPlayer) player;
            ServerPlayer sp = cp.getHandle();
            ServerGamePacketListenerImpl packetListener = sp.connection;
            packetListener.send(new ClientboundRotateHeadPacket(npc, (byte) ((mob.getBukkitYaw() *  256 / 360))));
            packetListener.send(new ClientboundMoveEntityPacket.Rot(npc.getBukkitEntity().getEntityId(), (byte) (mob.getBukkitEntity().getLocation().getYaw() *  256 / 360), (byte) (mob.getBukkitEntity().getLocation().getPitch() *  256 / 360), false));
        }
    }

    public void startJumping (BlockPosition blockPosition, BlockPosition another_pos, BlockPosition pos_above) {
        if (!(jumpTime.containsKey(mob))) {
            jumpTime.put(mob, System.currentTimeMillis());
        } else {
            long time_elapsed = (System.currentTimeMillis() - jumpTime.get(mob));
            if (time_elapsed >= 500) {
                mob.getBukkitEntity().setVelocity(new Vector(0, 0.5, 0));
                npcPlaceBlock(blockPosition, another_pos, pos_above);
            }
            if (npcHowFarFromGround(mob.getBlockY()) >= 1.5) {
                jumpTime.remove(mob);
            }
        }
    }

    public void moveNpc (ServerPlayer npc, double x, double y, double z) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer cp = (CraftPlayer) player;

            ServerPlayer sp = cp.getHandle();
            ServerGamePacketListenerImpl packetListener = sp.connection;
            packetListener.send(new ClientboundMoveEntityPacket.Pos(npc.getBukkitEntity().getEntityId(),
                    (short) (x * 4096), (short) (y * 4096), (short) (z * 4096), true));
        }
    }

    public void critAnimation (ServerPlayer player1, int animation) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer cp = (CraftPlayer) player;
            ServerPlayer sp = cp.getHandle();
            ServerGamePacketListenerImpl packetListener = sp.connection;
            packetListener.send(new ClientboundAnimatePacket(player1, animation));
        }
    }

    public double npcHowFarFromGround (int y) {
        Location location = mob.getBukkitEntity().getLocation();
        Block block = location.getBlock();
        while (!block.getType().isSolid()) {
            Location location1 = new Location(mob.getBukkitEntity().getWorld(), mob.getBlockX(), y, mob.getBlockZ());
            block = location1.getBlock();
            y--;
        }
        Location ground_loc = block.getLocation();
        return Math.abs(location.distance(ground_loc));
    }

    public void npcPerformAnMlg (BlockPosition blockPosition) {
        Block block = blockPosition.toLocation(mob.getBukkitEntity().getWorld()).getBlock();
        Location mob_loc = new Location(mob.getBukkitEntity().getWorld(), mob.getBlockX(), mob.getBlockY() - 1, mob.getBlockZ());

        if (!(block.getType().isAir()) && !(block.isLiquid())) {
            ItemStack itemStack = new ItemStack(Items.WATER_BUCKET);
            npcSetItemInHand(itemStack);
            Block water = mob_loc.getBlock();
            npcSwingArm(bot, 0);
            water.setType(Material.WATER);
            water_blocks.add(water);
            waterToAirTime.put(water, System.currentTimeMillis());
            k = 0;
        }
        else if (block.getType().equals(Material.LAVA)) {
            ItemStack itemStack = new ItemStack(Items.OAK_BOAT);
            npcSetItemInHand(itemStack);
            npcSwingArm(bot, 0);
            mob.getBukkitEntity().getWorld().spawnEntity(mob_loc, EntityType.BOAT);
        }

    }

    public void npcPlaceBlock (BlockPosition block_pos_below1, BlockPosition block_pos_below2, BlockPosition block_pos_above) {
        Block block_below = block_pos_below2.toLocation(mob.getBukkitEntity().getWorld()).getBlock();
        Block block_above = block_pos_above.toLocation(mob.getBukkitEntity().getWorld()).getBlock();
        BlockPos blockPos_above = new BlockPos(block_above.getX(), block_above.getY(), block_above.getZ());

        if (block_below.getType().isSolid() && (!(block_above.getType().isSolid()))) {
            npcSwingArm(bot, 0);
            block_pos_below1.toLocation(mob.getBukkitEntity().getWorld()).getBlock().setType(Material.COBBLESTONE);
            jumpTime.remove(mob);
        }
        if (block_above.getType().isSolid()) {
            ItemStack itemStack = new ItemStack(Items.NETHERITE_PICKAXE);
            npcSetItemInHand(itemStack);
            int id = net.minecraft.world.level.block.Block.getId(((CraftBlockData) block_above.getBlockData()).getState());

            npcBreakBlock(id, blockPos_above, n);
            n++;
            if (n >= 10)  {
                block_above.setType(Material.AIR);
                blockBreakTime.remove(block_above);
                n = 0;
            }
        }
    }

    public void npcSetItemInHand (ItemStack itemStack) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer cp = (CraftPlayer) player;
            ServerPlayer sp = cp.getHandle();
            bot.setItemInHand(InteractionHand.MAIN_HAND, itemStack);
            ServerGamePacketListenerImpl packetListener = sp.connection;
            packetListener.send(new ClientboundSetEquipmentPacket(bot.getBukkitEntity().getEntityId(), List.of(new Pair<>(EquipmentSlot.MAINHAND,
                    itemStack))));
        }
    }

    public void npcSwingArm (ServerPlayer npc, int arm) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer cp = (CraftPlayer) player;
            ServerPlayer sp = cp.getHandle();
            ServerGamePacketListenerImpl packetListener = sp.connection;
            packetListener.send(new ClientboundAnimatePacket(npc, arm));
        }
    }

    public void npcShieldBlocking () {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = new PacketContainer(PacketType.Play.Client.USE_ITEM);
        packet.getIntegers().write(0, 1);
        manager.receiveClientPacket(bot.getBukkitEntity(), packet, true);

        //manager.broadcastServerPacket(packet);
    }

    public void npcBreakBlock (int id, BlockPos block_pos, int progress) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            CraftPlayer cp = (CraftPlayer) player;
            ServerPlayer sp = cp.getHandle();
            ServerGamePacketListenerImpl packetListener = sp.connection;
            packetListener.send(new ClientboundBlockDestructionPacket(id, block_pos, progress));
        }
    }

    //damage calculations
    public void damagePlayer(Player p, double damage) {
        double points = p.getAttribute(Attribute.GENERIC_ARMOR).getValue();
        double toughness = p.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
        PotionEffect effect = p.getPotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
        int resistance = effect == null ? 0 : effect.getAmplifier();
        int epf = getEPF(p.getInventory());

        p.damage(calculateDamageApplied(damage, points, toughness, resistance, epf));
    }

    public double calculateDamageApplied(double damage, double points, double toughness, int resistance, int epf) {
        double withArmorAndToughness = damage * (1 - Math.min(20, Math.max(points / 5, points - damage / (2 + toughness / 4))) / 25);
        double withResistance = withArmorAndToughness * (1 - (resistance * 0.2));
        return withResistance * (1 - (Math.min(20.0, epf) / 25));
    }

    public static int getEPF(PlayerInventory inv) {
        org.bukkit.inventory.ItemStack helm = inv.getHelmet();
        org.bukkit.inventory.ItemStack chest = inv.getChestplate();
        org.bukkit.inventory.ItemStack legs = inv.getLeggings();
        org.bukkit.inventory.ItemStack boot = inv.getBoots();

        return (helm != null ? helm.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                (chest != null ? chest.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                (legs != null ? legs.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0) +
                (boot != null ? boot.getEnchantmentLevel(Enchantment.DAMAGE_ALL) : 0);

    }

}
