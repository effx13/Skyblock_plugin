package com.effx.skyblock.Data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Files {
    public void saveFile(File f, User user) {
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        List<User> users = new ArrayList<User>();
//        users.add()
        int order = user.getOrder();
        UUID uuid = user.getUuid();
        double X = user.getX();
        double Y = user.getY();
        double Z = user.getZ();
        boolean hasIsland = user.isHasIsland();
    }
/*    public JsonObject loadFile(File f) {
        Gson gson = new Gson();
        User[] users = gson.fromJson()
    }*/
}
