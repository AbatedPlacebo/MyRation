package com.example.test.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Catalog.class, Categories.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract CategoryDao categoryDao();
}
