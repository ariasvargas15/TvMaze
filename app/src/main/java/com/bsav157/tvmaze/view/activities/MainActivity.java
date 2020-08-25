package com.bsav157.tvmaze.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.view.fragments.ListShowsFragment;
import com.bsav157.tvmaze.view.fragments.QueryFragment;
import com.bsav157.tvmaze.view.fragments.QueryPersonFragment;
import com.bsav157.tvmaze.view.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    private BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflateFragment(new ListShowsFragment());
        nav = findViewById(R.id.bottom_navigation);
        nav.setOnNavigationItemSelectedListener(this);

    }


    private void inflateFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_1:
                inflateFragment(new ListShowsFragment());
                return true;
            case R.id.page_2:
                inflateFragment(new QueryFragment());
                return true;
            case R.id.page_3:
                inflateFragment(new QueryPersonFragment());
                return true;
            case R.id.page_4:
                inflateFragment(new SettingsFragment());
                return true;
            default:
                return false;
        }
    }

}