package net.pietreg.randomtp.listeners;

import net.pietreg.randomtp.RandomTeleportPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class PlayerJoinListener implements Listener {

    private final RandomTeleportPlugin plugin;

    public PlayerJoinListener(final RandomTeleportPlugin plugin){
        this.plugin = plugin;
        if (plugin.getConfiguration().OPTIONS_FIRST$TELEPORT) plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent e){
        final Player player = e.getPlayer();
        if (!player.hasPlayedBefore()){
            plugin.getTeleportManager().teleportSolo(player);
        }
    }
}
