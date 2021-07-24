package com.effx.skyblock.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabComplete implements TabCompleter {
    public static final String[] cmds = {"help", "test", "create", "tp"};

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<String>();

        if (args.length == 1) {
            String partialCommand = args[0];
            List<String> commands = new ArrayList<>(Arrays.asList(cmds));
            StringUtil.copyPartialMatches(partialCommand, commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
