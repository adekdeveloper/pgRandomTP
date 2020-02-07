package net.pietreg.randomtp.managers;

import net.pietreg.randomtp.configs.Config;
import net.pietreg.randomtp.utils.LocationUtils;
import org.bukkit.Location;

public final class ButtonManager {

    private Config config;

    public ButtonManager(final Config config){
        this.config = config;
    }

    public boolean isSoloButton(final Location location){
        return config.LIST_SOLO.contains(LocationUtils.locToString(location));
    }

    public void removeSoloButton(final Location location){
        config.LIST_SOLO.remove(LocationUtils.locToString(location));
        config.saveConfig();
    }

    public void addSoloButton(final Location location){
        config.LIST_SOLO.add(LocationUtils.locToString(location));
        config.saveConfig();
    }

    public boolean isGroupButton(final Location location){
        return config.LIST_GROUP.contains(LocationUtils.locToString(location));
    }

    public void removeGroupButton(final Location location){
        config.LIST_GROUP.remove(LocationUtils.locToString(location));
        config.saveConfig();
    }

    public void addGroupButton(final Location location){
        config.LIST_GROUP.add(LocationUtils.locToString(location));
        config.saveConfig();
    }
}
