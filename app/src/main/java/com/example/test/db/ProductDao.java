package com.example.test.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM catalog LIMIT 10")
    List<Catalog> getAll();

    @Query("SELECT * FROM catalog WHERE category = 1 OR category = 2 " +
            "OR category = 8 OR category = 25 OR category = 6 OR category = 7")
    List<Catalog> getBreakfast();

    @Query("SELECT * FROM catalog WHERE category = 1 OR category = 2 " +
            "OR category = 8 OR category = 25 OR category = 27 OR category = 26 " +
            "OR category = 3 OR category = 4 OR category = 5 OR category = 10")
    List<Catalog> getLunch();

    @Query("SELECT * FROM catalog WHERE category = 9 OR category = 16 " +
            "OR category = 8 OR category = 17 OR category = 26")
    List<Catalog> getDinner();

    @Query("SELECT * FROM catalog WHERE category = 24 OR category = 20 " +
            "OR category = 9")
    List<Catalog> getSnack();

    @Query("SELECT * FROM catalog WHERE id IN (:productIds)")
    List<Catalog> loadAllByIds(int[] productIds);

    @Query("SELECT * FROM catalog WHERE name LIKE :name LIMIT 1")
    Catalog findByName(String name);

    @Insert
    void insertAll(Catalog... catalogs);

    @Delete
    void delete(Catalog catalog);
}
