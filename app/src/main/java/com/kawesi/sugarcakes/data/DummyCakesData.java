package com.kawesi.sugarcakes.data;

import com.kawesi.sugarcakes.Cake;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DummyCakesData {
    ArrayList<Cake> cakes = new ArrayList<Cake>();

    Date currentTime = Calendar.getInstance().getTime();

    public ArrayList<Cake> generateDummyCakesData(){
        Cake cake1 = new Cake(1, "Cup Cake", 20000, currentTime, "Berry, Floor, Chocolate, blueband, edibale cover ");
        cakes.add(cake1);
        Cake cake2 = new Cake(1, "Milk Cake", 60000, currentTime, "Berry, Floor, Chocolate");
        cakes.add(cake2);
        Cake cake3 = new Cake(1, "Soda Cake", 25000, currentTime, "Berry, Floor, Chocolate");
        cakes.add(cake3);
        Cake cake4 = new Cake(1, "Bottle Cake", 80000, currentTime, "Berry, Floor, Chocolate");
        cakes.add(cake4);
        Cake cake5 = new Cake(1, "Hat Cake", 150000, currentTime, "Berry, Floor, Chocolate");
        cakes.add(cake5);
        Cake cake6 = new Cake(1, "Glass Cake", 20000, currentTime, "Berry, Floor, Chocolate");
        cakes.add(cake6);
        Cake cake7 = new Cake(1, "Car Cake", 20000, currentTime, "Berry, Floor, Chocolate");
        cakes.add(cake7);
        Cake cake8 = new Cake(1, "Chair Cake", 20000, currentTime, "Berry, Floor, Chocolate");
        cakes.add(cake8);
        Cake cake9 = new Cake(1, "Doll Cake", 20000, currentTime, "Berry, Floor, Chocolate");
        cakes.add(cake9);
        Cake cake10 = new Cake(1, "Graduation Cake", 20000, currentTime, "Berry, Floor, Chocolate");
        cakes.add(cake10);
        Cake cake11 = new Cake(1, "Water Cake", 20000, currentTime, "Berry, Floor, Chocolate");
        cakes.add(cake11);
        Cake cake12 = new Cake(1, "Pot Cake", 350000, currentTime, "Berry, Floor, Chocolate");
        cakes.add(cake12);
        return cakes;
    }
}