package com.bsav157.tvmaze.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.model.interactors.Interactor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Interactor in = new Interactor();
    }
}