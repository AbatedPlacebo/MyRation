package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.test.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean quiz = true;
    private int num_of_question = 1;
    private RadioButton first_answerButton;
    private RadioButton second_answerButton;
    private RadioButton third_answerButton;
    //private RadioButton fourth_answerButton;
    //private RadioButton fiveth_answerButton;
    //private RadioButton sixth_answerButton;
    private Button nextButton;
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private EditText inputText;
    private TextView hintText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

            first_answerButton = findViewById(R.id.first_button);
            first_answerButton.setOnClickListener(radioButtonClickListener);
            second_answerButton = findViewById(R.id.second_button);
            second_answerButton.setOnClickListener(radioButtonClickListener);
            third_answerButton = findViewById(R.id.third_button);
            third_answerButton.setOnClickListener(radioButtonClickListener);
            nextButton = findViewById(R.id.next_button);
            questionTextView = findViewById(R.id.answer_text_view);
            radioGroup = findViewById(R.id.radioGroup);
            inputText = findViewById(R.id.input_text);
            hintText = findViewById(R.id.hint_text);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(radioGroup.getCheckedRadioButtonId() == -1)
                        Toast.makeText(getApplicationContext(), "ошибка", Toast.LENGTH_SHORT).show();
                    else {
                        switch (num_of_question){
                            case 1: // меняем на вопрос о поле
                            {
                                questionTextView.setText(R.string.b);
                                radioGroup.clearCheck();
                                first_answerButton.setText("Мужской");
                                second_answerButton.setText("Женский");
                                third_answerButton.setVisibility(View.INVISIBLE);
                                break;
                            }
                            case 2: // меняем на вопрос о возрасте
                            {
                                questionTextView.setText(R.string.с);
                                radioGroup.setVisibility(View.INVISIBLE);
                                inputText.setVisibility(View.VISIBLE);
                                break;
                            }
                            case 3: // меняем на вопрос о росте
                            {
                                if(inputText.getText().length() == 0){ // нужна более лучшая валидация
                                    Toast.makeText(getApplicationContext(), "ошибка", Toast.LENGTH_SHORT).show();
                                    num_of_question--;
                                }
                                else{
                                    questionTextView.setText(R.string.d);
                                    inputText.setText("");
                                    hintText.setText(R.string.hint_height);
                                }
                                break;
                            }
                            case 4: // меняем на вопрос о весе
                            {
                                if(inputText.getText().length() == 0){ // нужна более лучшая валидация
                                    Toast.makeText(getApplicationContext(), "ошибка", Toast.LENGTH_SHORT).show();
                                    num_of_question--;
                                }
                                else{
                                    questionTextView.setText(R.string.e);
                                    inputText.setText("");
                                    hintText.setText(R.string.hint_weight);
                                }
                                break;
                            }
                            case 5: // меняем на вопросы об аллергии, тут надо подумать как реализовать, поэтому пока что просто запускаем основной экран
                            {
                                if(inputText.getText().length() == 0){ // нужна более лучшая валидация
                                    Toast.makeText(getApplicationContext(), "ошибка", Toast.LENGTH_SHORT).show();
                                    num_of_question--;
                                }
                                else {
                                    /*questionTextView.setText(R.string.f);
                                    radioGroup.setOrientation(LinearLayout.HORIZONTAL);*/
                                    StartApp();
                                }

                                break;
                            }
                            case 6: // меняем на основной экран
                            {
                                StartApp();
                            }
                        }
                       num_of_question++;
                    }
                }
            });

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
    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.first_button: Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show(); // надо будет заменить на запоминание результатов
                    break;
                case R.id.second_button: Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.third_button: Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        }
    };

}