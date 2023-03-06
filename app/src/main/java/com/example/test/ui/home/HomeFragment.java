package com.example.test.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.databinding.FragmentHomeBinding;
import com.example.test.ui.JSONHelper;
import com.example.test.ui.User;
import com.example.test.ui.dashboard.DashboardFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ListView listView;
    private TextView textView_stats;
    private TextView textView_stage;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        user = JSONHelper.importFromJSON(getContext());
        if (!user.isHasbudget()){ // если о бюджете ещё нет данных
            //Получаем вид с файла prompt.xml, который применим для диалогового окна:
            LayoutInflater li = LayoutInflater.from(getContext());
            View promptsView = li.inflate(R.layout.prompt, null);

            //Создаем AlertDialog
            AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getContext());

            //Настраиваем prompt.xml для нашего AlertDialog:
            mDialogBuilder.setView(promptsView);

            //Настраиваем отображение поля для ввода текста в открытом диалоге:
            final EditText userInput = promptsView.findViewById(R.id.text_budget);

            //Настраиваем сообщение в диалоговом окне:
            mDialogBuilder
                    .setPositiveButton("OK",null);
            //Создаем AlertDialog:
            AlertDialog alertDialog = mDialogBuilder.create();
            //и отображаем его:
            alertDialog.show();
            // ставим обработчик на кнопку
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Вводим текст и сохраняем в переменную
                    if(userInput.getText().length() == 0) {
                        Toast.makeText(getContext(),"Пожалуйста, введите бюджет",Toast.LENGTH_LONG).show();
                    }
                    else if(Float.parseFloat(String.valueOf(userInput.getText())) > 100) { // Минимальный бюджет надо выставить
                        user.setbudget(Float.parseFloat(String.valueOf(userInput.getText())));
                        JSONHelper.exportToJSON(getContext(), user);
                        alertDialog.dismiss();
                        // Здесь нужно запустить алгоритм
                    }
                    else{
                        Toast.makeText(getContext(),"Пожалуйста, введите бюджет > 100 рублей",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listView = root.findViewById(R.id.listview_products);

        textView_stats = root.findViewById(R.id.text_stats);
        textView_stage = root.findViewById(R.id.text_stage);
        textView_stage.setText("1 из 4");
        textView_stats.setText("Жиры: 20\nБелки: 15\nУглеводы: 100");
        List<String> list = new ArrayList<>();
        for(int i=0;i<3;i++)
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