package com.example.test.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.db.DatabaseLoad;

public class ProductAdapterHome extends ProductAdapter{
    public ProductAdapterHome(DatabaseLoad db, Context context) {
        super(db, context);
    }

    @Override
    View.OnClickListener getActionClick(int i, View singleItem, ViewGroup viewGroup) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.getProducts().remove(i);
                notifyDataSetChanged();
                if (db.getProducts().isEmpty()){
                    Toast toast = Toast.makeText(context, "Все продукты удалены",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        };
    }
}
