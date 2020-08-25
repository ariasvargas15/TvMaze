package com.bsav157.tvmaze.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.presenter.SettingsPresenter;
import com.bsav157.tvmaze.presenter.interfaces.ISettings;
import com.bsav157.tvmaze.utils.Defines;
import com.bsav157.tvmaze.utils.Util;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsFragment extends Fragment implements ISettings.View, View.OnClickListener {

    @BindView(R.id.last_password) TextInputEditText lastP;
    @BindView(R.id.new_password) TextInputEditText newP;
    @BindView(R.id.confirm_password) TextInputEditText confirmP;
    @BindView(R.id.layout_last_password) TextInputLayout layoutP;
    @BindView(R.id.button_settings_password) Button button;
    @BindView(R.id.message_settings) TextView message;
    @BindView(R.id.settings_header_text) TextView header;
    private SettingsPresenter presenter;
    private boolean lastIsVisible;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        button.setEnabled(false);
        confirmP.setEnabled(false);
        setViews();
        checkMinLenght();
        presenter = new SettingsPresenter(this);
        button.setOnClickListener(this);
        return view;
    }

    private void setViews() {
        try {
            boolean val = Util.getValuePreference(Objects.requireNonNull(getContext()), Defines.TOKEN).isEmpty();
            lastIsVisible = !val;
            if(val){
                header.setText(getResources().getString(R.string.set_password));
                button.setText(getResources().getString(R.string.set_button));
                layoutP.setVisibility(View.GONE);
            } else {
                header.setText(getResources().getString(R.string.change_password));
                button.setText(getResources().getString(R.string.change_button));
            }
        } catch (Exception e){
            Log.e("setViewsFragmentSettings", "Null");
        }
    }

    @Override
    public void showResponse(boolean response) {
        if(response){
            message.setText(getResources().getString(R.string.change_password_success));
            lastP.setText("");
            newP.setText("");
            confirmP.setText("");
        } else {
            message.setText(getResources().getString(R.string.last_password_incorrect));
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_settings_password){
            String l;
            String n;
            if(!comparePasswords()){
                message.setText(getResources().getString(R.string.password_equals));
            } else {
                if(lastIsVisible){
                    if(newP.getText() != null && lastP.getText() != null){
                        n = newP.getText().toString();
                        l = lastP.getText().toString();
                        presenter.changePassword(getContext(), l, n);
                    }
                } else {
                    if(newP.getText() != null){
                        n = newP.getText().toString();
                        l = "";
                        presenter.changePassword(getContext(), l, n);
                    }
                }
            }
        }
    }

    private boolean comparePasswords() {
        if(newP.getText() != null && confirmP.getText() != null){
            String n = newP.getText().toString();
            String c = confirmP.getText().toString();
            return n.equals(c);
        }
        return false;
    }

    private void checkMinLenght(){
        newP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence text, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                if(text.length() < 4){
                    if(text.length() > 0) message.setText(getResources().getString(R.string.password_short));
                    button.setEnabled(false);
                    confirmP.setEnabled(false);
                } else {
                    message.setText("");
                    button.setEnabled(true);
                    confirmP.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}