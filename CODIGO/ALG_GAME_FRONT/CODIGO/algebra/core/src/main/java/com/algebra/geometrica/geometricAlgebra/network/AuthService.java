package com.algebra.geometrica.geometricAlgebra.network;

import com.algebra.geometrica.geometricAlgebra.DTO.LoginRequest;
import com.algebra.geometrica.geometricAlgebra.utils.AppPaths;
import com.google.gson.Gson;
import okhttp3.*;

import java.util.concurrent.TimeUnit;

public class AuthService {

    private static final String BASE_URL = AppPaths.Server.URL_SERVER_DEV + AppPaths.Auth.AUTH_PATH;
    private final OkHttpClient client;
    private final Gson gson;


    public AuthService() {
        this.client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();
        this.gson = new Gson();
    }

    public void login(String userName, String passUser, Callback callback){
        String url = BASE_URL + AppPaths.Auth.AUTH_LOGIN;

        RequestBody body = RequestBody.create(
            gson.toJson(new LoginRequest(userName, passUser)),
            MediaType.get("application/json")
        );

        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();

        client.newCall(request).enqueue(callback);
    }

    public void register (String userName, String passUser, Callback callback){
        String url = BASE_URL + AppPaths.Auth.AUTH_REGISTER;

        RequestBody body = RequestBody.create(
            gson.toJson(new LoginRequest(userName, passUser)),
            MediaType.get("application/json")
        );

        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();

        client.newCall(request).enqueue(callback);

    }


}
