package net.pietreg.randomtp.utils;

import com.google.common.collect.Sets;
import net.pietreg.randomtp.configs.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.util.Set;

public final class LocationUtils {

    private LocationUtils(){
    }

    public static String locToString(final Location loc) {
        return (loc.getBlockX()) + ";" + loc.getBlockY() + ";" + loc.getBlockZ();
    }

    public static Location getRandomCords(final Config config) {
        // RADIOAKTYWNOŚĆ METODY (LEPIEJ NA NIĄ NIE PATRZEĆ)
        Location location = new Location(Bukkit.getWorlds().get(0), RandomUtils.getRandomDouble(-config.OPTIONS_CORDS$MIN, config.OPTIONS_CORDS$MAX), 0.0, RandomUtils.getRandomDouble(-config.OPTIONS_CORDS$MIN, config.OPTIONS_CORDS$MAX));
        while (location.getBlock().getBiome() == Biome.OCEAN || location.getBlock().getBiome() == Biome.DEEP_OCEAN || location.getBlock().getBiome() == Biome.RIVER){
            location = new Location(Bukkit.getWorlds().get(0), RandomUtils.getRandomDouble(-config.OPTIONS_CORDS$MIN, config.OPTIONS_CORDS$MAX), 0.0, RandomUtils.getRandomDouble(-config.OPTIONS_CORDS$MIN, config.OPTIONS_CORDS$MAX));
        }
        location.setY((double)location.getWorld().getHighestBlockYAt(location.getBlockX(), location.getBlockZ()) + 1);
        return location;
    }

    public static Set<Player> findPlayers(final Location location, final int distance, boolean b){
        final Set<Player> playerList = Sets.newHashSet();
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (location.distance(player.getLocation()) < distance){
                if (b){
                    if (player.getLocation().getBlock().getType().toString().contains("PLATE")){
                        playerList.add(player);
                    }
                }else {
                    playerList.add(player);
                }
            }
        });
        return playerList;
    }
}
