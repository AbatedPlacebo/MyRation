package com.example.test.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM catalog")
    List<Catalog> getAll();

    @Query("SELECT * FROM catalog WHERE id IN (:productIds)")
    List<Catalog> loadAllByIds(int[] productIds);

    @Query("SELECT * FROM catalog WHERE name LIKE :name LIMIT 1")
    Catalog findByName(String name);

    @Insert
    void insertAll(Catalog... catalogs);

    @Delete
    void delete(Catalog catalog);
}
