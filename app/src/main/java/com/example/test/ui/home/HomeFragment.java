package com.example.test.ui.home;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.databinding.FragmentHomeBinding;
import com.example.test.db.DatabaseLoad;
import com.example.test.ui.dashboard.CustomAdapter;
import com.example.test.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ListView listView;
    private TextView textView_stats;
    private TextView textView_stage;
    private Button buttonForward;
    private Button buttonBack;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = root.findViewById(R.id.listviewHome);
        DatabaseLoad db = new DatabaseLoad(getContext());
        db.populate();
        com.example.test.ui.dashboard.CustomAdapter listAdapter = new com.example.test.ui.dashboard.CustomAdapter(db, getContext());
        listView.setAdapter(listAdapter);


        homeViewModel.getRationNumber().observe(getViewLifecycleOwner(), rationNumber -> {
            textView_stage.setText(rationNumber.toString() + "/4");

        });
        textView_stats = root.findViewById(R.id.text_stats);
        textView_stage = root.findViewById(R.id.text_stage);
        buttonBack = root.findViewById(R.id.buttonBackHome);
        buttonForward = root.findViewById(R.id.buttonForwardHome);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curValue =homeViewModel.getRationNumber().getValue();
                if (homeViewModel.getRationNumber().getValue() > 1)
                    homeViewModel.getRationNumber().setValue(curValue - 1);
            }
        });
        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curValue =homeViewModel.getRationNumber().getValue();
                if (homeViewModel.getRationNumber().getValue() < 4)
                    homeViewModel.getRationNumber().setValue(curValue + 1);
            }
        });

        textView_stats.setText("Жиры: 20\nБелки: 15\nУглеводы: 100");


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    public class CustomAdapter extends BaseAdapter {
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