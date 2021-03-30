package com.sachin.CricketGame.util;

import java.util.Random;

public final class Util {
    private Util(){
    }
    public static int randomNumbers(String playerRole){
        // assume 7 for wicket
        if(playerRole.equals("Batsman")) {
            int[] arr = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 6, 6, 6, 7,7};
            return arr[new Random().nextInt(arr.length)];
        }
        else{
            int[] arr = {0,0,0,1,1,1,2,2,2,3,4,6,7,7,7};
            return arr[new Random().nextInt(arr.length)];
        }
    }
    public static String generateRandomCoinFace(){
        return String.valueOf(Coin.values()[new Random().nextInt(2)]);
    }
}
