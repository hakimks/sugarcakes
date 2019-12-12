package com.kawesi.sugarcakes.data;

import java.util.HashMap;

public final class CakeCategory {


    public static  HashMap<Integer, String> categoryMap = new HashMap<>();
    // add values to the hashmap

     static {
        categoryMap.put(1, "Birthday");
        categoryMap.put(2, "Wedding");
        categoryMap.put(3, "Baby Shower");
        categoryMap.put(4, "Introduction");
    }

}
