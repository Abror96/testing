package com.era.a100.retrofit;

import com.era.a100.retrofit.models.requests.AuthRequest;
import com.era.a100.retrofit.models.responses.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterfaces {

    // auth
    @Headers({"Content-Type:application/json"})
    @POST("auth/authenticate")
    Call<AuthResponse> auth(@Body AuthRequest authRequest);

}
