package com.requestAPI;

import com.pojo.Block;
import com.pojo.Channel;
import com.pojo.requestPOJO.UserRegisterRequest;
import com.pojo.responsePOJO.*;
import retrofit2.Call;
import retrofit2.http.*;


import java.util.Date;
import java.util.List;

public interface UserAPI {

    /**
     * 获取当前account的信息
     * @param authorization
     * @return
     */
    @Headers({
            "Content-Type: application/json",
            "Connection: keep-alive"
    })
    @GET("/user")
    Call<AccountResponse> getUserAccountInfo(@Header("Authorization") String authorization);


    /**
     * 注册user
     * @param userRegisterRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json",
            "Connection: keep-alive"
    })
    @POST("/user")
    Call<String> signUp(@Body UserRegisterRequest userRegisterRequest);


    /**
     * 修改密码
     * @param authorization
     * @param userRegisterRequest
     * @return
     */
    @Headers({
            "Content-Type: application/json",
            "Connection: keep-alive"
    })
    @PUT("/user")
    Call<String> updateAccountInfo(@Header("Authorization") String authorization, @Body UserRegisterRequest userRegisterRequest);


    /**
     * 获取leagues信息
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/leagues/pageable/{page}/{size}")
    Call<PageableResponseLeague> getLeaguesPageable(@Header("Authorization") String authorization,
                                                    @Path("page") Integer page,
                                                    @Path("size") Integer size);


    /**
     * 获取区块信息
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/blocks/pageable/{page}/{size}")
    Call<PageableResponseBlock> getBlocksPageable(@Header("Authorization") String authorization,
                                                  @Path("page") Integer page,
                                                  @Path("size") Integer size);


    /**
     * 倒序获取区块信息
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/blocks/pageable/desc/{page}/{size}")
    Call<PageableResponseBlock> getDescBlockPageable(@Header("Authorization") String authorization,
                                                     @Path("page") Integer page,
                                                     @Path("size") Integer size);


    /**
     * 通过channel的id获取相应的区块
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/blocks/channel_id/{id}/pageable/{page}/{size}")
    Call<PageableResponseBlock> getBlocksByChannelIdPageable(@Header("Authorization") String authorization,
                                                  @Path("id") Integer id,
                                                  @Path("page") Integer page,
                                                  @Path("size") Integer size);


    /**
     * 通过channelid倒序获取区块信息
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/blocks/channel_id/{id}/pageable/desc/{page}/{size}")
    Call<PageableResponseBlock> getDescBlockByChannelIdPageable(@Header("Authorization") String authorization,
                                                                @Path("id") Integer id,
                                                                @Path("page") Integer page,
                                                                @Path("size") Integer size);

    /**
     * 获取区块最近五天折线图数据
     * @param authorization
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/blocks/linechart_data")
    Call<LineChartDataResponse<Date>> getBlocksLastFiveDays(@Header("Authorization") String authorization);


    /**
     * 获取orderers信息
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/orderers/pageable/{page}/{size}")
    Call<PageableResponseOrderer> getOrdererPageable(@Header("Authorization") String authorization,
                                                     @Path("page") Integer page,
                                                     @Path("size") Integer size);


    /**
     * 通过league的id获取orderers的信息
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/orderers/league/{id}/pageable/{page}/{size}")
    Call<PageableResponseOrderer> getOrdererByLeagueIdPageable(@Header("Authorization") String authorization,
                                                               @Path("id") Integer id,
                                                               @Path("page") Integer page,
                                                               @Path("size") Integer size);


    /**
     * 获取组织信息
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/orgs/pageable/{page}/{size}")
    Call<PageableResponseOrg> getOrgPageable(@Header("Authorization") String authorization,
                                             @Path("page") Integer page,
                                             @Path("size") Integer size);


    /**
     * 通过league的id获取orgs
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/orgs/league/{id}/pageable/{page}/{size}")
    Call<PageableResponseOrg> getOrgByLeagueIdPageable(@Header("Authorization") String authorization,
                                                       @Path("id") Integer id,
                                                       @Path("page") Integer page,
                                                       @Path("size") Integer size);

    /**
     * 获取peers的信息
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/peers/pageable/{page}/{size}")
    Call<PageableResponsePeer> getPeersPageable(@Header("Authorization") String authorization,
                                                @Path("page") Integer page,
                                                @Path("size") Integer size);


    /**
     * 获取交易信息
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/txs/pageable/{page}/{size}")
    Call<PageableResponseTransaction> getTransactionsPageable(@Header("Authorization") String authorization,
                                                              @Path("page") Integer page,
                                                              @Path("size") Integer size);


    /**
     * 倒序获取交易的分页信息
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/txs/pageable/desc/{page}/{size}")
    Call<PageableResponseTransaction> getDescTransactionPageable(@Header("Authorization") String authorization,
                                                                 @Path("page") Integer page,
                                                                 @Path("size") Integer size);


    /**
     * 获取区块的交易信息
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/txs/block_id/{id}/pageable/{page}/{size}")
    Call<PageableResponseTransaction> getTransactionPageableByBlock(@Header("Authorization") String authorization,
                                                                     @Path("id") Integer id,
                                                                     @Path("page") Integer page,
                                                                     @Path("size") Integer size);
    /**
     * 倒序获取区块的交易信息
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/txs/block_id/{id}/pageable/desc/{page}/{size}")
    Call<PageableResponseTransaction> getDescTransactionPageableByBlock(@Header("Authorization") String authorization,
                                                                    @Path("id") Integer id,
                                                                    @Path("page") Integer page,
                                                                    @Path("size") Integer size);
    /**
     * 获取交易最近五天折线图数据
     * @param authorization
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/txs/linechart_data")
    Call<LineChartDataResponse<Date>> getTransactionsLastFiveDays(@Header("Authorization") String authorization);
    
    
    /**
     * 获取channel的list
     * @param authorization
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/channel")
    Call<List<Channel>> getChannelList (@Header("Authorization") String authorization);


    /**
     * 分页查询Channel
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/channel/pageable/{page}/{size}")
    Call<PageableResponseChannel> getPageableChannel (@Header("Authorization") String authorization,
                                                      @Path("page") Integer page,
                                                      @Path("size") Integer size);

    /**
     * 倒序获取读写集的分页信息
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/rw_set/pageable/desc/{page}/{size}")
    Call<PageableResponseRWSet> getDescRWSetsPageable(@Header("Authorization") String authorization,
                                                                 @Path("page") Integer page,
                                                                 @Path("size") Integer size);

    /**
     * 获取读写集信息
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/rw_set/pageable/{page}/{size}")
    Call<PageableResponseRWSet> getRWSetsPageable(@Header("Authorization") String authorization,
                                                              @Path("page") Integer page,
                                                              @Path("size") Integer size);

    /**
     * 获取读写集最近五天折线图数据
     * @param authorization
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/rw_set/linechart_data")
    Call<LineChartDataResponse<Date>> getRWSetsLastFiveDays(@Header("Authorization") String authorization);
    
    /**
     * 获取当天区块的list
     * @param authorization
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/blocks/current_day_data")
    Call<List<Block>> getCurrentDayBlocks(@Header("Authorization") String authorization);
    
    /**
     * 分页查询Chaincode
     * @param authorization
     * @param page
     * @param size
     * @return
     */
    @Headers({
            "Content-Type: application/json"
    })
    @GET("/chaincodes/pageable/{page}/{size}")
    Call<PageableResponseChaincode> getPageableChaincode (@Header("Authorization") String authorization,
                                                      @Path("page") Integer page,
                                                      @Path("size") Integer size);

    
    
}
