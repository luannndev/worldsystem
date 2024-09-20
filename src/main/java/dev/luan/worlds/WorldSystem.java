package dev.luan.worlds;

import dev.luan.worlds.command.WorldCommand;
import dev.luan.worlds.config.ConfigManager;
import dev.luan.worlds.listener.PlayerJoinListener;
import dev.luan.worlds.message.MessageProvider;
import dev.luan.worlds.world.WorldManager;
import dev.luan.worlds.world.WorldManagerImpl;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class WorldSystem extends JavaPlugin {

    private ConfigManager defaultConfig;
    private ConfigManager worldsConfig;
    private MessageProvider messageProvider;
    private WorldManager worldManager;

    @Override
    public void onEnable() {
        ConfigManager configDE = new ConfigManager(this, "de.yml");
        ConfigManager configEN = new ConfigManager(this, "en.yml");

        this.defaultConfig = new ConfigManager(this, "config.yml");
        this.worldsConfig = new ConfigManager(this, "worlds.yml");
        this.messageProvider = new MessageProvider(defaultConfig, configDE, configEN);
        this.worldManager = new WorldManagerImpl(this, worldsConfig.getConfiguration());

        worldManager.loadWorlds();

        getCommand("world").setExecutor(new WorldCommand(this, worldManager, messageProvider));
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    @Override
    public void onDisable() {
        if (worldManager != null) {
            worldManager.saveWorlds();
        }
    }
}