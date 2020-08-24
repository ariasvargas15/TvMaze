package com.bsav157.tvmaze.model.interactors;

import android.util.Log;

import com.bsav157.tvmaze.model.apiservice.ApiAdapter;
import com.bsav157.tvmaze.model.entitites.Episode;
import com.bsav157.tvmaze.presenter.interfaces.IListEpisodes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEpisodesInteractor implements Callback<List<Episode>> {

    private IListEpisodes.Presenter presenter;

    public ListEpisodesInteractor(IListEpisodes.Presenter view){
        presenter = view;
    }

    public void makeQuery(int idShow){
        Call<List<Episode>> call = ApiAdapter.getApiService().getEpisodes(idShow);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Episode>> call, Response<List<Episode>> response) {
        if (response.isSuccessful()) {
            List<Episode> list = response.body();
            if(list != null) {
                presenter.showList((ArrayList<Episode>) list);
            } else {
                Log.e("onResponseShowList", "Response is null");
            }

        }
    }

    @Override
    public void onFailure(Call<List<Episode>> call, Throwable t) {
        Log.e("onFailureShowList", "Response failed");
    }
}
