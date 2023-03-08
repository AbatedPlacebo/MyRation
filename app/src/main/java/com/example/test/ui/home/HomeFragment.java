package com.example.test.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.databinding.FragmentHomeBinding;
import com.example.test.db.DatabaseLoad;
import com.example.test.ui.JSONHelper;
import com.example.test.ui.ProductAdapter;
import com.example.test.ui.ProductAdapterHome;
import com.example.test.ui.User;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ListView listView;
    private TextView textView_stats;
    private TextView textView_stage;
    private Button buttonForward;
    private Button buttonBack;
    private User user;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = root.findViewById(R.id.listviewHome);
        DatabaseLoad db = new DatabaseLoad(getContext());
        db.populate();
        user = JSONHelper.importFromJSON(getContext());
        db.setRations(user);
        ProductAdapter listAdapter = new ProductAdapterHome(db, getContext(),
                homeViewModel.getRationNumber().getValue() - 1);
        listView.setAdapter(listAdapter);
        homeViewModel.getRationNumber().observe(getViewLifecycleOwner(), rationNumber -> {
            textView_stage.setText(rationNumber.toString() + "/4");
            Toast toast = Toast.makeText(getContext(), "Page changed", Toast.LENGTH_SHORT);
            toast.show();
        });

        textView_stats = root.findViewById(R.id.text_stats);
        textView_stage = root.findViewById(R.id.text_stage);
        buttonBack = root.findViewById(R.id.buttonBackHome);
        buttonForward = root.findViewById(R.id.buttonForwardHome);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curValue = homeViewModel.getRationNumber().getValue();
                if (homeViewModel.getRationNumber().getValue() > 1)
                    homeViewModel.getRationNumber().setValue(curValue - 1);
            }
        });
        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curValue = homeViewModel.getRationNumber().getValue();
                if (homeViewModel.getRationNumber().getValue() < 4)
                    homeViewModel.getRationNumber().setValue(curValue + 1);
            }
        });
        textView_stats.setText(String.format("Белки: %f\nЖиры: %f\nУглеводы: %f",
                user.getCurrentProteins(),
                user.getCurrentFats(),
                user.getCurrentCarbs()));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}