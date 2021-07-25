package com.effx.skyblock.Data;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.*;

public class Files {
    Plugin plugin = Bukkit.getPluginManager().getPlugin("effx_Skyblock");
    public void saveFile(User user, Player player) {
        Gson gson = new Gson();
        gson.toJson(user);
        File file = new File(plugin.getDataFolder(), "/users/" + player.getUniqueId() + ".json");
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(gson.toJson(user));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public User loadFile(File f) {
        Reader reader = null;
        Gson gson = null;
        try {
            reader = new FileReader(f);
            gson = new Gson();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return gson.fromJson(reader, User.class);
    }

    public int getFilesCount() {
        File dir = new File(plugin.getDataFolder(), "/users/");
        File[] files = dir.listFiles();
        return files.length;
    }

}
