package me.in4.questnpc.managers;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPCManager {

    private ServerPlayer gagloev;

    public void spawnGagloev(Player player) {
        CraftPlayer craft_player = (CraftPlayer) player;
        MinecraftServer server = craft_player.getHandle().getServer();
        ServerLevel level = craft_player.getHandle().getLevel();
        GameProfile gp = new GameProfile(UUID.randomUUID(), ChatColor.GOLD + "[GAG] Quester");

        String signature = "DjXPe0GytXLj9gbulaeyrukc1OHUFvuSSqu/vKNmmHp+7iQPxfmvDSjnDkrvJOkLoo5y0m9ZfNkdv2iTNAKUy3F7jBvEgwwe2dPkQePN7NJmnpr513PtV+Nwzyl1rGOj3EUZYbHznW4VgWjcj+syIzHCi/X5TkoEqqVJ0L1qKCd+UJXuR5oC0x1CJkStoPdQxJ2kmW2/8gz0zBys8U8LTmf0lB6IdmhMAS7Zwtmh9PkN7UyT6UPBCXz4VehWmPFJXHlZG95aNzqNJpMqoSWGRiI6vVBrfG+n1iIZ6oxrG1JmEO3hEwB/8d8WklXsjL7yjS5H2NaGeg0ix/AC7yOG+LD+ig8vLqVdKR8PZDwlTnjvb3q83K3O1dfTfSEAzKYDS+6ZrHkhT+HsYmjUS2NV/SvATtSijTrYv4MZ+Ryp35Hd3v83BBXh6Ho1mVg3xqud3JZ4+rbTSVmO6eG8ZvNwk1+WQLQUmrIgSbdPRDYare1tWSlqSSMwlZciSDcdLTlkPr1uUMjM0K4w8gpkDVuh5HyPXh48Jgrnto0TSUaXtogRF0HD1PK52R9U4StYB0jwDD72Gah5mNn/DZRPyVX9CYpoQcSuGuH0hlnRh8w0+66XUUl4dLpM4fNTRRhX8IEmhUqZf/wyNsUYZx3AApAZG1WqvLezTcB2uh8/l50B8dU=";
        String texture = "ewogICJ0aW1lc3RhbXAiIDogMTY1MjQ4NTI2NTkwMywKICAicHJvZmlsZUlkIiA6ICIzNTU2ZWQzNmU2ODA0YjQzYmJiMTFhNGE0ZDk2ODQ5YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJpbjRfcHJvIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFjYWNhZTVmNjYzMTM5YTMyODVhYmMwMDU2MWFkNjM0N2YzNzVmNGE2NjhlZGVjNjc5ZjE2OGRkMzkzYmZjYzkiCiAgICB9CiAgfQp9";

        gp.getProperties().put("textures", new Property("textures", texture, signature));

        ServerPlayer npc = new ServerPlayer(server, level, gp);
        npc.setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());

        ServerGamePacketListenerImpl ps = craft_player.getHandle().connection;
        ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
        ps.send(new ClientboundAddPlayerPacket(npc));

        this.gagloev = npc;
    }

    public int getGagloevID() {
        if (this.gagloev == null) {
            return 0;
        }
        return this.gagloev.getId();
    };
}
