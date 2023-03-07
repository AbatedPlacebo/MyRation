package com.example.test.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.test.R;
import com.example.test.db.Catalog;
import com.example.test.db.DatabaseLoad;
import com.squareup.picasso.Picasso;

abstract public class ProductAdapter extends BaseAdapter {
    DatabaseLoad db;
    Context context;
    public ProductAdapter(DatabaseLoad db, Context context) {
        super();
        this.context = context;
        this.db = db;
    }

    @Override
    abstract public int getCount();

    @Override
    abstract public Object getItem(int i);

    @Override
    abstract public long getItemId(int i);

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View singleItem = view;
        ProductViewHolder holder = null;
        if (singleItem == null) {
            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.
                    inflate(R.layout.single_product_item, viewGroup, false);
            holder = new ProductViewHolder(singleItem);
            singleItem.setTag(holder);
        } else {
            holder = (ProductViewHolder) singleItem.getTag();
        }

        Picasso.get().load(((Catalog) getItem(i)).URLImage).into(holder.productImage);
        holder.productTitle.setText(((Catalog) getItem(i)).productName);
        holder.productDetails.setText(
                String.format("Категория: %s\nЦена: %d ₽\nБелки: %.1f\nЖиры: %.1f\nУглеводы: %.1f",
                        db.findCategoryById(((Catalog) getItem(i)).productCategory)
                                .categoriesName,
                        ((Catalog) getItem(i)).productPrice,
                        ((Catalog) getItem(i)).productProteins,
                        ((Catalog) getItem(i)).productFats,
                        ((Catalog) getItem(i)).productCarbs)
        );
        singleItem.setOnClickListener(getActionClick(i, singleItem, viewGroup));

        return singleItem;
    }
    abstract View.OnClickListener getActionClick(int i, View singleItem, ViewGroup viewGroup);
}
