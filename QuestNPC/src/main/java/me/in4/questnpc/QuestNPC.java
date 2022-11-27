package me.in4.questnpc;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import me.in4.questnpc.commands.SpawnNPC;
import me.in4.questnpc.managers.NPCManager;
import me.in4.questnpc.managers.QuestManager;
import me.in4.questnpc.menus.QuestMenu;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class QuestNPC extends JavaPlugin {

    private NPCManager npcManager;
    private QuestManager questManager;
    private static QuestNPC plugin;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        npcManager = new NPCManager();
        questManager = new QuestManager();

        plugin = this;

        getCommand("gagloevquester").setExecutor(new SpawnNPC(npcManager));
        MenuManager.setup(getServer(), this);

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(this, PacketType.Play.Client.USE_ENTITY) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                PacketContainer packetContainer = event.getPacket();

                int entity_id = packetContainer.getIntegers().read(0);
                if (entity_id == npcManager.getGagloevID()) {
                    EnumWrappers.Hand hand = packetContainer.getEnumEntityUseActions().read(0).getHand();
                    EnumWrappers.EntityUseAction action = packetContainer.getEnumEntityUseActions().read(0).getAction();
                    if (hand == EnumWrappers.Hand.MAIN_HAND && action == EnumWrappers.EntityUseAction.INTERACT){
                      getServer().getScheduler().runTask(plugin, () -> {
                          try {
                              MenuManager.openMenu(QuestMenu.class, event.getPlayer());
                          } catch (MenuManagerException | MenuManagerNotSetupException e) {
                              e.printStackTrace();
                          }
                      });

                    }
                }
            }
        });
    }

    public static QuestNPC getPlugin() {
        return plugin;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }

    public QuestManager getQuestManager() {
        return questManager;
    }
}
