package me.in4.mcbuteverymobisaravager;

import me.in4.mcbuteverymobisaravager.classes.items.weapons.Guns;
import me.in4.mcbuteverymobisaravager.events.InteractionEvents;
import me.in4.mcbuteverymobisaravager.events.RavagerSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class McButEveryMobIsARavager extends JavaPlugin {

    public static McButEveryMobIsARavager plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Guns.init();
        getServer().getPluginManager().registerEvents(new RavagerSpawnEvent(), this);
        //getServer().getPluginManager().registerEvents(new InteractionEvents(), this);
    }

}
