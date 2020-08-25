package com.bsav157.tvmaze.model.entitites;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Embedded implements Serializable
{

    @SerializedName("show")
    @Expose
    private Show show;
    private final static long serialVersionUID = -8203828679015117046L;

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    @Override
    public String toString() {
        return "Embedded{" +
                "show=" + show +
                '}';
    }
}
