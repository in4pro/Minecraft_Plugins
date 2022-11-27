package me.in4.questnpc.managers;

import me.in4.questnpc.QuestNPC;
import me.in4.questnpc.models.KillsQuest;
import me.in4.questnpc.models.Quest;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class QuestManager {

    public List<Quest> getAvailabeQuests() {
        List<Quest> availableQuests = new ArrayList<>();

        QuestNPC.getPlugin().getConfig().getConfigurationSection("quests.kills").getKeys(false).forEach(quest_name -> {
            String name = QuestNPC.getPlugin().getConfig().getString("quests.kills." + quest_name + ".name");
            String description = QuestNPC.getPlugin().getConfig().getString("quests.kills." + quest_name + ".description");
            String reward = QuestNPC.getPlugin().getConfig().getString("quests.kills." + quest_name + ".reward");
            int reward_amount = QuestNPC.getPlugin().getConfig().getInt("quests.kills." + quest_name + ".reward_amount");
            String type = QuestNPC.getPlugin().getConfig().getString("quests.kills." + quest_name + "target.type");
            int count = QuestNPC.getPlugin().getConfig().getInt("quests.kills." + quest_name + "target.count");

            EntityType typeEnum = EntityType.valueOf(type);
            //ItemStack rewardEnum = new ItemStack(Material.valueOf(String.valueOf(reward)));

            Quest quest = new KillsQuest(name, description, reward, reward_amount, typeEnum, count);
            availableQuests.add(quest);
            System.out.println(quest);
        });
        return availableQuests;

    }

}
