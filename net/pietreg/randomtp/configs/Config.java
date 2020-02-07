package net.pietreg.randomtp.configs;

import com.google.common.collect.Lists;
import net.pietreg.randomtp.RandomTeleportPlugin;
import net.pietreg.randomtp.configs.core.Configuration;
import net.pietreg.randomtp.configs.core.impl.ConfigurationImpl;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public final class Config {

    private final Configuration configuration;

    public Config(final RandomTeleportPlugin plugin){
        configuration = new ConfigurationImpl(new File(plugin.getDataFolder(), "config.yml"), this.getClass());
    }

    public int OPTIONS_CORDS$MIN = 400;
    public int OPTIONS_CORDS$MAX = 2000;
    public boolean OPTIONS_FIRST$TELEPORT = true;
    public boolean OPTIONS_GIVE$ITEMS = true;
    public boolean OPTIONS_GROUP_USE$PLATE = true;
    public boolean OPTIONS_GROUP_REMOVE$EFFECTS = true;
    public int OPTIONS_GROUP_RADIUS = 3;

    public List<String> ITEMS = Arrays.asList("material:IRON_PICKAXE enchants:DIG_SPEED;2@nlDURABILITY;3 lore:&6PIETREG_TO_FAJNE_PLUIGNY_ROBI@nlXD amount:1 name:&bITEM_&3TAKO data:0", "material:COOKED_BEEF amount:64");

    public List<String> LIST_SOLO = Lists.newArrayList();
    public List<String> LIST_GROUP = Lists.newArrayList();

    public void saveConfig(){
        configuration.save();
    }

    public void reloadConfig(){
        configuration.save();
    }
}
