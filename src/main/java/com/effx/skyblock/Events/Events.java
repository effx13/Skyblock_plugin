package com.effx.skyblock.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class Events implements Listener {
    Plugin plugin = Bukkit.getPluginManager().getPlugin("effx_Skyblock");

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();

    }
}
