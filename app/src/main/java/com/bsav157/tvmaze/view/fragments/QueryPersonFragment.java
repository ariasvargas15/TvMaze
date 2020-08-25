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
import com.bsav157.tvmaze.model.entitites.Person;
import com.bsav157.tvmaze.presenter.SearchPersonPresenter;
import com.bsav157.tvmaze.presenter.interfaces.ISearchPerson;
import com.bsav157.tvmaze.utils.RecyclerViewOnItemClickListener;
import com.bsav157.tvmaze.view.adapters.PersonAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;


public class QueryPersonFragment extends Fragment implements ISearchPerson.View, TextView.OnEditorActionListener {

    private SearchPersonPresenter presenter;
    @BindView(R.id.search) EditText searchEditText;
    @BindView(R.id.list_search) RecyclerView recycler;
    @BindView(R.id.message_search) TextView message;
    private AlertDialog dialog;

    public QueryPersonFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_query, container, false);
        ButterKnife.bind(this, view);

        searchEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        searchEditText.setOnEditorActionListener(this);
        searchEditText.setHint(getResources().getString(R.string.search_bar_people));

        presenter = new SearchPersonPresenter(this);

        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();

        return view;
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        boolean action = false;
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            message.setVisibility(View.GONE);
            InputMethodManager inputMethodManager = (InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(textView.getWindowToken(), 0);
            dialog.show();
            presenter.makeQuery(searchEditText.getText().toString());
            action = true;
        }
        return action;
    }

    @Override
    public void showSearch(final ArrayList<Person> people) {
        if(people.isEmpty()){
            message.setVisibility(View.VISIBLE);
        } else {
            GridLayoutManager grid = new GridLayoutManager(getContext(), 2);
            recycler.setLayoutManager(grid);
            PersonAdapter adapter = new PersonAdapter(people, new RecyclerViewOnItemClickListener() {
                @Override
                public void onClick(View v, int position) {
                    searchEditText.setText("");
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.content, InfoPeopleFragment.newInstance(people.get(position))).addToBackStack(null).commit();
                }
            });
            recycler.setAdapter(adapter);
        }
        dialog.dismiss();
    }
}