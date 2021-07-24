package com.effx.skyblock.Commands;

import com.effx.skyblock.Utils.Utils;
import com.effx.skyblock.World.Create_World;
import com.effx.skyblock.World.Schemetic;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class MainCommand implements CommandExecutor {
    public ConsoleCommandSender console = Bukkit.getConsoleSender();
    public static Logger log = Logger.getLogger("Minecraft");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {

            Player p = (Player) sender;
            Plugin plugin = Bukkit.getPluginManager().getPlugin("effx_Skyblock");

            if (args.length == 0) {
                help(p);
            } else switch (args[0]) {
                case "help" -> help(p);
                case "create" -> {
                    if (Bukkit.getWorld("esb_skyblock") != null) {
                        p.sendMessage("이미 존재합니다.");
                    } else {
                        Create_World cw = new Create_World();
                        cw.Create_void();
                        p.sendMessage("생성완료!");
                    }
                }
                case "tp" -> {
                    World w = Bukkit.getWorld("esb_skyblock");
                    p.teleport(new Location(w, 0, 0, 0));
                    p.sendMessage("TP!");
                }
                case "make" -> {
                    try {
                        Schemetic schemetic = new Schemetic();
                        InputStream is = getClass().getResourceAsStream("/skyblock.schem");
                        File tmp;
                        tmp = File.createTempFile("skyblock", ".schem");
                        tmp.deleteOnExit();
                        FileOutputStream fos = new FileOutputStream(tmp);
                        IOUtils.copy(is, fos);
                        Clipboard clipboard = schemetic.getSkyblockSchemetic(tmp);
                        schemetic.CopySchemetic(clipboard, 0, 90, 0);
                        p.sendMessage("생성하였습니다.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case "test" -> {
                    Utils utils = new Utils();
                    int[] xy = utils.GenerateXY(p, Integer.parseInt(args[1]));
                    p.sendMessage(ChatColor.AQUA + (xy[0] + ", " + xy[1]));
                }
            }
        } else {
            log.info("콘솔에서는 사용하실 수 없습니다.");
        }
        return true;
    }

    private void help(Player p) {
        p.sendMessage(ChatColor.GRAY + "--------------------[ " + ChatColor.BLUE + "SkyBlock" + ChatColor.GRAY + " ]----------------------");
        p.sendMessage(ChatColor.GRAY + "- " + ChatColor.GREEN + "/esb tp" + ChatColor.GRAY + " 자신의 섬으로 이동합니다.");
    }
}
