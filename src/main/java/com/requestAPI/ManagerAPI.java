package com.requestAPI;

import com.pojo.requestPOJO.*;
import com.pojo.responsePOJO.AccountResponse;
import com.pojo.responsePOJO.PageableResponseAccountResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface ManagerAPI {

    /**
     * 管理员创建user
     * @param authorization
     * @param userCreateRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/admin/users")
    Call<String> createUser(@Header("Authorization") String authorization,
                            @Body UserCreateRequest userCreateRequest);


    /**
     * 管理员更新账号信息
     * @param authorization
     * @param userManagerUpdateRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/admin/users")
    Call<String> updateUser(@Header("Authorization") String authorization,
                            @Body UserManagerUpdateRequest userManagerUpdateRequest);


    /**
     * 通过id获取user
     * @param authorization
     * @param id
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/admin/users/id/{id}")
    Call<AccountResponse> getUserById(@Header("Authorization") String authorization,
                                      @Path("id") Integer id);


    /**
     * 通过名字获取user
     * @param authorization
     * @param account_name
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/admin/users/name/{account_name}")
    Call<AccountResponse> getUserByName(@Header("Authorization") String authorization,
                                      @Path("account_name") String account_name);


    /**
     * 获取Accounts信息
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/admin/users/pageable/{page}/{size}")
    Call<PageableResponseAccountResponse> getAccountsPageable(
            @Header("Authorization") String authorization,
            @Path("page") Integer page,
            @Path("size") Integer size);


    /**
     * 添加节点
     * @param authorization
     * @param peerCreateRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/admin/peer")
    Call<String> addPeer(@Header("Authorization") String authorization,
                         @Body PeerCreateRequest peerCreateRequest);


    /**
     * 更新节点信息
     * @param authorization
     * @param peerUpdateRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/admin/peer")
    Call<String> updatePeer(@Header("Authorization") String authorization,
                            @Body PeerUpdateRequest peerUpdateRequest);



    /**
     * 添加组织
     * @param authorization
     * @param orgCreateRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/admin/org")
    Call<String> addOrg(@Header("Authorization") String authorization,
                        @Body OrgCreateRequest orgCreateRequest);


    /**
     * 更新组织信息
     * @param authorization
     * @param orgUpdateRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/admin/org")
    Call<String> updateOrg(@Header("Authorization") String authorization,
                           @Body OrgUpdateRequest orgUpdateRequest);


    /**
     * 添加排序节点
     * @param authorization
     * @param ordererCreateRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/admin/orderer")
    Call<String> addOrderer(@Header("Authorization") String authorization,
                            @Body OrdererCreateRequest ordererCreateRequest);


    /**
     * 更新排序节点
     * @param authorization
     * @param ordererUpdateRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/admin/orderer")
    Call<String> updateOrderer(@Header("Authorization") String authorization,
                            @Body OrdererUpdateRequest ordererUpdateRequest);


    /**
     * 添加账本
     * @param authorization
     * @param leagueManageRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @POST("/admin/league")
    Call<String> createLeague(@Header("Authorization") String authorization,
                            @Body LeagueManageRequest leagueManageRequest);


    /**
     * 更新账本信息
     * @param authorization
     * @param id
     * @param leagueManageRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @PUT("/admin/league/id/{id}")
    Call<String> updateLeague(@Header("Authorization") String authorization,
                              @Path("id") Integer id,
                              @Body LeagueManageRequest leagueManageRequest);


}
