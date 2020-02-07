package net.pietreg.randomtp.configs.core.impl;

import java.io.*;

import net.pietreg.randomtp.configs.core.Configuration;
import org.bukkit.configuration.file.*;

public final class ConfigurationImpl implements Configuration {

    private final File file;
    private final Class<?> clazz;

    public ConfigurationImpl(final File file, final Class<?> clazz) {
        this.clazz = clazz;
        this.file = file;
        this.reload();
    }

    @Override
    public void reload() {
        final File config = this.check(this.file);
        try {
            final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(config);
            this.parse(this.clazz, configuration);
            configuration.save(config);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void save() {
        final File config = this.check(this.file);
        try {
            final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(config);
            this.parseSave(this.clazz, configuration);
            configuration.save(config);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
