package com.requestAPI;

import com.pojo.responsePOJO.LoginResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface LoginAPI {

    /**
     * 登录
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/oauth/token")
    Call<LoginResponse> login(@QueryMap(encoded = true) Map<String, Object> map);


    /**
     * 更新token
     * @param map
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/oauth/token")
    Call<LoginResponse> refreshToken(@QueryMap(encoded = true) Map<String, Object> map);
}
