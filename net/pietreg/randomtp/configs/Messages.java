package net.pietreg.randomtp.configs;

import net.pietreg.randomtp.RandomTeleportPlugin;
import net.pietreg.randomtp.configs.core.Configuration;
import net.pietreg.randomtp.configs.core.impl.ConfigurationImpl;

import java.io.File;

public final class Messages {

    private final Configuration configuration;

    public Messages(final RandomTeleportPlugin plugin){
        configuration = new ConfigurationImpl(new File(plugin.getDataFolder(), "messages.yml"), this.getClass());
    }

    public String TELEPORTED_SOLO = "&eZostales przeteleportowany na losowe kordynaty.";
    public String TELEPORTED_GROUP = "&bZostales przeteleportowany na losowe kordynaty.";

    public String COMMAND_NO$BUTTON = "&4Blad: &cBlok nie jest przyciskiem!";
    public String COMMAND_ENGAGED$BUTTON = "&4Blad: &cTen przycisk jest zajety!";
    public String COMMAND_NO$TELEPORTER = "&4Blad: &cTen przycisk nie jest teleportem!";
    public String COMMAND_OTHER$BUTTON = "&4Blad: &cNie mozesz usunac tego przycisku, jest on innym teleportem!";
    public String COMMAND_SOLO_MUST$WOOD$BUTTON = "&4Blad: &cPrzycisk do losowego teleportu solo musi byc drewniany!";
    public String COMMAND_SOLO_CREATED = "&aTeleport solo zostal stworzony!";
    public String COMMAND_SOLO_REMOVED = "&cTeleport solo zostal usuniety!";

    public String COMMAND_GROUP_MUST$STONE$BUTTON = "&4Blad: &cPrzycisk do losowego teleportu group musi byc kamienny!";
    public String COMMAND_GROUP_CREATED = "&aTeleport group zostal stworzony!";
    public String COMMAND_GROUP_REMOVED = "&cTeleport group zostal usuniety!";

    public void saveConfig(){
        configuration.save();
    }

    public void reloadConfig(){
        configuration.reload();
    }
}
