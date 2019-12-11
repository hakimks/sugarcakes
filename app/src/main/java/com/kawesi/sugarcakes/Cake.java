package com.kawesi.sugarcakes;

import java.util.Date;

public class Cake {
    private int id;
    private String name;
    private int price;
    private Date creationDate;

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    private String ingredients;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public Cake(int id, String name, int price, Date creationDate, String ingredients) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
        this.ingredients = ingredients;
    }
}
