package dev.luan.worlds.command.sub;

import dev.luan.worlds.command.SubCommand;
import dev.luan.worlds.message.MessageProvider;
import dev.luan.worlds.world.WorldManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class WorldInformationCommand implements SubCommand {

    private final WorldManager worldManager;
    private final MessageProvider messageProvider;

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(messageProvider.noSpecifiedWorldMessage());
            return false;
        }

        final var world = worldManager.getWorld(args[1]);
        if (world == null) {
            player.sendMessage(messageProvider.noWorldMessage());
            return false;
        }

        player.sendMessage(messageProvider.getMessage("command.world.information.header").replaceText(it -> it.match("%world%").replacement(world.meta().getName())));
        player.sendMessage(messageProvider.getMessage("command.world.information.type").replaceText(it -> it.match("%type%").replacement(world.meta().getWorldType().getName())));
        player.sendMessage(messageProvider.getMessage("command.world.information.players").replaceText(it -> it.match("%players%").replacement(String.valueOf(world.getWorld().getPlayers().size()))));
        player.sendMessage(messageProvider.getMessage("command.world.information.mobs").replaceText(it -> it.match("%mobs%").replacement(String.valueOf(world.meta().isSpawnMobs()))));
        player.sendMessage(messageProvider.getMessage("command.world.information.animals").replaceText(it -> it.match("%animals%").replacement(String.valueOf(world.meta().isSpawnAnimals()))));
        return true;
    }
}