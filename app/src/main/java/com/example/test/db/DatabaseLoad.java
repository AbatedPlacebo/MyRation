package com.example.test.db;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class DatabaseLoad {
    List<Categories> categories;
    List<Catalog> products;
    ProductDao productDao;
    CategoryDao categoryDao;
    AppDatabase db;

    public DatabaseLoad(Context context) {
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "my_ratio")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .createFromAsset("database/my_ratio.db")
                .build();
        productDao = db.productDao();
        categoryDao = db.categoryDao();
    }

    public void populate() {
        categories = categoryDao.getAll();
        products = productDao.getAll();
    }

    public Categories findCategoryById(int id) {
        return categoryDao.findById(id);
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public List<Catalog> getProducts() {
        return products;
    }
}
