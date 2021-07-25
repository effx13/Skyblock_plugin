package com.effx.skyblock;

import com.effx.skyblock.Commands.MainCommand;
import com.effx.skyblock.Commands.TabComplete;
import com.effx.skyblock.Events.Events;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    private final File f = new File(getDataFolder().getPath());
    public static Logger log = Logger.getLogger("Minecraft");
    public ConsoleCommandSender console = Bukkit.getConsoleSender();

    @Override
    public void onEnable() {
        log.info(ChatColor.AQUA + "활성화!");
        getCommand("esb").setTabCompleter(new TabComplete());
        getCommand("esb").setExecutor(new MainCommand());
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        setupWorldEdit();
        if (Bukkit.getWorld("esb_skyblock") != null) {
            getServer().createWorld(new WorldCreator("esb_skyblock"));
        }
        makeFile(f);
    }

    @Override
    public void onDisable() {
        log.info(ChatColor.AQUA + "비활성화!");
    }

    private void setupWorldEdit() {
        if (getServer().getPluginManager().getPlugin("WorldEdit").isEnabled()) {
            log.info(ChatColor.AQUA + "WorldEdit 감지");
        } else {
            log.info(ChatColor.RED + "WorldEdit을 찾지 못하였습니다.");
            log.info(ChatColor.RED + "WorldEdit 플러그인을 넣어주세요.");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void makeFile(File f) {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
            new File(getDataFolder().getPath() + "/users").mkdir();
        }
    }
}
