package com.bsav157.tvmaze.model.interactors;

import android.util.Log;

import com.bsav157.tvmaze.model.apiservice.ApiAdapter;
import com.bsav157.tvmaze.model.entitites.CastCredit;
import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.presenter.interfaces.IListCasts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCastsInteractor implements Callback<List<CastCredit>> {

    private IListCasts.Presenter presenter;

    public ListCastsInteractor(IListCasts.Presenter view){
        presenter = view;
    }

    public void makeQuery(int idPerson){
        Call<List<CastCredit>> call = ApiAdapter.getApiService().getCastCredits(idPerson);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<CastCredit>> call, Response<List<CastCredit>> response) {
        if (response.isSuccessful()) {
            List<CastCredit> list = response.body();
            ArrayList<Show> shows = new ArrayList<>();
            if(list != null) {
                for (CastCredit s: list) {
                    shows.add(s.getEmbedded().getShow());
                }
                presenter.showList(shows);
            } else {
                Log.e("onResponseSearch", "Response is null");
            }

        }
    }

    @Override
    public void onFailure(Call<List<CastCredit>> call, Throwable t) {
        Log.e("onFailureShowList", "Response failed");
    }
}
