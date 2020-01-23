package com.kawesi.sugarcakes.data;

import com.kawesi.sugarcakes.Cake;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DummyCakesData {
    ArrayList<Cake> cakes = new ArrayList<Cake>();

    Date currentTime = Calendar.getInstance().getTime();

    public ArrayList<Cake> generateDummyCakesData(){
        Cake cake1 = new Cake("Cup Cake", 20000, currentTime, "Berry, Floor, Chocolate, blueband, edibale cover ", 1);
        cakes.add(cake1);
        Cake cake2 = new Cake("Milk Cake", 60000, currentTime, "Berry, Floor, Chocolate", 2);
        cakes.add(cake2);
        Cake cake3 = new Cake("Soda Cake", 25000, currentTime, "Berry, Floor, Chocolate", 3);
        cakes.add(cake3);
        Cake cake4 = new Cake("Bottle Cake", 80000, currentTime, "Berry, Floor, Chocolate", 1);
        cakes.add(cake4);
        Cake cake5 = new Cake("Hat Cake", 150000, currentTime, "Berry, Floor, Chocolate", 2);
        cakes.add(cake5);
        Cake cake6 = new Cake("Glass Cake", 20000, currentTime, "Berry, Floor, Chocolate", 3);
        cakes.add(cake6);
        Cake cake7 = new Cake("Car Cake", 20000, currentTime, "Berry, Floor, Chocolate", 1);
        cakes.add(cake7);
        Cake cake8 = new Cake("Chair Cake", 20000, currentTime, "Berry, Floor, Chocolate", 2);
        cakes.add(cake8);
        Cake cake9 = new Cake("Doll Cake", 20000, currentTime, "Berry, Floor, Chocolate", 3);
        cakes.add(cake9);
        Cake cake10 = new Cake("Graduation Cake", 20000, currentTime, "Berry, Floor, Chocolate", 1);
        cakes.add(cake10);
        Cake cake11 = new Cake("Water Cake", 20000, currentTime, "Berry, Floor, Chocolate", 2);
        cakes.add(cake11);
        Cake cake12 = new Cake("Pot Cake", 350000, currentTime, "Berry, Floor, Chocolate", 3);
        cakes.add(cake12);
        return cakes;
    }
}
