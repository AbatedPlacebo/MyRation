package com.example.test.db;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys=
        {@ForeignKey(entity= Categories.class,
                onDelete=ForeignKey.CASCADE,
                onUpdate=ForeignKey.CASCADE,
                parentColumns = "id",
                childColumns = "category")
        })
public class Catalog {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "name")
    @NonNull
    public String productName;
    @ColumnInfo(name = "category")
    public Integer productCategory;
    @ColumnInfo(name = "mass")
    public Float productMass;
    @ColumnInfo(name = "volume")
    public Float productVolume;
    @ColumnInfo(name = "nutrition")
    public float productNutrition;
    @ColumnInfo(name = "proteins")
    public float productProteins;
    @ColumnInfo(name = "fats")
    public float productFats;
    @ColumnInfo(name = "carbs")
    public float productCarbs;
    @ColumnInfo(name = "url")
    public String URL;
    @ColumnInfo(name = "image")
    public String URLImage;
}
