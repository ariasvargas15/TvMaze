package com.bsav157.tvmaze.presenter.interfaces;

import android.content.Context;

public interface ISettings {

    interface View{
        void showResponse(boolean response);

    }

    interface Presenter{
        void showResponse(boolean response);
        void changePassword(Context context, String lastP, String newP);
    }
}
