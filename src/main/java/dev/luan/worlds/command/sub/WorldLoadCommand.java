package dev.luan.worlds.command.sub;

import dev.luan.worlds.command.SubCommand;
import dev.luan.worlds.message.MessageProvider;
import dev.luan.worlds.world.WorldManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class WorldLoadCommand implements SubCommand {

    private final WorldManager worldManager;
    private final MessageProvider messageProvider;

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(messageProvider.noSpecifiedWorldMessage());
            return false;
        }

        final var world = worldManager.getWorld(args[1]);
        if (!this.worldManager.exists(world)) {
            player.sendMessage(messageProvider.noWorldMessage());
            return false;
        }

        this.worldManager.loadWorld(world.meta().getName());
        player.sendMessage(messageProvider.getMessage("command.world.load.success").replaceText(text -> text.match("%world%").replacement(world.meta().getName())));
        return true;
    }
}