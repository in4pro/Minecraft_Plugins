package me.in4.questnpc.menus;

import me.in4.questnpc.QuestNPC;
import me.in4.questnpc.managers.QuestManager;
import me.in4.questnpc.models.KillsQuest;
import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class QuestMenu extends Menu {

    private final QuestManager questManager;

    public QuestMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.questManager = QuestNPC.getPlugin().getQuestManager();
    }

    @Override
    public String getMenuName() {
        return "Gag's Quests Menu";
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {

    }

    @Override
    public void setMenuItems() {

        questManager.getAvailabeQuests().forEach(quest -> {
            ItemStack item;

            boolean isOnQuest = false;

            if (quest instanceof KillsQuest killsQuest) {
                item = makeItem(Material.DIAMOND_SWORD,
                        ChatColor.DARK_RED + "" + ChatColor.BOLD + "" + killsQuest.getName(),
                        ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "" + killsQuest.getDescription(),
                        " ",
                        ChatColor.GOLD + "" + ChatColor.BOLD + "" + killsQuest.getReward(),
                        " ",
                        (isOnQuest ? ChatColor.RED + "You are on this quest!" : ChatColor.GREEN + "Click to accept!"));
                inventory.addItem(item);
            }
        });

    }
}
