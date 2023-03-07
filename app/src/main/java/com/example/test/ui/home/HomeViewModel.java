package com.example.test.ui.home;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.ui.JSONHelper;
import com.example.test.ui.User;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<Integer> rationNumber =
            new MutableLiveData(1);
    private final MutableLiveData<Integer> rationPrice =
            new MutableLiveData(0);
    private final MutableLiveData<Integer> proteinsTotal =
            new MutableLiveData(0);
    private final MutableLiveData<Integer> fatsTotal =
            new MutableLiveData(0);
    private final MutableLiveData<Integer> carbsTotal =
            new MutableLiveData(0);

    public HomeViewModel() {

    }
    public MutableLiveData<Integer> getRationNumber() {
        return rationNumber;
    }
    public MutableLiveData<Integer> getRationPrice() {
        return rationPrice;
    }
    public MutableLiveData<Integer> getProteinsTotal() {
        return proteinsTotal;
    }
    public MutableLiveData<Integer> getFatsTotal() {
        return fatsTotal;
    }
    public MutableLiveData<Integer> getCarbsTotal() {
        return carbsTotal;
    }
    public List<String> generateProducts(){
        List<String> list = new ArrayList<String>();
        return list;
    }
}