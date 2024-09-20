package dev.luan.worlds.world;

import java.util.Set;

public interface WorldManager {

    void createWorld(WorldMeta meta);

    void deleteWorld(String name);

    boolean exists(String name);

    default boolean exists(Worlds world) {
        return exists(world.meta().getName());
    }

    Worlds getWorld(String name);

    Set<String> getWorlds();

    Worlds importWorld(String name);

    void loadWorld(String name);

    void loadWorlds();

    void unloadWorld(String name);

    void save(WorldMeta meta);

    default void save(Worlds world) {
        save(world.meta());
    }

    void saveWorlds();

}