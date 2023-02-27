package com.example.test.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Categories {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "name")
    @NonNull
    public String categoriesName;
}
