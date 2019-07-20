package us.fiercepvp.api.file;

import lombok.*;

import org.bukkit.configuration.file.*;
import org.bukkit.plugin.*;

import java.io.*;

@Getter
public abstract class FileSetup {

    private File file;
    private FileConfiguration config;
    private Plugin plugin;
    private String filename;

    public FileSetup(final Plugin plugin, final String filename) {
        this.plugin = plugin;
        this.filename = filename;
        this.setupFile();
    }

    public abstract void setup();

    private void setupFile() {
        try {
            this.file = new File(this.plugin.getDataFolder(), this.filename + ".yml");
            this.config = YamlConfiguration.loadConfiguration(this.file);
            if (!this.file.exists()) {
                final Reader defConfigStream = new InputStreamReader(this.plugin.getResource(this.filename + ".yml"));
                final YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                this.config.options().copyDefaults(true);
                this.config.setDefaults(defConfig);this.config.save(this.file);
            }
            this.config = YamlConfiguration.loadConfiguration(this.file);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    protected String getMessage(final String message) {
        return this.config.contains(message) ? this.config.getString(message) : "";
    }

    private static String replaceVariable(final String string, final String index, final String replacement) {
        try {
            return string.contains("%" + index) ? string.replace("%" + index, replacement) : string;
        } catch (final Exception ex) {
            return string;
        }
    }

    public static String replaceVariable(final String string, final int index, final String replacement) {
        return replaceVariable(string, "" + index, replacement);
    }

}
