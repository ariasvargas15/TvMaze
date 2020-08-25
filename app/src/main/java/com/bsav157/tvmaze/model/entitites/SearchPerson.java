package com.bsav157.tvmaze.model.entitites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchPerson implements Serializable {

    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("person")
    @Expose
    private Person person;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "SearchPerson{" +
                "score=" + score +
                ", person=" + person +
                '}';
    }
}