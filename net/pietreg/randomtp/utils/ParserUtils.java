package net.pietreg.randomtp.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;

public final class ParserUtils {

    private ParserUtils(){
    }

    public static ItemStack parseItemStack(String string) {
        final ItemStack is = new ItemStack(Material.DIAMOND);
        final ItemMeta im = is.getItemMeta();

        final String[] args = string.split(" ");
        for (String arg : args) {
            final String[] splitArg = arg.split(":");
            final String key = splitArg[0];
            final String value = splitArg[1];
            if (key.equalsIgnoreCase("material")) {
                final Material mat = Material.matchMaterial(value);
                if (mat == null) continue;
                is.setType(mat);
            } else if (key.equalsIgnoreCase("amount")) {
                if (!IntegerUtils.isInt(value)) continue;
                is.setAmount(Integer.parseInt(value));
            } else if (key.equalsIgnoreCase("name")) {
                final String name = value.replace("_", " ");
                im.setDisplayName(ChatUtils.colored(name));
            } else if (key.equalsIgnoreCase("lore")) {
                final List<String> lore = new LinkedList<>();
                final String[] splitLore = value.split("@nl");
                for (String s : splitLore) {
                    lore.add(ChatUtils.colored(s.replace("_", " ")));
                }
                im.setLore(lore);
            } else if (key.equalsIgnoreCase("data") || key.equalsIgnoreCase("durability")) {
                if (!IntegerUtils.isInt(value)) continue;
                final short data = (short) Integer.parseInt(value);
                is.setDurability(data);
            } else if (key.equalsIgnoreCase("enchants")) {
                final String[] enchantmentArray = value.split("@nl");
                for (String s : enchantmentArray) {
                    final String[] enchantmentSplit = s.split(";");
                    if (enchantmentSplit.length < 1) continue;
                    final Enchantment ench;
                    try {
                        ench = Enchantment.getByName(enchantmentSplit[0]);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        continue;
                    }
                    if (!IntegerUtils.isInt(enchantmentSplit[1])) continue;
                    final int level = Integer.parseInt(enchantmentSplit[1]);
                    if (ench != null) im.addEnchant(ench, level, true);
                }
            }
        }
        is.setItemMeta(im);
        return is;
    }
}
