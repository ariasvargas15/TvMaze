package com.bsav157.tvmaze.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.view.fragments.InfoShowFragment;
import com.bsav157.tvmaze.view.fragments.ListShowsFragment;
import com.bsav157.tvmaze.view.fragments.QueryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    BottomNavigationView nav;

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
            default:
                return false;
        }
    }
}