package com.bsav157.tvmaze.view.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.presenter.ListShowsPresenter;
import com.bsav157.tvmaze.presenter.interfaces.IListShows;
import com.bsav157.tvmaze.utils.InputMinMaxFilter;
import com.bsav157.tvmaze.utils.RecyclerViewOnItemClickListener;
import com.bsav157.tvmaze.view.adapters.ShowAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class ListShowsFragment extends Fragment implements IListShows.View, View.OnClickListener {

    private static final int MIN = 1;
    private static final int MAX = 200;

    @BindView(R.id.number_picker) EditText et;
    @BindView(R.id.list_shows) RecyclerView recycler;
    @BindView(R.id.decrement_number) ImageView dec;
    @BindView(R.id.increment_number) ImageView inc;
    private AlertDialog dialog;
    private ListShowsPresenter presenter;

    public ListShowsFragment() {
        // Required empty public constructor
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_list_shows, container, false);
        ButterKnife.bind(this, view);

        et.setFilters(new InputFilter[]{ new InputMinMaxFilter(MIN, MAX)});
        et.setEnabled(false);

        dec.setOnClickListener(this);
        inc.setOnClickListener(this);

        makeTextWatcherListener();

        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();

        presenter = new ListShowsPresenter(this);
        makeQuery(0);
        return view;
    }

    @Override
    public void showList(final ArrayList<Show> shows) {
        GridLayoutManager grid = new GridLayoutManager(getContext(), 2);
        recycler.setLayoutManager(grid);
        ShowAdapter adapter = new ShowAdapter(shows, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Fragment fragment = InfoShowFragment.newInstance(shows.get(position));
                assert getFragmentManager() != null;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        recycler.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void onClick(android.view.View view) {
        switch (view.getId()){
            case R.id.decrement_number:
                int val = Integer.parseInt(et.getText().toString());
                if(val == MIN){
                    et.setText(String.valueOf(MAX));
                } else {
                    et.setText(String.valueOf(val-1));
                }
                break;
            case R.id.increment_number:
                int valx = Integer.parseInt(et.getText().toString());
                if(valx == MAX){
                    et.setText(String.valueOf(MIN));
                } else {
                    et.setText(String.valueOf(valx+1));
                }
                break;
            default:
                break;
        }
    }

    private void makeQuery(int page){
        dialog.show();
        presenter.makeQuery(page);
    }

    private void makeTextWatcherListener(){
        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                makeQuery(Integer.parseInt(s.toString()) - 1);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }
}