package com.bsav157.tvmaze.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
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

public class LoginActivity extends AppCompatActivity implements ILogin.View, View.OnClickListener {

    private TextInputEditText input;
    private Button button;
    private TextView message;
    private LoginPresenter presenter;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        launchBiometricManager();

        input = findViewById(R.id.password);
        button = findViewById(R.id.login_button);
        message = findViewById(R.id.message_info_login);
        presenter = new LoginPresenter(this);

        button.setOnClickListener(this);

    }

    private void launchBiometricManager() {
        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                executor = ContextCompat.getMainExecutor(this);
                biometricPrompt = new BiometricPrompt(LoginActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        Toast.makeText(getApplicationContext(),"Authentication error: " + errString, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationSucceeded(
                            @NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(in);
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(getApplicationContext(), "Authentication failed",Toast.LENGTH_SHORT).show();
                    }
                });

                promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Biometric login")
                        .setSubtitle("Log in using your biometric credential")
                        .setNegativeButtonText("Use account password")
                        .build();

                biometricPrompt.authenticate(promptInfo);

                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e("MY_APP_TAG", "No biometric features available on this device.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Log.e("MY_APP_TAG", "The user hasn't associated " +
                        "any biometric credentials with their account.");
                break;

        }
    }

    @Override
    public void showResponse(boolean response) {
        if(!response){
            message.setText(getResources().getString(R.string.message_password_incorrect));
        } else {
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
        }
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