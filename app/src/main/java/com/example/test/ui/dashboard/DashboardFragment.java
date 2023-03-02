package com.example.test.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.databinding.FragmentDashboardBinding;
import com.example.test.db.Catalog;
import com.example.test.db.DatabaseLoad;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ListView listView = root.findViewById(R.id.listview);

        DatabaseLoad db = new DatabaseLoad(getContext());
        db.populate();
        CustomAdapter listAdapter = new CustomAdapter(db);
        listView.setAdapter(listAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    class CustomAdapter extends BaseAdapter {
        DatabaseLoad db;

        public CustomAdapter(DatabaseLoad db) {
            super();
            this.db = db;
        }

        @Override
        public int getCount() {
            return db.getProducts().size();
        }

        @Override
        public Object getItem(int i) {
            return db.getProducts().get(i);
        }

        @Override
        public long getItemId(int i) {
            return db.getProducts().get(i).hashCode();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View singleItem = view;
            ProductViewHolder holder = null;
            if (singleItem == null) {
                LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                singleItem = layoutInflater.inflate(R.layout.single_product_item, viewGroup, false);
                holder = new ProductViewHolder(singleItem);
                singleItem.setTag(holder);
            } else {
                holder = (ProductViewHolder) singleItem.getTag();
            }
            final Bitmap[] bitmap = {null};
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bitmap[0] = BitmapFactory.decodeStream((InputStream) new URL(
                                db.getProducts().get(i).URLImage).getContent());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            bitmap[0] = Bitmap.createScaledBitmap(bitmap[0], holder.productImage.getWidth(),holder.productImage.getHeight(),true);
            holder.productImage.setImageBitmap(bitmap[0]);
            holder.productTitle.setText(db.getProducts().get(i).productName);
            holder.productDetails.setText(
                    String.format("Категория: %s\nБелки: %.1f\nЖиры: %.1f\nУглеводы: %.1f",
                            db.findCategoryById(db.getProducts().get(i).productCategory)
                                    .categoriesName,
                            db.getProducts().get(i).productProteins,
                            db.getProducts().get(i).productFats,
                            db.getProducts().get(i).productCarbs)
            );
            singleItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(db.getProducts().get(i).URL));
                    startActivity(browser);
                }
            });
            return singleItem;
        }
    }

}
