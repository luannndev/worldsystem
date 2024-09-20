package dev.luan.worlds.command.sub;

import dev.luan.worlds.command.SubCommand;
import dev.luan.worlds.command.WorldCommand;
import dev.luan.worlds.generator.EmptyChunkGenerator;
import dev.luan.worlds.message.MessageProvider;
import dev.luan.worlds.world.WorldManager;
import dev.luan.worlds.world.WorldMeta;
import lombok.RequiredArgsConstructor;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class WorldCreateCommand implements SubCommand {

    private final WorldCommand parent;
    private final WorldManager worldManager;
    private final MessageProvider messageProvider;

    @Override
    public boolean execute(Player player, String[] args) {
        if (args.length < 3) {
            player.sendMessage(messageProvider.noSpecifiedWorldMessage());
            return false;
        }

        WorldType worldType;
        World.Environment environment;
        String generator = null;

        switch (args[2].toLowerCase()) {
            case "normal":
                worldType = WorldType.NORMAL;
                environment = World.Environment.NORMAL;
                break;
            case "flat":
                worldType = WorldType.FLAT;
                environment = World.Environment.NORMAL;
                break;
            case "amplified":
                worldType = WorldType.AMPLIFIED;
                environment = World.Environment.NORMAL;
                break;
            case "large_biomes":
                worldType = WorldType.LARGE_BIOMES;
                environment = World.Environment.NORMAL;
                break;
            case "nether":
                worldType = WorldType.NORMAL;
                environment = World.Environment.NETHER;
                break;
            case "end":
                worldType = WorldType.NORMAL;
                environment = World.Environment.THE_END;
                break;
            case "void":
                worldType = WorldType.NORMAL;
                environment = World.Environment.NORMAL;
                generator = new EmptyChunkGenerator().getName();
                break;
            default:
                parent.sendUsage(player);
                return false;
        }

        if (worldManager.exists(args[1])) {
            player.sendMessage(messageProvider.getMessage("command.world.create.alreadyExists"));
            return false;
        }

        //create world with given arguments
        worldManager.createWorld(new WorldMeta(args[1],
                worldType,
                environment,
                generator, 0L,
                true,
                true,
                true,
                true,
                true));

        player.sendMessage(messageProvider.getMessage("command.world.create.create-success").replaceText(it -> it.match("%world%").replacement(args[1])));
        return true;
    }
}