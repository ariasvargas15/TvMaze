package com.bsav157.tvmaze.model.entitites;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CastCredit implements Serializable {


    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    @Override
    public String toString() {
        return "CastCredit{" +
                "embedded=" + embedded +
                '}';
    }
}