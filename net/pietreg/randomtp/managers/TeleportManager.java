package net.pietreg.randomtp.managers;

import com.google.common.collect.Lists;
import net.pietreg.randomtp.RandomTeleportPlugin;
import net.pietreg.randomtp.utils.ChatUtils;
import net.pietreg.randomtp.utils.LocationUtils;
import net.pietreg.randomtp.utils.ParserUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public final class TeleportManager {

    private final RandomTeleportPlugin plugin;
    private final List<ItemStack> itemStack;

    public TeleportManager(final RandomTeleportPlugin plugin){
        this.plugin = plugin;
        this.itemStack = Lists.newArrayList();
        for (String s : plugin.getConfiguration().ITEMS){
            itemStack.add(ParserUtils.parseItemStack(s));
        }
    }

    public void teleportSolo(final Player player){
        final Location location = LocationUtils.getRandomCords(plugin.getConfiguration());
        if (plugin.getConfiguration().OPTIONS_GIVE$ITEMS) itemStack.forEach(item -> player.getInventory().addItem(item));
        player.teleport(location);
        ChatUtils.sendMessage(player, plugin.getMessages().TELEPORTED_SOLO);
    }

    public void teleportGroup(final Player player){
        if (plugin.getConfiguration().OPTIONS_GROUP_USE$PLATE && player.getLocation().getBlock().getType() != Material.STONE_PLATE) return;
        final Location location = LocationUtils.getRandomCords(plugin.getConfiguration());
        LocationUtils.findPlayers(player.getLocation(), plugin.getConfiguration().OPTIONS_GROUP_RADIUS, plugin.getConfiguration().OPTIONS_GROUP_USE$PLATE).forEach(players -> {
            if (plugin.getConfiguration().OPTIONS_GROUP_REMOVE$EFFECTS) players.getActivePotionEffects().forEach(potionEffect -> players.removePotionEffect(potionEffect.getType()));
            if (plugin.getConfiguration().OPTIONS_GIVE$ITEMS) itemStack.forEach(item -> player.getInventory().addItem(item));
            players.teleport(location);
            ChatUtils.sendMessage(players, plugin.getMessages().TELEPORTED_GROUP);
        });
    }
}
