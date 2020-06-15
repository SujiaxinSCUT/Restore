package com.requestAPI.Impl;

import com.pojo.responsePOJO.LoginResponse;
import com.requestAPI.LoginAPI;
import com.requestAPI.retrofitBuilder.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.HashMap;
import java.util.Map;

public class LoginAPIImpl {

    private static Retrofit retrofit;
    private static LoginAPI loginAPI;

    static {
        try {
            retrofit = RetrofitBuilder.getRetrofit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginAPI = retrofit.create(LoginAPI.class);
    }


    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public static LoginResponse login(String username, String password) throws Exception{

        Map<String, Object> map = new HashMap<>();

        map.put("username", username);
        map.put("password", password);
        map.put("grant_type", "password");
        map.put("scope", "read write trust");
        map.put("client_id", "client_web");
        map.put("client_secret", "storic-secret");
        Call<LoginResponse> loginRes = loginAPI.login(map);
        Response<LoginResponse> res = loginRes.execute();


        if (res.code() == 200) {
            System.out.println("登录成功");
            LoginResponse token = res.body();
            return token;
        } else {
            System.out.println(res.errorBody().string());
            return null;
        }
    }


    /**
     * 刷新token
     * @param refresh_token
     * @param scope
     * @return
     * @throws Exception
     */
    public static LoginResponse refreshToken(String refresh_token, String scope) throws Exception{

        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "refresh_token");
        map.put("scope", scope);
        map.put("client_id", "client_web");
        map.put("client_secret", "storic-secret");
        map.put("refresh_token", refresh_token);

        Call<LoginResponse> refreshTokenRes = loginAPI.refreshToken(map);
        Response<LoginResponse> res = refreshTokenRes.execute();

        if (res.code() == 200) {
            System.out.println("更新token成功");
            LoginResponse refreshToken = res.body();
            return refreshToken;
        } else {
            System.out.println(res.errorBody().string());
            return null;
        }

    }
}
