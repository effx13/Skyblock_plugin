package com.effx.skyblock.World;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EmptyChunkGenerator extends ChunkGenerator {
    @Override
    @NonNull
    public @NotNull ChunkData generateChunkData(@NotNull World world, @NotNull Random random, int x, int z, @NotNull BiomeGrid biome) {
        return createChunkData(world);
    }
}
