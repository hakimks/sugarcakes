package com.kawesi.sugarcakes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "cake_table")
public class Cake {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "cake_name")
    private String name;
    @ColumnInfo(name = "cake_category")
    private int category;
    @ColumnInfo(name = "cake_price")
    private int price;
    @ColumnInfo(name = "creation_date")
    private Date creationDate;
    @ColumnInfo(name = "cake_ingredients")
    private String ingredients;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }




    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }




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


    public Cake(int id, String name, int price, Date creationDate, String ingredients, int category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
        this.ingredients = ingredients;
        this.category = category;
    }
}
