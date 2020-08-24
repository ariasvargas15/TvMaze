package com.bsav157.tvmaze.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bsav157.tvmaze.utils.Defines;
import com.bsav157.tvmaze.utils.Util;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent in = new Intent();
        if(!Util.getValuePreference(this, Defines.TOKEN).isEmpty()){
            in.setClass(this, LoginActivity.class);
        } else {
            in.setClass(this, MainActivity.class);
        }
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }
}

