package com.bsav157.tvmaze.model.database;

import androidx.room.Room;
import android.annotation.SuppressLint;
import android.content.Context;

import com.bsav157.tvmaze.model.entitites.Favorite;

import java.util.List;

public class FavoriteDto {

    @SuppressLint("StaticFieldLeak")
    private static FavoriteDto fDto;
    private FavoriteDao fDao;

    private FavoriteDto(Context context) {
        Context appContext = context.getApplicationContext();
        FavoriteDatabase database = Room.databaseBuilder(appContext, FavoriteDatabase.class, "favorite")
                .allowMainThreadQueries().build();
        fDao = database.getFavoriteDao();
    }

    public static FavoriteDto getInstance(Context context) {
        if (fDto == null) {
            fDto = new FavoriteDto(context);
        }
        return fDto;
    }

    public List<Favorite> getFavorites() {
        return fDao.getFavorites();
    }

    public Favorite getFavorite(String id) {
        return fDao.getFavorite(id);
    }

    public void addFavorite(Favorite favorite) {
        fDao.addFavorite(favorite);
    }

    public void updateFavorite(Favorite favorite) {
        fDao.updateFavorite(favorite);
    }

    public void deleteFavorite(Favorite favorite) {
        fDao.deleteFavorite(favorite);
    }
}
