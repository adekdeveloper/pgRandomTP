package net.pietreg.randomtp.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class ChatUtils {

    private ChatUtils(){
    }

    public static String colored(final String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean sendMessage(final Player player, final String messages){
        player.sendMessage(colored(messages));
        return true;
    }

    public static boolean sendMessage(final CommandSender commandSender, final String message){
        commandSender.sendMessage(colored(message));
        return true;
    }
}
