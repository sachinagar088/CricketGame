package com.cricket;

import java.util.Scanner;

public class Input {
    Scanner sc=new Scanner(System.in);

    public static Input getInstance(){
        return new Input();
    }

    public int getInteger(String message){
        System.out.println(message);
        return sc.nextInt();
    }
    public String getString(String message){
        System.out.println(message);
        return sc.next();
    }
}
