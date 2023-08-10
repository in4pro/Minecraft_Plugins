package me.in4.npc.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;
import me.in4.npc.Npc;
import me.in4.npc.bots.Bot;
import me.in4.npc.runnables.OneRun;
import me.in4.npc.runnables.Updater;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

import static me.in4.npc.Npc.mobs;
import static me.in4.npc.Npc.plugin;

public class CreateBot implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            CraftPlayer craft_player = (CraftPlayer) player;
            ServerPlayer server_player = craft_player.getHandle();
            MinecraftServer server = server_player.getServer();
            ServerLevel level = server_player.getLevel();
            GameProfile gp = new GameProfile(UUID.randomUUID(), "Gagloev");
            assert server != null;
            ServerPlayer bot = new ServerPlayer(server, level, gp);
            Location location = player.getLocation();
            location.setY(location.getBlockY());
            /*while (location.getBlock().getType() == Material.AIR) {
                location.subtract(0, 1, 0);
            }*/

            Bot mob = new Bot(player.getLocation());
            ServerLevel world = ((CraftWorld) player.getWorld()).getHandle();
            world.addFreshEntity(mob);
            new OneRun(mob).runTaskLater(plugin, 2);
            mob.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.065);
            mob.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(6.5);
            Npc.getMobs().add(mob);

            bot.setPos(location.getX(), location.getY() + 0.1, location.getZ());

            String signature = "DjXPe0GytXLj9gbulaeyrukc1OHUFvuSSqu/vKNmmHp+7iQPxfmvDSjnDkrvJOkLoo5y0m9ZfNkdv2iTNAKUy3F7jBvEgwwe2dPkQePN7NJmnpr513PtV+Nwzyl1rGOj3EUZYbHznW4VgWjcj+syIzHCi/X5TkoEqqVJ0L1qKCd+UJXuR5oC0x1CJkStoPdQxJ2kmW2/8gz0zBys8U8LTmf0lB6IdmhMAS7Zwtmh9PkN7UyT6UPBCXz4VehWmPFJXHlZG95aNzqNJpMqoSWGRiI6vVBrfG+n1iIZ6oxrG1JmEO3hEwB/8d8WklXsjL7yjS5H2NaGeg0ix/AC7yOG+LD+ig8vLqVdKR8PZDwlTnjvb3q83K3O1dfTfSEAzKYDS+6ZrHkhT+HsYmjUS2NV/SvATtSijTrYv4MZ+Ryp35Hd3v83BBXh6Ho1mVg3xqud3JZ4+rbTSVmO6eG8ZvNwk1+WQLQUmrIgSbdPRDYare1tWSlqSSMwlZciSDcdLTlkPr1uUMjM0K4w8gpkDVuh5HyPXh48Jgrnto0TSUaXtogRF0HD1PK52R9U4StYB0jwDD72Gah5mNn/DZRPyVX9CYpoQcSuGuH0hlnRh8w0+66XUUl4dLpM4fNTRRhX8IEmhUqZf/wyNsUYZx3AApAZG1WqvLezTcB2uh8/l50B8dU=";
            String texture = "ewogICJ0aW1lc3RhbXAiIDogMTY1MjQ4NTI2NTkwMywKICAicHJvZmlsZUlkIiA6ICIzNTU2ZWQzNmU2ODA0YjQzYmJiMTFhNGE0ZDk2ODQ5YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJpbjRfcHJvIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFjYWNhZTVmNjYzMTM5YTMyODVhYmMwMDU2MWFkNjM0N2YzNzVmNGE2NjhlZGVjNjc5ZjE2OGRkMzkzYmZjYzkiCiAgICB9CiAgfQp9";

            gp.getProperties().put("textures", new Property("textures", texture, signature));

            for (Player bukkitPlayer : Bukkit.getOnlinePlayers()) {
                CraftPlayer cp = (CraftPlayer) bukkitPlayer;
                ServerPlayer sp = cp.getHandle();
                ServerGamePacketListenerImpl packetListener = sp.connection;
                packetListener.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, bot));
                packetListener.send(new ClientboundAddPlayerPacket(bot));
                packetListener.send(new ClientboundSetEquipmentPacket(bot.getBukkitEntity().getEntityId(), List.of(new Pair<>(EquipmentSlot.OFFHAND,
                        new ItemStack(Items.SHIELD)))));
            }
            Npc.getBots().add(bot);

            double prev_x = mob.getX();
            double prev_y = mob.getY();
            double prev_z = mob.getZ();

            new Updater(bot, mob, prev_x, prev_y, prev_z).runTaskTimer(plugin, 1, 1);
        }

        return true;
    }



}
