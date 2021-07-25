package com.effx.skyblock.Utils;

public class Utils {
    public int[] GenerateXY(int playerNum) {
        boolean goingUp = false;
        boolean goingPlus = true;
        int weight = 150;
        int goAbout = weight;

        int x = 0, y = 0;


        while (playerNum > 0) {
            int went = 0;
            while (went < goAbout) {
                if (playerNum <= 0) {
                    break;
                }
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
