package com.bsav157.tvmaze.model.apiservice;


import com.bsav157.tvmaze.model.entitites.Episode;
import com.bsav157.tvmaze.model.entitites.Show;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("shows")
    Call<List<Show>> getShows( @Query("page") int page);
    Call<List<Show>> getQuery( @Query("q") String query);

    @GET("shows/{id}/episodes")
    Call<List<Episode>> getEpisodes(@Path("id") int id);

}
