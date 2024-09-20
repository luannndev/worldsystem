package dev.luan.worlds.command.sub;

import dev.luan.worlds.command.SubCommand;
import dev.luan.worlds.message.MessageProvider;
import dev.luan.worlds.world.WorldManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;

@RequiredArgsConstructor
public class WorldImportCommand implements SubCommand {

    private final WorldManager worldManager;
    private final MessageProvider messageProvider;

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(messageProvider.noSpecifiedWorldMessage());
            return false;
        }

        boolean force = args.length > 2 && args[2].equalsIgnoreCase("-force");

        var world = Bukkit.getWorld(args[1]);
        if (world != null && !force) {
            player.sendMessage(messageProvider.getMessage("command.world.import.alreadyExists"));
            return false;
        }

        if (!(new File(System.getProperty("user.dir") + "/" + args[1])).exists()) {
            player.sendMessage(messageProvider.noWorldMessage());
            return true;
        }

        final var worlds = worldManager.importWorld(args[1]);

        player.sendMessage(messageProvider.getMessage("command.world.import.success").replaceText(text -> text.match("%world%").replacement(worlds.meta().getName())));
        return true;
    }
}