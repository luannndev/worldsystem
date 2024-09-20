package dev.luan.worlds.command.sub;

import dev.luan.worlds.command.SubCommand;
import dev.luan.worlds.message.MessageProvider;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class WorldTeleportCommand implements SubCommand {

    private final MessageProvider messageProvider;

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(messageProvider.noSpecifiedWorldMessage());
            return false;
        }

        final var world = Bukkit.getWorld(args[1]);
        if (world == null) {
            player.sendMessage(messageProvider.noWorldMessage());
            return false;
        }

        player.teleport(world.getSpawnLocation());
        player.sendMessage(messageProvider.getMessage("command.world.teleport.teleported").replaceText(it -> it.match("%world%").replacement(world.getName())));
        return true;
    }
}