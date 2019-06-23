package com.era.a100.mvp.interactors;

import android.util.Log;

import com.era.a100.mvp.contracts.AuthContract;
import com.era.a100.retrofit.ApiClient;
import com.era.a100.retrofit.ApiInterfaces;
import com.era.a100.retrofit.models.requests.AuthRequest;
import com.era.a100.retrofit.models.responses.AuthResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthInteractorImpl implements AuthContract.Interactor {

    private ApiInterfaces apiService =
            ApiClient.getInstance().create(ApiInterfaces.class);
    private String TAG = "LOGGERR";

    @Override
    public void auth(OnFinishedListener onFinishedListener, String email, String password) {

        Call<AuthResponse> addVideo = apiService.auth(new AuthRequest(email, password));

        addVideo.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    AuthResponse authResponse = response.body();
                    onFinishedListener.onFinished(authResponse.getToken(), authResponse.getRefreshToken());
                } else onFinishedListener.onFailure("Произошла ошибка сервера "+ statusCode +". Попытайтесь снова");
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure("Произошла ошибка сервера. Попытайтесь снова");
            }
        });

    }
}
