package com.effx.skyblock.Utils;

import org.bukkit.entity.Player;

public class Utils {
    public int[] GenerateXY(Player player, int playerNum) {
        boolean goingUp = false;
        boolean goingPlus = true;
        int weight = 150;
        int goAbout = weight;

        int x = 0, y = 0;

        player.sendMessage("player: " + playerNum);

        while (playerNum > 0) {
            int went = 0;
            while (went < goAbout) {
                if (playerNum <= 0) {
                    break;
                }
                player.sendMessage(x + ", " + y);
                playerNum -= 1;

                went += weight;
                if (goingPlus && !goingUp) {
                    x += weight;
                } else if (goingPlus && goingUp) {
                    y += weight;
                } else if (!goingPlus && !goingUp) {
                    x -= weight;
                } else if (!goingPlus && goingUp) {
                    y -= weight;
                }
            }
            if (goingUp) {
                goAbout += weight;
                goingPlus = !goingPlus;
            }
            goingUp = !goingUp;
        }
        return new int[]{x, y};
    }
}
