package com.bsav157.tvmaze.model.interactors;

import android.content.Context;

import com.bsav157.tvmaze.presenter.interfaces.ILogin;
import com.bsav157.tvmaze.utils.Defines;
import com.bsav157.tvmaze.utils.Util;

public class LoginInteractor {

    private ILogin.Presenter presenter;

    public LoginInteractor (ILogin.Presenter presenter){
        this.presenter = presenter;
    }

    public void validateToken(Context context, String token){
        presenter.showResponse(Util.getValuePreference(context, Defines.TOKEN).equals(token));
    }
}
