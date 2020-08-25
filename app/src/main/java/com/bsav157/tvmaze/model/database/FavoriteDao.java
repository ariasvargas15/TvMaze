package com.bsav157.tvmaze.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bsav157.tvmaze.model.entitites.Favorite;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    List<Favorite> getFavorites();

    @Query("SELECT * FROM favorite WHERE id LIKE :uuid")
    Favorite getFavorite(String uuid);

    @Insert
    void addFavorite(Favorite favorite);

    @Delete
    void deleteFavorite(Favorite favorite);

    @Update
    void updateFavorite(Favorite favorite);
}
