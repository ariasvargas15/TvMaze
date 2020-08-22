package com.bsav157.tvmaze.model.interactors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bsav157.tvmaze.model.apiservice.ApiAdapter;
import com.bsav157.tvmaze.model.entitites.Episode;
import com.bsav157.tvmaze.model.entitites.Show;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Interactor implements Callback<List<Show>> {
    public Interactor(){
        Call<List<Show>> call = ApiAdapter.getApiService().getShows(0);
        call.enqueue(this);
        /*Call<List<Show>> call2 = ApiAdapter.getApiService().getQuery("girl");
        call2.enqueue(this);
        Call<List<Episode>> call3 = ApiAdapter.getApiService().getEpisodes(23);
        call.enqueue(this);*/
    }

    @Override
    public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
        if (response.isSuccessful()) {
                List<Show> list = response.body();
                if(list != null) {
                    Log.e("list", list.toString());
                    Log.e("list", list.get(45).toString());
                } else {
                    Log.e("listnull", "esnulo");
                }
        }
    }

    @Override
    public void onFailure(Call<List<Show>> call, Throwable t) {
        Log.e("fail", "failed");
    }
}
