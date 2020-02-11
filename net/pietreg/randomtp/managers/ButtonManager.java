package net.pietreg.randomtp.managers;

import net.pietreg.randomtp.configs.Config;
import net.pietreg.randomtp.utils.LocationUtils;
import org.bukkit.Location;

public final class ButtonManager {

    public ButtonManager(){
    }

    public boolean isSoloButton(final Location location){
        return Config.LIST_SOLO.contains(LocationUtils.locToString(location));
    }

    public void removeSoloButton(final Location location){
        Config.LIST_SOLO.remove(LocationUtils.locToString(location));
        Config.saveConfig();
    }

    public void addSoloButton(final Location location){
        Config.LIST_SOLO.add(LocationUtils.locToString(location));
        Config.saveConfig();
    }

    public boolean isGroupButton(final Location location){
        return Config.LIST_GROUP.contains(LocationUtils.locToString(location));
    }

    public void removeGroupButton(final Location location){
        Config.LIST_GROUP.remove(LocationUtils.locToString(location));
        Config.saveConfig();
    }

    public void addGroupButton(final Location location){
        Config.LIST_GROUP.add(LocationUtils.locToString(location));
        Config.saveConfig();
    }
}
