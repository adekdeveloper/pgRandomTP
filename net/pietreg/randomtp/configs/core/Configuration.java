package net.pietreg.randomtp.configs.core;

import org.bukkit.configuration.file.*;
import org.apache.commons.lang.*;
import java.lang.reflect.*;
import java.io.*;

public interface Configuration {

    default void parseSave(final Class<?> clazz, final YamlConfiguration config) {
        try {
            for (final Field f : clazz.getFields()) {
                if (Modifier.isPublic(f.getModifiers())) {
                    String path = f.getName().toLowerCase();
                    path = StringUtils.replace(path, "_", ".");
                    path = StringUtils.replace(path, "$", "-");
                    final Object value = f.get(null);
                    config.set(path, value);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    default void parse(final Class<?> clazz, final YamlConfiguration config) {
        try {
            for (final Field f : clazz.getFields()) {
                if (Modifier.isPublic(f.getModifiers())) {
                    String path = f.getName().toLowerCase();
                    path = StringUtils.replace(path, "_", ".");
                    path = StringUtils.replace(path, "$", "-");
                    final Object def = f.get(null);
                    final Object value = config.get(path, def);
                    config.set(path, value);
                    f.set(null, value);
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    default File check(final File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return file;
    }

    void reload();

    void save();
}

