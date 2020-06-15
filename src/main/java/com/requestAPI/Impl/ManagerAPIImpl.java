package com.requestAPI.Impl;

import com.pojo.requestPOJO.*;
import com.pojo.responsePOJO.AccountResponse;
import com.pojo.responsePOJO.PageableResponseAccountResponse;
import com.requestAPI.ManagerAPI;
import com.requestAPI.retrofitBuilder.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ManagerAPIImpl {

    private static Retrofit retrofit;
    private static ManagerAPI managerAPI;

    static {
        try {
            retrofit = RetrofitBuilder.getRetrofit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        managerAPI = retrofit.create(ManagerAPI.class);
    }


    /**
     * 创建user
     * @param authorization
     * @param userCreateRequest
     * @return
     * @throws Exception
     */
    public static String createUser(String authorization,
                                    UserCreateRequest userCreateRequest) throws Exception {

        Call<String> createUserRes = managerAPI.createUser(authorization, userCreateRequest);
        Response<String> res = createUserRes.execute();

        String result;
        if (res.code() == 201) {
            result = "创建user成功";
        } else {
            result = res.errorBody().string();
        }

        return result;
    }


    /**
     * 修改user信息
     * @param authorization
     * @param userManagerUpdateRequest
     * @return
     * @throws Exception
     */
    public static String updateUser(String authorization,
                                    UserManagerUpdateRequest userManagerUpdateRequest) throws Exception {

        Call<String> updateUserRes = managerAPI.updateUser(authorization, userManagerUpdateRequest);
        Response<String> res = updateUserRes.execute();

        String result;
        if (res.code() == 200) {
            result = "修改user信息成功";
        } else {
            result = res.errorBody().string();
        }

        return result;
    }


    /**
     * 通过id获取account信息
     * @param authorization
     * @param id
     * @return
     * @throws Exception
     */
    public static AccountResponse getUserById(String authorization,
                                              Integer id) throws Exception{
        Call<AccountResponse> getUserById = managerAPI.getUserById(authorization, id);
        Response<AccountResponse> userInfo = getUserById.execute();

        if (userInfo.code() == 200) {
            System.out.println("获取user成功");
            AccountResponse userAccount = userInfo.body();
            return userAccount;
        } else {
            System.out.println(userInfo.errorBody().string());
            return null;
        }
    }


    /**
     * 通过name获取account信息
     * @param authorization
     * @param account_name
     * @return
     * @throws Exception
     */
    public static AccountResponse getUserByName(String authorization,
                                                String account_name) throws Exception {

        Call<AccountResponse> getUserByName = managerAPI.getUserByName(authorization, account_name);
        Response<AccountResponse> userInfo = getUserByName.execute();

        if (userInfo.code() == 200) {
            System.out.println("获取user信息成功");
            AccountResponse userAccount = userInfo.body();
            return userAccount;
        } else {
            System.out.println(userInfo.errorBody().string());
            return null;
        }
    }


    /**
     * 获取account信息
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseAccountResponse getAccountsPageable(String authorization,
                                                                      Integer page,
                                                                      Integer size) throws Exception {

        Call<PageableResponseAccountResponse> getAccountsPageable = managerAPI.getAccountsPageable(authorization, page, size);
        Response<PageableResponseAccountResponse> accountPages = getAccountsPageable.execute();

        if (accountPages.code() == 200) {
            System.out.println("获取accont信息成功");
            PageableResponseAccountResponse accountsPages = accountPages.body();
            return accountsPages;
        } else {
            System.out.println(accountPages.errorBody().string());
            return null;
        }
    }


    /**
     * 添加节点
     * @param authorization
     * @param peerCreateRequest
     * @return
     * @throws Exception
     */
    public static String addPeer(String authorization,
                                 PeerCreateRequest peerCreateRequest) throws Exception {

        Call<String> addPeerRes = managerAPI.addPeer(authorization, peerCreateRequest);
        Response<String> res = addPeerRes.execute();

        String result;
        if (res.code() == 201) {
            result = "创建节点成功";
        } else {
            result = res.errorBody().string();
        }

        return result;
    }


    /**
     * 修改节点信息
     * @param authorization
     * @param peerUpdateRequest
     * @return
     * @throws Exception
     */
    public static String updatePeer(String authorization,
                                    PeerUpdateRequest peerUpdateRequest) throws Exception {

        Call<String> updatePeerRes = managerAPI.updatePeer(authorization, peerUpdateRequest);
        Response<String> res = updatePeerRes.execute();

        String result;
        if (res.code() == 200) {
            result = "修改节点信息成功";
        } else {
            result = res.errorBody().string();
        }

        return result;
    }


    /**
     * 添加组织
     * @param authorization
     * @param orgCreateRequest
     * @return
     * @throws Exception
     */
    public static String addOrg(String authorization,
                              OrgCreateRequest orgCreateRequest) throws Exception {

        Call<String> addOrgRes = managerAPI.addOrg(authorization, orgCreateRequest);
        Response<String> res = addOrgRes.execute();

        String result;
        if (res.code() == 201) {
            result = "添加组织成功";
        } else {
            result = res.errorBody().string();
        }
        return result;
    }


    /**
     * 更新组织信息
     * @param authorization
     * @param orgUpdateRequest
     * @return
     * @throws Exception
     */
    public static String updateOrg(String authorization,
                                 OrgUpdateRequest orgUpdateRequest) throws Exception {

        Call<String> updateOrgRes = managerAPI.updateOrg(authorization, orgUpdateRequest);
        Response<String> res = updateOrgRes.execute();

        String result;
        if (res.code() == 200) {
            result = "更新组织信息成功";
        } else {
            result = res.errorBody().string();
        }

        return result;
    }


    /**
     * 添加排序节点
     * @param authorization
     * @param ordererCreateRequest
     * @return
     * @throws Exception
     */
    public static String addOrderer(String authorization,
                                    OrdererCreateRequest ordererCreateRequest) throws Exception {

        Call<String> addOrdererRes = managerAPI.addOrderer(authorization, ordererCreateRequest);
        Response<String> res = addOrdererRes.execute();

        String result;

        if (res.code() == 201){
            result = "添加排序节点成功";
        } else {
            result = res.errorBody().string();
        }

        return result;
    }


    /**
     * 更新排序节点信息
     * @param authorization
     * @param ordererUpdateRequest
     * @return
     * @throws Exception
     */
    public static String updateOrderer(String authorization,
                                       OrdererUpdateRequest ordererUpdateRequest) throws Exception {

        Call<String> updateOrdererRes = managerAPI.updateOrderer(authorization, ordererUpdateRequest);
        Response<String> res = updateOrdererRes.execute();

        String result;

        if (res.code() == 200) {
            result = "修改排序节点信息成功";
        } else {
            result = res.errorBody().string();
        }

        return result;
    }


    /**
     * 创建账本
     * @param authorization
     * @param leagueManageRequest
     * @return
     * @throws Exception
     */
    public static String createLeague(String authorization,
                                      LeagueManageRequest leagueManageRequest) throws Exception {

        Call<String> createLeagueRes = managerAPI.createLeague(authorization, leagueManageRequest);
        Response<String> res = createLeagueRes.execute();

        String result;
        if (res.code() == 201) {
            result = "创建账本成功";
        } else {
            result = res.errorBody().string();
        }

        return result;
    }


    /**
     * 更新账本
     * @param authorization
     * @param id
     * @param leagueManageRequest
     * @return
     * @throws Exception
     */
    public static String updateLeague(String authorization, Integer id,
                                      LeagueManageRequest leagueManageRequest) throws Exception {

        Call<String> updateLeagueRes = managerAPI.updateLeague(authorization, id, leagueManageRequest);
        Response<String> res = updateLeagueRes.execute();

        String result;
        if (res.code() == 200) {
            result = "修改账本成功";
        } else {
            result = res.errorBody().string();
        }

        return result;
    }


}
