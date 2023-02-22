package com.example.test.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.databinding.FragmentDashboardBinding;

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
        List<String> list = new ArrayList<>();
        for(int i=0;i<100;i++)
            list.add("Item "+i);
        CustomAdapter listAdapter = new CustomAdapter(list);
        listView.setAdapter(listAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    class CustomAdapter extends BaseAdapter {
        List<String> items;

        public CustomAdapter(List<String> items) {
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
            TextView textView = new TextView(getContext());
            textView.setText(items.get(i));
            return textView;
        }
    }

}
