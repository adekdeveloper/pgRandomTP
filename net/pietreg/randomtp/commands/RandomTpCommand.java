package net.pietreg.randomtp.commands;

import net.pietreg.randomtp.RandomTeleportPlugin;
import net.pietreg.randomtp.configs.Messages;
import net.pietreg.randomtp.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class RandomTpCommand implements CommandExecutor {

    private RandomTeleportPlugin plugin;

    public RandomTpCommand(final RandomTeleportPlugin randomTeleportPlugin) {
        this.plugin = randomTeleportPlugin;
        this.plugin.getCommand("randomtp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return ChatUtils.sendMessage(sender, "&4Blad: &cKomenda nie jest przeznaczona dla CONSOLE.");
        if (!sender.hasPermission("pgRandomTP.management")) return ChatUtils.sendMessage(sender, "&4Blad: &cNie masz uprawnien! &7(pgRandomTP.management)");
        if (args.length != 2) return ChatUtils.sendMessage(sender, "&7Poprawne uzycie: &a/randomtp <solo/group> <set/remove>");

        final Player player = (Player) sender;
        final Block block = player.getTargetBlock((Set<Material>) null, 5);

        if (!block.getType().toString().contains("BUTTON")) return ChatUtils.sendMessage(player, Messages.COMMAND_NO$BUTTON);

        if (args[0].equalsIgnoreCase("solo")){
            if (args[1].equalsIgnoreCase("set")){
                if (plugin.getButtonManager().isSoloButton(block.getLocation()) || plugin.getButtonManager().isGroupButton(block.getLocation())) {
                    return ChatUtils.sendMessage(player, Messages.COMMAND_ENGAGED$BUTTON);
                }
                if (block.getType() != Material.WOOD_BUTTON) {
                    return ChatUtils.sendMessage(player, Messages.COMMAND_SOLO_MUST$WOOD$BUTTON);
                }
                plugin.getButtonManager().addSoloButton(block.getLocation());
                return ChatUtils.sendMessage(player, Messages.COMMAND_SOLO_CREATED);
            }
            if (args[1].equalsIgnoreCase("remove")){
                if (plugin.getButtonManager().isGroupButton(block.getLocation())) {
                    return ChatUtils.sendMessage(player, Messages.COMMAND_OTHER$BUTTON);
                }
                if (!plugin.getButtonManager().isSoloButton(block.getLocation())) {
                    return ChatUtils.sendMessage(player, Messages.COMMAND_NO$TELEPORTER);
                }
                plugin.getButtonManager().removeSoloButton(block.getLocation());
                return ChatUtils.sendMessage(player, Messages.COMMAND_SOLO_REMOVED);
            }
        }
        if (args[0].equalsIgnoreCase("group")){
            if (args[1].equalsIgnoreCase("set")){
                if (plugin.getButtonManager().isSoloButton(block.getLocation()) || plugin.getButtonManager().isGroupButton(block.getLocation())) {
                    return ChatUtils.sendMessage(player, Messages.COMMAND_ENGAGED$BUTTON);
                }
                if (block.getType() != Material.STONE_BUTTON) {
                    return ChatUtils.sendMessage(player, Messages.COMMAND_GROUP_MUST$STONE$BUTTON);
                }
                plugin.getButtonManager().addGroupButton(block.getLocation());
                return ChatUtils.sendMessage(player, Messages.COMMAND_GROUP_CREATED);
            }
            if (args[1].equalsIgnoreCase("remove")){
                if (plugin.getButtonManager().isSoloButton(block.getLocation())) {
                    return ChatUtils.sendMessage(player, Messages.COMMAND_OTHER$BUTTON);
                }
                if (!plugin.getButtonManager().isGroupButton(block.getLocation())) {
                    return ChatUtils.sendMessage(player, Messages.COMMAND_NO$TELEPORTER);
                }
                plugin.getButtonManager().removeGroupButton(block.getLocation());
                return ChatUtils.sendMessage(player, Messages.COMMAND_GROUP_REMOVED);
            }
        }
        return ChatUtils.sendMessage(player, "&7Poprawne uzycie: &a/randomtp <solo/group> <set/remove>");
    }
}
