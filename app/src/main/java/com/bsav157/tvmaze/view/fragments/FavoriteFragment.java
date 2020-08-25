package com.bsav157.tvmaze.view.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.presenter.ListEpisodesPresenter;
import com.bsav157.tvmaze.presenter.ListFavoritesPresenter;
import com.bsav157.tvmaze.presenter.interfaces.IListFavorites;
import com.bsav157.tvmaze.utils.RecyclerViewOnItemClickListener;
import com.bsav157.tvmaze.view.adapters.ShowAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;


public class FavoriteFragment extends Fragment implements IListFavorites.View {


    @BindView(R.id.list_favorites) RecyclerView recycler;
    private AlertDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        SpotsDialog.Builder sp = new SpotsDialog.Builder();
        sp.setContext(getContext()).setCancelable(false).setMessage("Loading...");
        dialog = sp.build();
        dialog.show();
        ListFavoritesPresenter presenter = new ListFavoritesPresenter(this, getContext());
        presenter.searchFavorites();
        return view;
    }

    @Override
    public void showFavorites(final ArrayList<Show> shows) {
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
}