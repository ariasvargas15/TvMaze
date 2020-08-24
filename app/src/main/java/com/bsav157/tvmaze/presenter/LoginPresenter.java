package com.bsav157.tvmaze.presenter;

import android.content.Context;

import com.bsav157.tvmaze.model.interactors.LoginInteractor;
import com.bsav157.tvmaze.presenter.interfaces.ILogin;

public class LoginPresenter implements ILogin.Presenter{

    private ILogin.View view;

    public LoginPresenter(ILogin.View view){
        this.view = view;
    }

    @Override
    public void validateLogin(Context context, String token) {
        LoginInteractor interactor = new LoginInteractor(this);
        interactor.validateToken(context, token);
    }

    @Override
    public void showResponse(boolean response) {
        view.showResponse(response);
    }
}
