package com.example.test.ui;

import android.content.Context;

import com.example.test.ui.home.HomeViewModel;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class JSONHelper {
    private static final String FILE_NAME = "data.json";

    public static boolean exportToJSON(Context context, User user) {

        Gson gson = new Gson();
        String jsonString = gson.toJson(user);

        try(FileOutputStream fileOutputStream =
                    context.openFileOutput(FILE_NAME,Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User importFromJSON(Context context) {

        try(FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            User user = gson.fromJson(streamReader, User.class);
            return user;
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
