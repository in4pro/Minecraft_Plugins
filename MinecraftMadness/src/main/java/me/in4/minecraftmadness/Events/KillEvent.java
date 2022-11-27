package me.in4.minecraftmadness.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class KillEvent implements Listener {

    @EventHandler
    public void playerRespawn (PlayerDeathEvent event) {
        if (event == null) {
            return;
        }
        Player player = event.getEntity();
        Player killer = player.getKiller();
        Material material = Material.ANVIL;
        Location loc = killer.getLocation();
        Location loc2 = player.getLocation();
        World world = killer.getWorld();
        int x = loc.getBlockX();
        int y = loc.getBlockY() + 10;
        int z = loc.getBlockZ();

        if (killer instanceof Player) {
            Random random = new Random();
            Random random1 = new Random();
            Random random2 = new Random();
            Random random3 = new Random();
            PotionEffect random_effects = effects[random.nextInt(effects.length)];
            PotionEffect random_effects1 = effects[random1.nextInt(effects.length)];
            PotionEffect random_effects2 = effects[random2.nextInt(effects.length)];
            PotionEffect random_effects3 = effects[random3.nextInt(effects.length)];
            killer.addPotionEffect(random_effects);
            killer.addPotionEffect(random_effects1);
            killer.addPotionEffect(random_effects2);
            killer.addPotionEffect(random_effects3);
            Location location = new Location(world, x, y, z);
            location.getBlock().setType(material);
            for (int cnt = 0; cnt <= 9; cnt++) {
                world.dropItemNaturally(loc2, new ItemStack(Material.EXPERIENCE_BOTTLE));
            }
        }
    }

    private PotionEffect[] effects = new PotionEffect[] {
            PotionEffectType.JUMP.createEffect(580, 9), PotionEffectType.LEVITATION.createEffect(580, 9),
            PotionEffectType.SLOW_FALLING.createEffect(580, 9), PotionEffectType.SPEED.createEffect(580, 9),
            PotionEffectType.DAMAGE_RESISTANCE.createEffect(580, 9), PotionEffectType.DOLPHINS_GRACE.createEffect(580, 9),
            PotionEffectType.FIRE_RESISTANCE.createEffect(580, 9), PotionEffectType.BAD_OMEN.createEffect(580, 9),
            PotionEffectType.INCREASE_DAMAGE.createEffect(580, 9), PotionEffectType.WITHER.createEffect(580, 9),
            PotionEffectType.SATURATION.createEffect(580, 9), PotionEffectType.CONFUSION.createEffect(580, 9),
            PotionEffectType.INVISIBILITY.createEffect(580, 9), PotionEffectType.HARM.createEffect(580, 9),
            PotionEffectType.FAST_DIGGING.createEffect(580, 9), PotionEffectType.SLOW_DIGGING.createEffect(580, 9),
            PotionEffectType.NIGHT_VISION.createEffect(580, 9), PotionEffectType.HERO_OF_THE_VILLAGE.createEffect(580, 9),
            PotionEffectType.DAMAGE_RESISTANCE.createEffect(580, 9), PotionEffectType.LUCK.createEffect(580, 9),
            PotionEffectType.ABSORPTION.createEffect(580, 9), PotionEffectType.SLOW.createEffect(580, 9),
            PotionEffectType.CONDUIT_POWER.createEffect(580, 9), PotionEffectType.HEALTH_BOOST.createEffect(580, 9),
            PotionEffectType.HEAL.createEffect(580, 9), PotionEffectType.HUNGER.createEffect(580, 9),
            PotionEffectType.WEAKNESS.createEffect(580, 9), PotionEffectType.WATER_BREATHING.createEffect(580, 9),
            PotionEffectType.GLOWING.createEffect(580, 9), PotionEffectType.BLINDNESS.createEffect(580, 9),
            PotionEffectType.REGENERATION.createEffect(580, 9), PotionEffectType.POISON.createEffect(580, 9),
            PotionEffectType.UNLUCK.createEffect(580, 9)
    };

}
