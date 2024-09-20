package dev.luan.worlds.listener;

import dev.luan.worlds.WorldSystem;
import dev.luan.worlds.world.Worlds;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {

    private final WorldSystem plugin;

    @EventHandler
    public void handle(PlayerJoinEvent event) {
        World spawnWorld = Bukkit.getWorld(plugin.getDefaultConfig().getConfiguration().getString("default-world"));

        if (spawnWorld == null) {
            plugin.getLogger().warning("[Worlds] The configured spawn world was not found, the spawn position of the player is not changed!");
            return;
        }

        if (event.getPlayer().hasPlayedBefore()) {
            return;
        }

        event.getPlayer().teleport(spawnWorld.getSpawnLocation());
    }
}