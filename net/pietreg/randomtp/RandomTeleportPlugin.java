package net.pietreg.randomtp;

import net.pietreg.randomtp.commands.RandomTpCommand;
import net.pietreg.randomtp.configs.Config;
import net.pietreg.randomtp.configs.Messages;
import net.pietreg.randomtp.listeners.PlayerInteractListener;
import net.pietreg.randomtp.listeners.PlayerJoinListener;
import net.pietreg.randomtp.managers.ButtonManager;
import net.pietreg.randomtp.managers.TeleportManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class RandomTeleportPlugin extends JavaPlugin {

    private TeleportManager teleportManager;
    private ButtonManager buttonManager;

    @Override
    public void onEnable(){
        this.initialize();
    }

    private void initialize(){
        final long start = System.currentTimeMillis();
        getLogger().info("Initializacja pluginu pgRandomTP...");

        new Config(this);
        new Messages(this);

        teleportManager = new TeleportManager();
        buttonManager = new ButtonManager();

        new PlayerJoinListener(this);
        new PlayerInteractListener(this);
        new RandomTpCommand(this);

        getLogger().info("Zaladowano plugin pgRandomTP w "+(System.currentTimeMillis()-start)+"ms");
    }

    public TeleportManager getTeleportManager(){
        return teleportManager;
    }

    public ButtonManager getButtonManager() {
        return buttonManager;
    }

}
