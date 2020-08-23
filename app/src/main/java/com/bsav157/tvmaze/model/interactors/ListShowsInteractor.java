package com.bsav157.tvmaze.model.interactors;

import android.util.Log;

import com.bsav157.tvmaze.model.apiservice.ApiAdapter;
import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.presenter.interfaces.IListShows;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListShowsInteractor implements Callback<List<Show>> {

    private IListShows.Presenter presenter;

    public ListShowsInteractor(IListShows.Presenter view){
        presenter = view;
    }

    public void makeQuery(int page){
        Call<List<Show>> call = ApiAdapter.getApiService().getShows(page);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
        if (response.isSuccessful()) {
                List<Show> list = response.body();
                if(list != null) {
                    presenter.showList((ArrayList<Show>) list);
                } else {
                    Log.e("onResponseShowList", "Response is null");
                }

        }
    }

    @Override
    public void onFailure(Call<List<Show>> call, Throwable t) {
        Log.e("onFailureShowList", "Response failed");
    }

}
