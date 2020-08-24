package com.bsav157.tvmaze.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bsav157.tvmaze.R;

import static android.content.Context.MODE_PRIVATE;

public abstract class Util {

    public static String getValuePreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public static void saveValuePreference(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void saveValuePreferenceInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getValuePreferenceInt(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.pref_key), MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

}
