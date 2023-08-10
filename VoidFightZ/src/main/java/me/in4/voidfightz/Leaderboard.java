package me.in4.voidfightz;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class Leaderboard {

    Player player;

    public Leaderboard (Player player) {
        this.player = player;
    }

    public void createScoreboard() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        assert manager != null;
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("leaderboard", "playerKillCount",
                ChatColor.GOLD + "" + ChatColor.BOLD + "Leaderboard");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        this.player.setScoreboard(scoreboard);



    }
}
