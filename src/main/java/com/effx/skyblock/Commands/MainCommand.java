package com.effx.skyblock.Commands;

import com.effx.skyblock.Data.Files;
import com.effx.skyblock.Data.User;
import com.effx.skyblock.Utils.Utils;
import com.effx.skyblock.World.Create_World;
import com.effx.skyblock.World.Schemetic;
import com.google.gson.Gson;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import org.apache.commons.io.IOUtils;
import org.bukkit.*;
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
                    if (p.isOp()) {
                        if (Bukkit.getWorld("esb_skyblock") != null) {
                            sendMessage_info(p, "월드가 이미 존재합니다.");
                        } else {
                            Create_World cw = new Create_World();
                            cw.Create_void();
                            sendMessage_info(p, "생성완료!");
                        }
                    } else {
                        sendMessage_error(p, "관리자가 아닙니다.");
                    }
                }
                case "make" -> {
                    Files files = new Files();
                    Utils utils = new Utils();
                    File userFile = new File(plugin.getDataFolder(), "/users/" + p.getUniqueId() + ".json");
                    World w = Bukkit.getWorld("esb_skyblock");
                    if (!userFile.exists() || !userFile.isFile()) {
                        try {
                            userFile.createNewFile();
                            int cnt = files.getFilesCount();
                            int[] XY = utils.GenerateXY(cnt);
                            files.saveFile(new User(p.getUniqueId(), XY[0], 90, XY[1], true), p);

                            Schemetic schemetic = new Schemetic();
                            InputStream is = getClass().getResourceAsStream("/skyblock.schem");
                            File tmp;
                            tmp = File.createTempFile("skyblock", ".schem");
                            tmp.deleteOnExit();
                            FileOutputStream fos = new FileOutputStream(tmp);
                            IOUtils.copy(is, fos);
                            Clipboard clipboard = schemetic.getSkyblockSchemetic(tmp);
                            schemetic.CopySchemetic(clipboard, XY[0], 90, XY[1]);
                            p.setBedSpawnLocation(new Location(w, XY[0] + 6, 84, XY[1] + 7), true);
                            sendMessage_info(p, "섬을 생성하였습니다.");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    } else {
                        sendMessage_error(p, "섬이 이미 존재합니다.");
                    }
                }
                case "tp" -> {
                    World w = Bukkit.getWorld("esb_skyblock");
                    Files files = new Files();
                    File userFile = new File(plugin.getDataFolder(), "/users/" + p.getUniqueId() + ".json");
                    if (!userFile.exists() || !userFile.isFile()) {
                        sendMessage_info(p, "아직 섬이 없습니다.");
                    } else {
                        Gson gson = new Gson();
                        User user = files.loadFile(new File(plugin.getDataFolder(), "/users/" + p.getUniqueId() + ".json"));
                        Location l = new Location(w, user.getX() + 6, user.getY() - 6, user.getZ() + 7);
                        p.teleport(l);
                        p.setBedSpawnLocation(l, true);
                    }
                }
                case "friend" -> {
                    if (args.length == 2) {
                        OfflinePlayer op = Bukkit.getOfflinePlayerIfCached(args[1]);
                        if (op != null) {
                            World w = Bukkit.getWorld("esb_skyblock");
                            Files files = new Files();
                            Utils utils = new Utils();
                            File userFile = new File(plugin.getDataFolder(), "/users/" + op.getUniqueId() + ".json");
                            if (userFile.exists()) {
                                User user = files.loadFile(userFile);
                                Location l = new Location(w, user.getX() + 6, user.getY() - 6, user.getZ() + 7);
                                p.teleport(l);
                                p.setBedSpawnLocation(l, true);
                            } else {
                                sendMessage_error(p, "해당 플레이어는 아직 섬이 없습니다.");
                            }
                        } else {
                            sendMessage_error(p, "해당 플레이어는 아직 섬이 없습니다.");
                        }
                    } else {
                        sendMessage_error(p, "뒤에 플레이어를 적어야합니다.");
                        p.sendMessage(ChatColor.GRAY + "- " + ChatColor.GREEN + "/esb friend <플레이어>" + ChatColor.WHITE + " - " + ChatColor.GRAY + "친구의 섬으로 이동합니다.");
                    }
                }
                case "back" -> {
                    if (p.getWorld().getName().equals("esb_skyblock")) {
                        World w = Bukkit.getWorld("world");
                        p.teleport(w.getSpawnLocation());
                        p.setBedSpawnLocation(null);
                    } else {
                        sendMessage_error(p, "지금 섬에 있지 않습니다.");
                    }
                }
                default -> {
                    sendMessage_error(p, "해당 명령어는 없는 명령어입니다.");
                }
            }
        } else {
            log.info(ChatColor.GRAY + "[" + ChatColor.GREEN + "Skyblock" + ChatColor.GRAY + "] " + ChatColor.RED + "콘솔에서는 사용하실 수 없습니다.");
        }
        return true;
    }

    private void help(Player p) {
        p.sendMessage(ChatColor.GRAY + "--------------------[ " + ChatColor.BLUE + "SkyBlock" + ChatColor.GRAY + " ]----------------------");
        p.sendMessage(ChatColor.GRAY + "- " + ChatColor.GREEN + "/esb help" + ChatColor.WHITE + " - " + ChatColor.GRAY + "플러그인의 도움말을 확인합니다.");
        p.sendMessage(ChatColor.GRAY + "- " + ChatColor.GREEN + "/esb create" + ChatColor.WHITE + " - " + ChatColor.GRAY + "스카이블럭 월드를 생성합니다.");
        p.sendMessage(ChatColor.GRAY + "- " + ChatColor.GREEN + "/esb make" + ChatColor.WHITE + " - " + ChatColor.GRAY + "자신의 섬을 생성합니다.");
        p.sendMessage(ChatColor.GRAY + "- " + ChatColor.GREEN + "/esb tp" + ChatColor.WHITE + " - " + ChatColor.GRAY + "자신의 섬으로 이동합니다.");
        p.sendMessage(ChatColor.GRAY + "- " + ChatColor.GREEN + "/esb friend <플레이어>" + ChatColor.WHITE + " - " + ChatColor.GRAY + "친구의 섬으로 이동합니다.");
        p.sendMessage(ChatColor.GRAY + "- " + ChatColor.GREEN + "/esb back" + ChatColor.WHITE + " - " + ChatColor.GRAY + "기본 월드로 돌아갑니다.");
    }

    private void sendMessage_info(Player p, String s) {
        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Skyblock" + ChatColor.GRAY + "] " + ChatColor.BLUE + s);
    }

    private void sendMessage_error(Player p, String s) {
        p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "Skyblock" + ChatColor.GRAY + "] " + ChatColor.RED + s);
    }
}
