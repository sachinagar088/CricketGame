package com.cricket;

import java.util.Random;

public final class Util {
    private Util(){
    }
    public static int randomNumbers(){
        int[] arr={0,1,2,3,4,6,7};
        // assuming 7 for wicket
        return arr[new Random().nextInt(arr.length)];
    }
    public static String generateRandomCoinFace(){
        String[] arr ={"H","T"};
        return arr[new Random().nextInt(arr.length)];
    }
}
