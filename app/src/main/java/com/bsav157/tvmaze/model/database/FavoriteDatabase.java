package com.bsav157.tvmaze.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bsav157.tvmaze.model.entitites.Favorite;

@Database(entities = {Favorite.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {
    public abstract FavoriteDao getFavoriteDao();
}
