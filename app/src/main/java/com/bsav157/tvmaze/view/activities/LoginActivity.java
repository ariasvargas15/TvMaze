package com.bsav157.tvmaze.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.presenter.LoginPresenter;
import com.bsav157.tvmaze.presenter.interfaces.ILogin;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ILogin.View, View.OnClickListener {

    @BindView(R.id.password) TextInputEditText input;
    @BindView(R.id.login_button) Button button;
    @BindView(R.id.message_info_login) TextView message;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        launchBiometricManager();
        presenter = new LoginPresenter(this);
        button.setOnClickListener(this);
    }

    private void launchBiometricManager() {
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Executor executor = ContextCompat.getMainExecutor(this);
                BiometricPrompt biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        Toast.makeText(getApplicationContext(),"Authentication error: " + errString, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationSucceeded(
                            @NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        openMainActivity();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(getApplicationContext(), "Authentication failed",Toast.LENGTH_SHORT).show();
                    }
                });

                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Biometric login")
                        .setSubtitle("Log in using your biometric credential")
                        .setNegativeButtonText("Use account password")
                        .build();

                biometricPrompt.authenticate(promptInfo);

                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("BiometricErrorNoHardware", "No biometric features available on this device.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("BiometricErrorHWUnavailable", "Biometric features are currently unavailable.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Log.e("BiometricErrorNoneEnrolled", "The user hasn't associated " +
                        "any biometric credentials with their account.");
                break;
            default:
                break;
        }
    }

    @Override
    public void showResponse(boolean response) {
        if(!response){
            message.setText(getResources().getString(R.string.message_password_incorrect));
        } else {
            openMainActivity();
        }
    }

    private void openMainActivity(){
        Intent in = new Intent(this, MainActivity.class);
        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.login_button){
            validateText();
        }
    }

    private void validateText(){
        message.setText("");
        if(input.getText() == null || input.getText().toString().isEmpty()) {
            message.setText(getResources().getString(R.string.message_password_empty));
        } else {
            presenter.validateLogin(getApplication(), input.getText().toString());
        }
    }
}