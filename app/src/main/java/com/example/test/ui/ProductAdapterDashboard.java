package com.example.test.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.db.DatabaseLoad;

public class ProductAdapterDashboard extends ProductAdapter {
    public ProductAdapterDashboard(DatabaseLoad db, Context context) {
        super(db, context);
    }

    @Override
    View.OnClickListener getActionClick(int i, View singleItem, ViewGroup viewGroup) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browser = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(db.getProducts().get(i).URL));
                context.startActivity(browser);
            }
        };
    }
}
