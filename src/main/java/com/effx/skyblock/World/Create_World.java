package com.effx.skyblock.World;

import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;

public class Create_World extends ChunkGenerator {

    public void Create_void() {
        WorldCreator wc = new WorldCreator("esb_skyblock");
        wc.generator(new EmptyChunkGenerator());
        wc.createWorld();
    }
}
