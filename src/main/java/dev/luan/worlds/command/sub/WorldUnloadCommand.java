package dev.luan.worlds.command.sub;

import dev.luan.worlds.command.SubCommand;
import dev.luan.worlds.command.WorldCommand;
import dev.luan.worlds.message.MessageProvider;
import dev.luan.worlds.world.WorldManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class WorldUnloadCommand implements SubCommand {

    private final WorldCommand parent;
    private final WorldManager worldManager;
    private final MessageProvider messageProvider;

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(messageProvider.noSpecifiedWorldMessage());
            return false;
        }

        var world = worldManager.getWorld(args[1]);
        if (!this.worldManager.exists(world)) {
            player.sendMessage(messageProvider.noWorldMessage());
            return false;
        }

        //teleport all players that are still on the world to the default world
        parent.teleportToDefaultWorld(world);

        this.worldManager.unloadWorld(world.meta().getName());
        player.sendMessage(messageProvider.getMessage("command.world.unload.success").replaceText(text -> text.match("%world%").replacement(world.meta().getName())));
        return true;
    }
}