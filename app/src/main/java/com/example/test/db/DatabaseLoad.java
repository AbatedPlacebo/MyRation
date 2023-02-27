package com.example.test.db;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class DatabaseLoad {
    List<Categories> categories;
    List<Catalog> products;

    public void populate(Context context){
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "my_ratio")
                .allowMainThreadQueries()
                .createFromAsset("database/my_ratio.db")
                .build();
        ProductDao productDao = db.productDao();
        CategoryDao categoryDao = db.categoryDao();
        categories = categoryDao.getAll();
        products = productDao.getAll();
    }
    public List<Categories> getCategories(){
        return categories;
    }
    public List<Catalog> getProducts(){
        return products;
    }
}
