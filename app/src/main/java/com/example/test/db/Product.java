package com.example.test.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "name")
    public String productName;
    @ColumnInfo(name = "category")
    public int productCategory;
    @ColumnInfo(name = "mass")
    public String productMass;
    @ColumnInfo(name = "proteins")
    public String productProteins;
    @ColumnInfo(name = "fats")
    public String productFats;
    @ColumnInfo(name = "carbs")
    public String productCarbs;
    @ColumnInfo(name = "url")
    public String URL;
}
