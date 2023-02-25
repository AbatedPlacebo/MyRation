package com.example.test.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.R;
import com.example.test.databinding.FragmentNotificationsBinding;
import com.example.test.ui.JSONHelper;
import com.example.test.ui.User;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private User user;
    private EditText text_target;
    private EditText text_male;
    private EditText text_age;
    private EditText text_height;
    private EditText text_weight;
    private EditText text_allergy;
    private Button button_save;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        user = new User("","Мужской",0,0,0,new String[]{"a","b"});
        button_save = root.findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() { // сохраняем данные
            @Override
            public void onClick(View view) {
                user.setTarget(String.valueOf(text_target.getText()));
                user.setMale(String.valueOf(text_male.getText()));
                user.setAge(Integer.parseInt(String.valueOf(text_age.getText())));
                user.setHeight(Float.parseFloat(String.valueOf(text_height.getText())));
                user.setWeight(Float.parseFloat(String.valueOf(text_weight.getText())));
                boolean result = JSONHelper.exportToJSON(getContext(), user);
                if(result){
                    Toast.makeText(getContext(), "Данные сохранены", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(), "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
                }
            }
        });
        text_target = root.findViewById(R.id.text_target);
        text_male = root.findViewById(R.id.text_male);
        text_age = root.findViewById(R.id.text_age);
        text_height = root.findViewById(R.id.text_height );
        text_weight = root.findViewById(R.id.text_weight);
        text_allergy = root.findViewById(R.id.text_allergy);
        // TextView textView = root.findViewById(R.id.text_notifications);
        user = JSONHelper.importFromJSON(getContext());
        text_target.setText(user.getTarget());
        text_male.setText(user.getMale());
        text_age.setText(String.valueOf(user.getAge()));
        text_height.setText(String.valueOf(user.getHeight()));
        text_weight.setText(String.valueOf(user.getWeight()));
        //text_target.setText(user.getExceps());
        //textView.setText(user.toString());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}