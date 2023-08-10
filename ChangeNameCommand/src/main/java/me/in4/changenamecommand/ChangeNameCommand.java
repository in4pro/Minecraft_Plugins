package me.in4.changenamecommand;

import org.bukkit.plugin.java.JavaPlugin;

public final class ChangeNameCommand extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("newname").setExecutor(new ChangeYourName());
        getCommand("resetname").setExecutor(new ResetName());
    }

}
