package com.kawesi.sugarcakes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "cake_table")
public class Cake implements Parcelable {
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

    public static final Creator<Cake> CREATOR = new Creator<Cake>() {
        @Override
        public Cake createFromParcel(Parcel in) {
            return new Cake(in);
        }

        @Override
        public Cake[] newArray(int size) {
            return new Cake[size];
        }
    };

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


    public Cake(String name, int price, Date creationDate, String ingredients, int category) {
        this.name = name;
        this.price = price;
        this.creationDate = creationDate;
        this.ingredients = ingredients;
        this.category = category;
    }
    public Cake(Parcel in){
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        creationDate = new Date(in.readLong());
        ingredients = in.readString();
        category = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(category);
        dest.writeInt(price);
        dest.writeString(ingredients);
    }
}
