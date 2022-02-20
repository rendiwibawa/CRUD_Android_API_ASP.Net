package com.example.apiretrofitasp2.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model implements Serializable {
    //Inisialisai tipe data yang ada di api nya

    @SerializedName("id")
    private int id ;

    @SerializedName("name")
    private String name ;

    @SerializedName("price")
    private int price ;

    @SerializedName("quantity")
    private int quantity ;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}