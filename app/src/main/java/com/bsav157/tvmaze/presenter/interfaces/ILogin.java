package com.bsav157.tvmaze.presenter.interfaces;

import android.content.Context;

public interface ILogin {

    interface View{
        void showResponse(boolean response);
    }

    interface Presenter{
        void validateLogin(Context context, String token);
        void showResponse(boolean response);
    }
}
