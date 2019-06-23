package com.era.a100.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.era.a100.R;
import com.era.a100.databinding.ActivityAuthBinding;
import com.era.a100.mvp.contracts.AuthContract;
import com.era.a100.mvp.interactors.AuthInteractorImpl;
import com.era.a100.mvp.presenters.AuthPresenterImpl;
import com.era.a100.utils.PrefConfig;
import com.google.android.material.snackbar.Snackbar;

import static com.era.a100.utils.Constants.initProgressDialog;

public class AuthActivity extends AppCompatActivity implements AuthContract.View {

    private ActivityAuthBinding binding;
    private AuthContract.Presenter presenter;
    private PrefConfig prefConfig;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);

        presenter = new AuthPresenterImpl(this, new AuthInteractorImpl());
        prefConfig = new PrefConfig(this);
        progressDialog = initProgressDialog(this, "Идёт загрузка");

        if (prefConfig.getLoginStatus()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.etEmail.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();
                if (!email.isEmpty() && !password.isEmpty()) {
                    presenter.onAuthClicked(email, password);
                } else {
                    Snackbar.make(binding.mainView, "Заполните все поля", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onAuthSuccess(String token, String refreshToken) {
        Log.d("LOGGERR", " \nToken: " + token + "\nRefresh token: " + refreshToken);
        prefConfig.setLoginStatus(true);
        prefConfig.setToken(token);
        prefConfig.setRefreshToken(refreshToken);

        startActivity(new Intent(this, AndroidLauncherActivity.class));
        finish();
    }

    @Override
    public void showSnackbar(String message) {
        Snackbar.make(binding.mainView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }
}
