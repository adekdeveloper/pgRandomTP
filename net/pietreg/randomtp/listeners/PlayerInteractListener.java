package net.pietreg.randomtp.listeners;

import net.pietreg.randomtp.RandomTeleportPlugin;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class PlayerInteractListener implements Listener {

    private final RandomTeleportPlugin plugin;

    public PlayerInteractListener(final RandomTeleportPlugin plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event){
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        final Player player = event.getPlayer();
        final Block block = event.getClickedBlock();
        if (block.getType() == Material.WOOD_BUTTON && plugin.getButtonManager().isSoloButton(block.getLocation())){
            plugin.getTeleportManager().teleportSolo(player);
        }else if (block.getType() == Material.STONE_BUTTON && plugin.getButtonManager().isGroupButton(block.getLocation())){
            plugin.getTeleportManager().teleportGroup(player);
        }
    }
}
