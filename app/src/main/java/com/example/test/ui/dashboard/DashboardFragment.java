package com.example.test.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.databinding.FragmentDashboardBinding;
import com.example.test.db.Catalog;
import com.example.test.db.DatabaseLoad;

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
        List<Catalog> list = new ArrayList<>();
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
            String name = items.get(i).productName;
            String URL = items.get(i).URL;

            TextView textView = new TextView(getContext());
            textView.setText(items.get(i).productName);
            return textView;
        }
    }

}
