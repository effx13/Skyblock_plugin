package com.effx.skyblock.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TabComplete implements TabCompleter {
    public static final String[] cmds = {"help", "create", "tp", "make", "friend", "back"};

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        Object lists[];

        List<String> completions = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            list.add(p.getName());
        }

        if (args.length == 1) {
            String partialCommand = args[0];
            List<String> commands = new ArrayList<>(Arrays.asList(cmds));
            StringUtil.copyPartialMatches(partialCommand, commands, completions);
            Collections.sort(completions);
            return completions;
        } else if (args.length == 2 && Objects.equals(args[1], "friends")) {
            return list;
        }
        return null;
    }
}
