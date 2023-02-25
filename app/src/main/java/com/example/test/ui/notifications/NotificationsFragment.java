package com.example.test.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    private Spinner text_target;
    private Spinner text_male;
    private EditText text_age;
    private EditText text_height;
    private EditText text_weight;
    private EditText text_allergy;
    private Button button_save;
    private enum Targets  {lose_weight,equal_weight,up_weight};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        user = new User(0,0,0,0,0,new String[]{"a","b"});
        button_save = root.findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() { // сохраняем данные
            @Override
            public void onClick(View view) {
                user.setTarget(text_target.getSelectedItemPosition());
                user.setMale(text_male.getSelectedItemPosition());
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
        text_target = root.findViewById(R.id.target_spinner);
        ArrayAdapter<CharSequence> target_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.target_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        target_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        text_target.setAdapter(target_adapter);
        text_male = root.findViewById(R.id.text_male);
        ArrayAdapter<CharSequence> male_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.male_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        male_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        text_male.setAdapter(male_adapter);
        text_age = root.findViewById(R.id.text_age);
        text_height = root.findViewById(R.id.text_height );
        text_weight = root.findViewById(R.id.text_weight);
        text_allergy = root.findViewById(R.id.text_allergy);
        // TextView textView = root.findViewById(R.id.text_notifications);
        user = JSONHelper.importFromJSON(getContext());
        text_target.setSelection(user.getTarget());
        text_male.setSelection(user.getMale());
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