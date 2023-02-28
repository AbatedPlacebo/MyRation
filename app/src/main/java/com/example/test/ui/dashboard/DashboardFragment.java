package com.example.test.ui.dashboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

        DatabaseLoad db = new DatabaseLoad();
        db.populate(getContext());
        CustomAdapter listAdapter = new CustomAdapter(db.getProducts());
        listView.setAdapter(listAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    class CustomAdapter extends BaseAdapter {
        List<Catalog> items;

        public CustomAdapter(List<Catalog> items) {
            super();
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return items.get(i).hashCode();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View singleItem = view;
            ProductViewHolder holder = null;
            if (singleItem == null){
                LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                singleItem = layoutInflater.inflate(R.layout.single_product_item,viewGroup, false);
                holder = new ProductViewHolder(singleItem);
                singleItem.setTag(holder);
            }
            else {
                holder = (ProductViewHolder) singleItem.getTag();
            }
            final Bitmap[] bitmap = {null};
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try  {
                        bitmap[0] = BitmapFactory.decodeStream((InputStream)new URL(
                                items.get(i).URL).getContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

            holder.productImage.setImageBitmap(bitmap[0]);
            holder.productTitle.setText(items.get(i).productName);
            holder.productDetails.setText(
                    "Категория: " + items.get(i).productCategory +
                    "\nБелки: " + String.valueOf(items.get(i).productProteins) +
                            "\nЖиры: " + String.valueOf(items.get(i).productFats) +
                            "\nУглеводы: " + String.valueOf(items.get(i).productCarbs)
            );
            singleItem.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "You clicked:" + items.get(i).productName,
                            Toast.LENGTH_SHORT).show();
                }
            });
            return singleItem;
        }
    }

}
