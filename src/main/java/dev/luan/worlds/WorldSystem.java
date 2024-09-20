package dev.luan.worlds;

import dev.luan.worlds.config.ConfigManager;
import dev.luan.worlds.message.MessageProvider;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class WorldSystem extends JavaPlugin {

    private ConfigManager defaultConfig;
    private ConfigManager worldsConfig;
    private MessageProvider messageProvider;

    @Override
    public void onEnable() {
        ConfigManager configDE = new ConfigManager(this, "de.yml");
        ConfigManager configEN = new ConfigManager(this, "en.yml");

        this.defaultConfig = new ConfigManager(this, "config.yml");
        this.worldsConfig = new ConfigManager(this, "worlds.yml");
        this.messageProvider = new MessageProvider(defaultConfig, configDE, configEN);
    }

    @Override
    public void onDisable() {

    }
}
