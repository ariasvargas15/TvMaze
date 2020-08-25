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

public class SettingsFragment extends Fragment implements ISettings.View, View.OnClickListener {

    private TextInputEditText lastP;
    private TextInputEditText newP;
    private TextInputEditText confirmP;
    private TextInputLayout layoutP;
    private Button button;
    private TextView message;
    private SettingsPresenter presenter;
    private TextView header;
    private boolean lastIsVisible;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        lastP = view.findViewById(R.id.last_password);
        newP = view.findViewById(R.id.new_password);
        confirmP = view.findViewById(R.id.confirm_password);
        button = view.findViewById(R.id.button_settings_password);
        layoutP = view.findViewById(R.id.layout_last_password);
        button.setEnabled(false);
        confirmP.setEnabled(false);
        message = view.findViewById(R.id.message_settings);
        header = view.findViewById(R.id.settings_header_text);
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
        Log.e("paso", "paso2");

        if(response){
            message.setText(getResources().getString(R.string.change_password_success));
        } else {
            message.setText(getResources().getString(R.string.last_password_incorrect));
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_settings_password){
            String l;
            String n;
            Log.e("paso", "paso");
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
                Log.e("textWatcher", start + " -- " + before + " -- " + count);
                if(text.length() < 4){
                    message.setText(getResources().getString(R.string.password_short));
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