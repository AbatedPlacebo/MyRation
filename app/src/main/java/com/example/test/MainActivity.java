package com.example.test;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.test.db.DatabaseLoad;

import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.test.ui.JSONHelper;
import com.example.test.ui.User;


import com.example.test.db.DatabaseLoad;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import androidx.room.Room;

import com.example.test.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Button button_privacy_next;
    private CheckBox checkBox_privacy;
    private TextView textView_privacy;
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
        if(user == null) {
            setContentView(R.layout.privacy_police);
            button_privacy_next = findViewById(R.id.button_privacy_next);
            textView_privacy = findViewById(R.id.text_privacy_police);
            button_privacy_next.setEnabled(false);
            checkBox_privacy = findViewById(R.id.checkbox_privacy);
            checkBox_privacy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox_privacy.isChecked()) button_privacy_next.setEnabled(true);
                    else button_privacy_next.setEnabled(false);
                }
            });
            textView_privacy.setMovementMethod(new ScrollingMovementMethod());
            textView_privacy.setText(Html.fromHtml("<p style=\"text-align: center;\">Пользовательское соглашение в отношении мобильного приложения &laquo;Мой рацион&raquo;</p>\n" +
                    "<p style=\"text-align: left;\">1.ОБЩИЕ ПОЛОЖЕНИЯ</p>\n" +
                    "<p>1.1. Настоящее Пользовательское соглашение (далее - &laquo;Соглашение&raquo;) регламентирует отношения между разработчиками ПО &laquo;Мой рацион&raquo; (далее - &laquo;Разработчики&raquo;) и дееспособным физическим лицом, надлежащим образом присоединившимся к настоящему Соглашению для использования мобильного приложения &laquo;Мой рацион&raquo; (далее &ndash; &laquo;Пользователь&raquo;).</p>\n" +
                    "<p>1.2. Мобильное приложение &laquo;Мой рацион&raquo; (далее &ndash; &laquo;Приложение&raquo;) является программой для ЭВМ, представляющей собой рекомендательное информационное приложение, разработанное для мобильных устройств. Разработчики не являются экспертами в диетологии, а Приложение носит сугубо рекомендательный характер и основываются на общедоступных сведениях о питании.</p>\n" +
                    "<p>1.3. Настоящее Соглашение является открытым и общедоступным документом. Действующая редакция Соглашения располагается в сети Интернет по адресу: https://docs.google.com/document/d/1QVM3Ij5PX6uyzAxUJAYfOK6L08L3UZCn</p>\n" +
                    "<p>1.4. Заполнение формы в Приложении на мобильном устройстве Пользователя является акцептом настоящего Соглашения и подтверждением согласия Пользователя с его условиями.</p>\n" +
                    "<p>1.5. Принимая условия настоящего Соглашения, Пользователь подтверждает свое согласие на обработку Разработчиками его данных, предоставленных при заполнении формы в Приложении, в целях исполнения настоящего Соглашения и разрешения претензий, связанных с исполнением настоящего Соглашения.</p>\n" +
                    "<p>1.6. Условия настоящего Соглашения являются публичной офертой в соответствии с частью 2 статьи 437 Гражданского кодекса Российской Федерации заключить с Разработчиками договор присоединения в соответствии со статьей 428 Гражданского кодекса Российской Федерации, согласно условиям которого Разработчики предоставляет Пользователю безвозмездный доступ к Приложению на условиях настоящего Соглашения.</p>\n" +
                    "<p>1.7. Настоящее Соглашение может быть изменено и/или дополнено Разработчиками в одностороннем порядке. При этом продолжение использования Приложения после внесения изменений и/или дополнений в настоящее Соглашение, означает согласие Пользователя с такими изменениями и/или дополнениями, в связи с чем Пользователь обязуется регулярно отслеживать изменения в соответствующем разделе в Приложении и в Соглашении, размещенном на сайте https://docs.google.com/document/d/1QVM3Ij5PX6uyzAxUJAYfOK6L08L3UZCn.</p>\n" +
                    "<p>1.8. Обращения, предложения и претензии физических и юридических лиц к Разработчикам, связанные с содержанием и функционированием Приложения, нарушениями прав и интересов третьих лиц, требований законодательства Российской Федерации, а также для запросов уполномоченных законодательством Российской Федерации лиц могут быть направлены на адрес электронной почты example@example.ru.</p>\n" +
                    "<p>1.9. Настоящее Соглашение составлено в соответствии с законодательством Российской Федерации. Вопросы, не урегулированные Соглашением, подлежат разрешению в соответствии с законодательством Российской Федерации.</p>\n" +
                    "<p>1.10. Соглашаясь с условиями настоящего Соглашения, Пользователь подтверждает свою правоспособность и свою дееспособность.</p>\n" +
                    "<p>2.ПРАВА И ОБЯЗАННОСТИ ПОЛЬЗОВАТЕЛЯ</p>\n" +
                    "<p>2.1. Пользователь обязуется надлежащим образом соблюдать условия настоящего Соглашения.</p>\n" +
                    "<p>2.2. Пользователь обязуется принимать надлежащие меры для обеспечения сохранности своих данных, а также несет личную ответственность за их сохранность и конфиденциальность.</p>\n" +
                    "<p>2.3. Пользователь обязуется не использовать Приложение для любых иных целей, кроме как для целей, связанных с личным некоммерческим использованием.</p>\n" +
                    "<p>&nbsp;3.ПРАВА И ОБЯЗАННОСТИ РАЗРАБОТЧИКОВ</p>\n" +
                    "<p>3.1. Разработчики вправе передавать права и обязанности по настоящему Соглашению, третьим лицам в целях исполнения настоящего Соглашения без дополнительного согласия Пользователя.</p>\n" +
                    "<p>3.2. Разработчики вправе направлять Пользователю любым способом информацию о функционировании Приложения, в том числе размещать рекламные, информационные и иные сообщения внутри Приложения.</p>\n" +
                    "<p>4.ГАРАНТИИ И ОТВЕТСТВЕННОСТЬ СТОРОН</p>\n" +
                    "<p>4.1. Пользователь гарантирует, что не будет предпринимать каких-либо действий, направленных на причинение ущерба обладателю прав на Приложение, Разработчиков, правообладателям и иным лицам.</p>\n" +
                    "<p>4.2. В случае нарушения правил использования Приложения, указанных в разделе 2 настоящего Соглашения, а также в случае нарушения пункта 4.1 настоящего Соглашения, Пользователь обязуется возместить Разработчикам вред, причиненный такими действиями.</p>\n" +
                    "<p>4.3. Если Пользователем не доказано обратное, любые действия считаются совершенными соответствующим Пользователем. В случае несанкционированного доступа к данным Пользователя, или распространения этих данных Пользователь обязан незамедлительно сообщить об этом Разработчикам в установленном порядке.</p>\n" +
                    "<p>5.ССЫЛКИ НА САЙТЫ ТРЕТЬИХ ЛИЦ</p>\n" +
                    "<p>5.1. Приложение может содержать ссылки или предоставлять доступ на другие ресурсы в сети Интернет (сайты третьих лиц) и размещенный на данных ресурсах Контент, являющиеся результатом интеллектуальной деятельности третьих лиц и охраняемые в соответствии с законодательством Российской Федерации. Указанные сайты и размещенный на них Контент не проверяются Компанией на соответствие требованиям законодательства Российской Федерации.</p>\n" +
                    "<p>5.2. Разработчики не несут ответственность за любую информацию или Контент, размещенные на сайтах третьих лиц, к которым Пользователь получает доступ посредством Приложения, включая, в том числе, любые мнения или утверждения, выраженные на сайтах третьих лиц.</p>\n" +
                    "<p>5.3. Пользователь подтверждает, что с момента перехода Пользователя по ссылке, содержащейся в Приложении, на сайт третьего лица, взаимоотношения Разработчиков и Пользователя прекращаются, настоящее Соглашение в дальнейшем не распространяется на Пользователя, и Разработчики не несут ответственность за достоверность размещенной на сайтах третьих лиц информации, использование Пользователем Контента, правомерность такого использования и качество Контента, размещенного на сайтах третьих лиц.</p>\n" +
                    "<p>6.ЗАКЛЮЧИТЕЛЬНЫЕ ПОЛОЖЕНИЯ</p>\n" +
                    "<p>6.1. Вопросы, не урегулированные настоящим Соглашением, подлежат разрешению в соответствии с законодательством Российской Федерации.</p>\n" +
                    "<p>6.2. В случае возникновения любых споров или разногласий, связанных с исполнением настоящего Соглашения, Пользователь и Разработчики приложат все усилия для их разрешения путем проведения переговоров между ними. В случае, если споры не будут разрешены путем переговоров, споры подлежат разрешению в суде общей юрисдикции по месту нахождения Разработчиков в порядке, установленном действующим законодательством Российской Федерации.</p>\n" +
                    "<p>6.3.Настоящее Соглашение вступает в силу для Пользователя с момента подтверждения соглашения в Приложении на мобильном устройстве Пользователя. Настоящее Соглашение действует бессрочно.</p>\n" +
                    "<p>6.4.Настоящее Соглашение составлено на русском языке.</p>\n" +
                    "<p>6.5.Если какое-либо из положений настоящего Соглашения будет признано недействительным, это не оказывает влияния на действительность или применимость остальных положений настоящего Соглашения.</p>"));
            button_privacy_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StartQuiz();
                }
            });
        }
            else StartApp();
        }
        protected void StartQuiz(){
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
                        container_age.setHelperText(validAge());
                    }
                }
            });

            text_height = findViewById(R.id.text_height);
            container_height = findViewById(R.id.heightContainer);
            text_height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b){
                        container_height.setHelperText(validHeight());
                    }
                }
            });

            text_weight = findViewById(R.id.text_weight);
            container_weight = findViewById(R.id.weightContainer);
            text_weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b){
                        container_weight.setHelperText(validWeight());
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

            user = new User(0,0,0,0,0,0,0);

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
                        user.setPFCC();
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
    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);
        LinearLayout firstLayout = dialog.findViewById(R.id.layout_very_low);
        LinearLayout secLayout = dialog.findViewById(R.id.layout_low);
        LinearLayout thirdLayout = dialog.findViewById(R.id.layout_middle);
        LinearLayout fourLayout = dialog.findViewById(R.id.layout_high);
        LinearLayout fiveLayout = dialog.findViewById(R.id.layout_very_high);
        View.OnClickListener Layout_click_listener  = new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Закрытие диалога при нажатии куда-либо
                dialog.dismiss();
            }
        };
        firstLayout.setOnClickListener(Layout_click_listener);
        secLayout.setOnClickListener(Layout_click_listener);
        thirdLayout.setOnClickListener(Layout_click_listener);
        fourLayout.setOnClickListener(Layout_click_listener);
        fiveLayout.setOnClickListener(Layout_click_listener);

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
        if(text_age.getText().length() == 0) return "Необходимо заполнить";
        int ageText = Integer.parseInt(text_age.getText().toString());
        if(ageText < 7 || ageText > 90) return "От 7 до 90 лет";
        return null;
    }
    private String validHeight() { // Валидация роста
        if(text_height.getText().length() == 0) return "Необходимо заполнить";
        float heightText = Float.parseFloat(text_height.getText().toString());
        if(heightText < 110 || heightText > 230) return "От 110 до 230 см";
        return null;
    }
    private String validWeight() { // Валидация веса
        if(text_weight.getText().length() == 0) return "Необходимо заполнить";
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //user.set() текущие белки, жиры, углеводы, калории
        JSONHelper.exportToJSON(getApplicationContext(), user);
    }

    protected boolean CheckEditTexts(){
        container_age.setHelperText(validAge());
        if(container_age.getHelperText() != null) return false;
        container_height.setHelperText(validHeight());
        if(container_height.getHelperText() != null) return false;
        container_weight.setHelperText(validWeight());
        if(container_weight.getHelperText() != null) return false;
        return true;
    }

}