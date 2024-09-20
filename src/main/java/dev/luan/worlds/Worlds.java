package dev.luan.worlds;

import dev.luan.worlds.config.ConfigManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Worlds extends JavaPlugin {

    private ConfigManager defaultConfig;
    private ConfigManager worldsConfig;

    @Override
    public void onEnable() {
        ConfigManager configDE = new ConfigManager(this, "de.yml");
        ConfigManager configEN = new ConfigManager(this, "en.yml");

        this.defaultConfig = new ConfigManager(this, "config.yml");
        this.worldsConfig = new ConfigManager(this, "worlds.yml");
    }

    @Override
    public void onDisable() {

    }
}
