package de.devcyntrix.ndg;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/*
 * NoDiamondGenerator Project
 * 1.0.0 SNAPSHOT
 *
 * © 2018 Ricardo Borutta
 */
public class NDGPlugin extends JavaPlugin implements Listener {

    private List<String> worlds;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        this.worlds = getConfig().getStringList("worlds");
    }

    @EventHandler
    public void onWorld(ChunkPopulateEvent event) {
        if (worlds == null) return;
        if (!worlds.contains(event.getWorld().getName())) return;

        Chunk chunk = event.getChunk();

        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    Block block = chunk.getBlock(x, y, z);
                    if (block.getType() == Material.DIAMOND_ORE) {
                        block.setType(Material.STONE);
                    }
                }
            }
        }
    }
}
