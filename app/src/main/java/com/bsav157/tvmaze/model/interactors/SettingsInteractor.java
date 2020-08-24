package com.bsav157.tvmaze.model.interactors;

import android.content.Context;

import com.bsav157.tvmaze.presenter.interfaces.ISettings;
import com.bsav157.tvmaze.utils.Defines;
import com.bsav157.tvmaze.utils.Util;

public class SettingsInteractor {

    private ISettings.Presenter presenter;

    public SettingsInteractor(ISettings.Presenter presenter){
        this.presenter = presenter;
    }

    public void changePassword(Context context, String lastP, String newP){
        String val = Util.getValuePreference(context, Defines.TOKEN);
        boolean response = val.equals(lastP);
        if(response){
            Util.saveValuePreference(context, Defines.TOKEN, newP);
        }
        presenter.showResponse(response);
    }
}
