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
    int stage;
    public ProductAdapterHome(DatabaseLoad db, Context context, int stage) {
        super(db, context);
        this.stage = stage;
    }

    @Override
    public int getCount() {
        return db.getListRation(stage).size();
    }

    @Override
    public Object getItem(int i) {
        return db.getListRation(stage).get(i);
    }

    @Override
    public long getItemId(int i) {
        return db.getListRation(stage).get(i).hashCode();
    }

    @Override
    View.OnClickListener getActionClick(int i, View singleItem, ViewGroup viewGroup) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.getListRation(stage).remove(i);
                notifyDataSetChanged();
                if (db.getListRation(stage).isEmpty()){
                    Toast toast = Toast.makeText(context, "Все продукты удалены",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        };
    }
}
