package com.example.test.db;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

public class DatabaseLoad {
    public void populate(Context context){
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "database-name")
                .createFromAsset("res/database.db")
                .build();
        ProductDao productDao = db.productDao();
        List<Product> users = productDao.getAll();
    }
}
