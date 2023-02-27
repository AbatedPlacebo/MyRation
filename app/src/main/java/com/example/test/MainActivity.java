package com.example.test;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.ui.JSONHelper;
import com.example.test.ui.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.test.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Spinner spinner_target;
    private Spinner spinner_male;
    private TextView very_low_text;
    private TextView low_text;
    private TextView middle_text;
    private TextView high_text;
    private TextView very_high_text;
    private TextInputEditText text_age;
    private TextInputLayout container_age;
    private TextInputEditText text_height;
    private TextInputLayout container_height;
    private TextInputEditText text_weight;
    private TextInputLayout container_weight;
    // private EditText text_allergy;
    private Spinner spinner_activity;
    private Button button_next;
    private ImageButton button_info_activity;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = JSONHelper.importFromJSON(this); // если уже есть инфа, то просто запускаем основу
        if(user == null){
            setContentView(R.layout.questions);
            spinner_target = findViewById(R.id.spinner_target);
            ArrayAdapter<CharSequence> target_adapter = ArrayAdapter.createFromResource(this,
                    R.array.target_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
            target_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
            spinner_target.setAdapter(target_adapter);

            spinner_male = findViewById(R.id.spinner_male);
            ArrayAdapter<CharSequence> male_adapter = ArrayAdapter.createFromResource(this,
                    R.array.male_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
            male_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
            spinner_male.setAdapter(male_adapter);

            text_age = findViewById(R.id.text_age);
            container_age = findViewById(R.id.ageContainer);
            text_age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b){
                        if(text_age.getText().length() != 0) container_age.setHelperText(validAge());
                    }
                }
            });

            text_height = findViewById(R.id.text_height);
            container_height = findViewById(R.id.heightContainer);
            text_height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b){
                        if(text_height.getText().length() != 0) container_height.setHelperText(validHeight());
                    }
                }
            });

            text_weight = findViewById(R.id.text_weight);
            container_weight = findViewById(R.id.weightContainer);
            text_weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b){
                        if(text_weight.getText().length() != 0) container_weight.setHelperText(validWeight());
                    }
                }
            });

            spinner_activity = findViewById(R.id.spinner_activity);
            ArrayAdapter<CharSequence> activity_adapter = ArrayAdapter.createFromResource(this,
                    R.array.activity_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
            activity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
            spinner_activity.setAdapter(activity_adapter);

            button_next = findViewById(R.id.button_next);

            user = new User(0,0,0,0,0,0,new String[]{"a","b"});
            button_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!CheckEditTexts())
                        Toast.makeText(getApplicationContext(), "Ошибки в заполняемых полях", Toast.LENGTH_LONG).show();
                    else {
                        user.setTarget(spinner_target.getSelectedItemPosition());
                        user.setMale(spinner_male.getSelectedItemPosition());
                        user.setAge(Integer.parseInt(String.valueOf(text_age.getText())));
                        user.setHeight(Float.parseFloat(String.valueOf(text_height.getText())));
                        user.setWeight(Float.parseFloat(String.valueOf(text_weight.getText())));
                        user.setActivity(spinner_activity.getSelectedItemPosition());
                        boolean result = JSONHelper.exportToJSON(getApplicationContext(), user); // сохраняем введённые данные в JSON-файл
                        if(result){
                            Toast.makeText(getApplicationContext(), "Данные сохранены", Toast.LENGTH_LONG).show();
                            StartApp();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
            button_info_activity = findViewById(R.id.info_activity_Button);
            button_info_activity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog();
                }
            });
        }
        else StartApp();
        }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);
        very_low_text = dialog.findViewById(R.id.text_very_low);
        very_low_text.setText(Html.fromHtml("Выбирайте <b>минимальную нагрузку</b>, если работаете" +
                " в офисе, не тренируетесь, а вечера и выходные предпочитаете проводить за компьютером, а не на прогулках."));
        low_text = dialog.findViewById(R.id.text_low);
        low_text.setText(Html.fromHtml("Параметр <b>«низкая нагрузка»</b> подойдёт для тех, кто много трудится по дому, гуляет" +
                " с собакой, иногда выбирает прогулку вместо транспорта и изредка занимается спортом."));
        middle_text = dialog.findViewById(R.id.text_middle);
        middle_text.setText(Html.fromHtml("<b>Средняя нагрузка</b> подразумевает, что вы тренируетесь от 3 до 5 раз в неделю, при этом" +
                " в свободное время не только лежите на диване, но и ходите пешком, выполняете бытовые задачи."));
        high_text = dialog.findViewById(R.id.text_high);
        high_text.setText(Html.fromHtml("<b>Высокая нагрузка</b> предполагает, что вы занимаетесь спортом 6–7 раз в неделю — или 3–5, но при этом ваша работа — физический труд."));
        very_high_text = dialog.findViewById(R.id.text_very_high);
        very_high_text.setText(Html.fromHtml("<b>Очень высокая нагрузка</b> характерна для тех, кто тренируется по 2 раза в день или много занимается спортом и работает физически, " +
                "но при этом и о других видах активности не забывает."));
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private String validAge(){ // Валидация возраста
        int ageText = Integer.parseInt(text_age.getText().toString());
        if(ageText < 7 || ageText > 90) return "От 7 до 90 лет";
        return null;
    }
    private String validHeight() { // Валидация роста
        float heightText = Float.parseFloat(text_height.getText().toString());
        if(heightText < 110 || heightText > 230) return "От 110 до 230 см";
        return null;
    }
    private String validWeight() { // Валидация веса
        float weightText = Float.parseFloat(text_weight.getText().toString());
        if(weightText < 20 || weightText > 250) return "От 20 до 250 кг";
        return null;
    }

    protected void StartApp(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    protected boolean CheckEditTexts(){
        if(text_age.getText().length() == 0 || container_age.getHelperText() != null) return false;
        if(text_height.getText().length() == 0 || container_height.getHelperText() != null) return false;
        if(text_weight.getText().length() == 0 || container_weight.getHelperText() != null) return false;
        return true;
    }


}