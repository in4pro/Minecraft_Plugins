package me.in4.questnpc.models;

public abstract class Quest {

    private String name;
    private String description;
    private String reward;
    private int reward_amount;

    public Quest(String name, String description, String reward) {
        this.name = name;
        this.description = description;
        this.reward = reward;
        this.reward_amount = reward_amount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getReward() {
        return reward;
    }

    public int getReward_amount() {
        return reward_amount;
    }
}
