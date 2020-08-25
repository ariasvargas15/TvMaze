package com.bsav157.tvmaze.model.entitites;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Favorite {

    @PrimaryKey @NonNull private Integer id;

    public Favorite(@NonNull Integer id){
        this.id = id;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

}
