package com.example.test.db;

import android.content.Context;

import androidx.room.Room;

import com.example.test.ui.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseLoad {
    List<Categories> categories;
    List<Catalog> products;
    ProductDao productDao;
    CategoryDao categoryDao;
    AppDatabase db;
    List<Catalog> breakfast;
    List<List<Catalog>> rationsready;

    public DatabaseLoad(Context context) {
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "my_ratio")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .createFromAsset("database/my_ratio.db")
                .build();
        productDao = db.productDao();
        categoryDao = db.categoryDao();
        rationsready = new ArrayList<>(4);
    }

    public void populate() {
        categories = categoryDao.getAll();
        products = productDao.getAll();
    }

    public void setRations(User user){
        rationsready.add(new ArrayList<>());
        double[] stats = user.getBreakfastStats();
        breakfast = productDao.getBreakfast();
        Random random = new Random();
        int magic = random.nextInt(breakfast.size());
        double cal = 0, prot = 0, fat = 0, carb = 0;
        do{
            if (magic == breakfast.size())
                magic = 0;
            rationsready.get(0).add(breakfast.get(magic));
            magic = random.nextInt(breakfast.size());
            cal += breakfast.get(magic).productNutrition;
            prot += breakfast.get(magic).productProteins;
            fat += breakfast.get(magic).productFats;
            carb += breakfast.get(magic).productCarbs;
        }while (cal < stats[0] && prot < stats[1]
                && fat < stats[2] && carb < stats[3]);
    }

    public Categories findCategoryById(int id) {
        return categoryDao.findById(id);
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public List<Catalog> getListRation(int i) {
        if (i >= 0 && i <= 4)
            return rationsready.get(i);
        return null;
    }

    public List<Catalog> getProducts() {
        return products;
    }

}
