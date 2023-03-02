package com.example.test.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM categories")
    List<Categories> getAll();

    @Query("SELECT * FROM categories WHERE id IN (:categoriesIds)")
    List<Categories> loadAllByIds(int[] categoriesIds);

    @Query("SELECT * FROM categories WHERE id LIKE :id LIMIT 1")
    Categories findById(int id);

    @Insert
    void insertAll(Categories... categories);

    @Delete
    void delete(Categories category);
}
