package com.example.test.ui.home;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.ui.JSONHelper;
import com.example.test.ui.User;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private User user;
    private final MutableLiveData<Integer> rationNumber =
            new MutableLiveData(1);
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }
    public MutableLiveData<Integer> getRationNumber() {
        return rationNumber;
    }
}