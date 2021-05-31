package com.example.merge.login;

import android.content.Context;
import android.content.SharedPreferences;

public class AutoLogin {

    private static SharedPreferences preferences;

    public static void initialize(Context context) {
        preferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
    }

    public static void setPreferences(String email, String password) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    public static String getEmailFromPreference() {
        return preferences.getString("email", "");
    }

    public static String getPasswordFromPreference(){
        return preferences.getString("password", "");
    }
}
