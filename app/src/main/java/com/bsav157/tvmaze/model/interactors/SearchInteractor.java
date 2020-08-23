package com.bsav157.tvmaze.model.interactors;

import android.util.Log;

import com.bsav157.tvmaze.model.apiservice.ApiAdapter;
import com.bsav157.tvmaze.model.entitites.Search;
import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.presenter.interfaces.ISearchShow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchInteractor implements Callback<List<Search>> {

    private ISearchShow.Presenter presenter;

    public SearchInteractor(ISearchShow.Presenter view){
        presenter = view;
    }

    public void makeQuery(String query){
        Call<List<Search>> call = ApiAdapter.getApiService().getQuery(query);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Search>> call, Response<List<Search>> response) {
        if (response.isSuccessful()) {
            List<Search> list = response.body();
            ArrayList<Show> shows = new ArrayList<>();
            if(list != null) {
                for (Search s: list) {
                    shows.add(s.getShow());
                }
                presenter.showSearch(shows);
            } else {
                Log.e("onResponseSearch", "Response is null");
            }

        }
    }

    @Override
    public void onFailure(Call<List<Search>> call, Throwable t) {
        Log.e("onFailureSearch", "Response failed");
    }
}
