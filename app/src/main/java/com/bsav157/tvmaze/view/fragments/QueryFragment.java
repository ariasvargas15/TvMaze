package com.bsav157.tvmaze.view.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.presenter.SearchPresenter;
import com.bsav157.tvmaze.presenter.interfaces.ISearchShow;
import com.bsav157.tvmaze.utils.RecyclerViewOnItemClickListener;
import com.bsav157.tvmaze.view.adapters.ShowAdapter;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;


public class QueryFragment extends Fragment implements ISearchShow.View, TextView.OnEditorActionListener{

    private SearchPresenter presenter;
    private EditText searchEditText;
    private RecyclerView recycler;
    private AlertDialog dialog;

    public QueryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        searchEditText = view.findViewById(R.id.username);
        searchEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        searchEditText.setOnEditorActionListener(this);
        recycler = view.findViewById(R.id.list_search);

        presenter = new SearchPresenter(this);

        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();

        return view;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        boolean action = false;
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            InputMethodManager inputMethodManager = (InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(textView.getWindowToken(), 0);

            dialog.show();
            presenter.makeQuery(searchEditText.getText().toString());
            action = true;
        }
        return action;
    }

    @Override
    public void showSearch(final ArrayList<Show> shows) {
        GridLayoutManager grid = new GridLayoutManager(getContext(), 2);
        recycler.setLayoutManager(grid);
        ShowAdapter adapter = new ShowAdapter(shows, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                searchEditText.setText("");
                getFragmentManager().beginTransaction().replace(R.id.content, InfoShowFragment.newInstance(shows.get(position))).addToBackStack(null).commit();
            }
        });
        recycler.setAdapter(adapter);
        dialog.dismiss();
    }
}