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
    private Spinner spinner_target;
    private Spinner spinner_male;
    private EditText text_age;
    private EditText text_height;
    private EditText text_weight;
    //private EditText text_allergy;
    private Spinner spinner_activity;
    private Button button_save;
    private enum Targets  {lose_weight,equal_weight,up_weight};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        user = new User(0,0,0,0,0,0,0);
        button_save = root.findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() { // сохраняем данные
            @Override
            public void onClick(View view) {
                user.setTarget(spinner_target.getSelectedItemPosition());
                user.setMale(spinner_male.getSelectedItemPosition());
                user.setAge(Integer.parseInt(String.valueOf(text_age.getText())));
                user.setHeight(Float.parseFloat(String.valueOf(text_height.getText())));
                user.setWeight(Float.parseFloat(String.valueOf(text_weight.getText())));
                user.setActivity(spinner_activity.getSelectedItemPosition());
                user.setPFCC();
                boolean result = JSONHelper.exportToJSON(getContext(), user);
                if(result){
                    Toast.makeText(getContext(), "Данные сохранены", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getContext(), "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
                }
            }
        });
        spinner_target = root.findViewById(R.id.spinner_target);
        ArrayAdapter<CharSequence> target_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.target_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        target_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_target.setAdapter(target_adapter);
        spinner_male = root.findViewById(R.id.spinner_male);
        ArrayAdapter<CharSequence> male_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.male_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        male_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_male.setAdapter(male_adapter);
        text_age = root.findViewById(R.id.text_age);
        text_height = root.findViewById(R.id.text_height );
        text_weight = root.findViewById(R.id.text_weight);
        spinner_activity = root.findViewById(R.id.spinner_activity);
        ArrayAdapter<CharSequence> activity_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.activity_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        activity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_activity.setAdapter(activity_adapter);
        user = JSONHelper.importFromJSON(getContext());
        spinner_target.setSelection(user.getTarget());
        spinner_male.setSelection(user.getMale());
        text_age.setText(String.valueOf(user.getAge()));
        text_height.setText(String.valueOf(user.getHeight()));
        text_weight.setText(String.valueOf(user.getWeight()));
        spinner_activity.setSelection(user.getActivity());
        //text_target.setText(user.getExceps());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}