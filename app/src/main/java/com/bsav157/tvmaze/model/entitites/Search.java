package com.bsav157.tvmaze.model.entitites;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search implements Serializable {

    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("show")
    @Expose
    private Show show;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "Query{" +
                "score='" + score + '\'' +
                ", show=" + show +
                '}';
    }
}