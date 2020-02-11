package net.pietreg.randomtp.managers;

import com.google.common.collect.Lists;
import net.pietreg.randomtp.configs.Config;
import net.pietreg.randomtp.configs.Messages;
import net.pietreg.randomtp.utils.ChatUtils;
import net.pietreg.randomtp.utils.LocationUtils;
import net.pietreg.randomtp.utils.ParserUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public final class TeleportManager {

    private final List<ItemStack> itemStack;

    public TeleportManager(){
        this.itemStack = Lists.newArrayList();
        for (String s : Config.ITEMS){
            itemStack.add(ParserUtils.parseItemStack(s));
        }
    }

    public void teleportSolo(final Player player){
        final Location location = LocationUtils.getRandomCords();
        if (Config.OPTIONS_GIVE$ITEMS) itemStack.forEach(item -> player.getInventory().addItem(item));
        player.teleport(location);
        ChatUtils.sendMessage(player, Messages.TELEPORTED_SOLO);
    }

    public void teleportGroup(final Player player){
        if (Config.OPTIONS_GROUP_USE$PLATE && !player.getLocation().getBlock().getType().toString().contains("PLATE")) return;
        final Location location = LocationUtils.getRandomCords();
        LocationUtils.findPlayers(player.getLocation(), Config.OPTIONS_GROUP_RADIUS, Config.OPTIONS_GROUP_USE$PLATE).forEach(players -> {
            if (Config.OPTIONS_GROUP_REMOVE$EFFECTS) players.getActivePotionEffects().forEach(potionEffect -> players.removePotionEffect(potionEffect.getType()));
            if (Config.OPTIONS_GIVE$ITEMS) itemStack.forEach(item -> player.getInventory().addItem(item));
            players.teleport(location);
            ChatUtils.sendMessage(players, Messages.TELEPORTED_GROUP);
        });
    }
}
