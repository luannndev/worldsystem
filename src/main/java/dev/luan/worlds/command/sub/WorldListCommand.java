package dev.luan.worlds.command.sub;

import dev.luan.worlds.command.SubCommand;
import dev.luan.worlds.message.MessageProvider;
import dev.luan.worlds.world.WorldManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class WorldListCommand implements SubCommand {

    private final WorldManager worldManager;
    private final MessageProvider messageProvider;

    @Override
    public boolean execute(Player player, String[] args) {
        player.sendMessage(messageProvider.getMessage("command.world.list.header"));

        var worlds = worldManager.getWorlds();
        if (worlds.isEmpty()) {
            player.sendMessage(messageProvider.getMessage("command.world.list.noWorlds"));
            return false;
        }

        worlds.stream()
                .filter(worldName -> worldManager.getWorld(worldName).meta().isLoaded())
                .forEach(worldName -> player.sendMessage(messageProvider.getMessage("command.world.list.loop").replaceText(text -> text.match("%world%").replacement(worldName))));
        return true;
    }
}