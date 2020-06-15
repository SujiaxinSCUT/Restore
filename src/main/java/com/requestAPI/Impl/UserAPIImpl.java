package com.requestAPI.Impl;

import com.pojo.Block;
import com.pojo.Channel;
import com.pojo.requestPOJO.UserRegisterRequest;
import com.pojo.responsePOJO.*;
import com.requestAPI.UserAPI;
import com.requestAPI.retrofitBuilder.RetrofitBuilder;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class UserAPIImpl {

    private static Retrofit retrofit;
    private static UserAPI userAPI;

    static {
        try {
            retrofit = RetrofitBuilder.getRetrofit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userAPI = retrofit.create(UserAPI.class);
    }

    /**
     * 获取当前account信息，返回account
     * @param authorization
     * @return
     * @throws IOException
     */
    public static AccountResponse getUserAccountInfo(String authorization) throws IOException {

        Call<AccountResponse> getUserAccountInfo = userAPI.getUserAccountInfo(authorization);
        Response<AccountResponse> userAccountInfo = getUserAccountInfo.execute();

        if (userAccountInfo.code() == 200) {
            System.out.println("获取用户信息成功");
            AccountResponse curAccount = userAccountInfo.body();
            return curAccount;
        } else {
            System.out.println(userAccountInfo.errorBody().string());
            return null;
        }
    }

    /**
     * 注册,返回提示信息
     * @param name
     * @param password
     * @return
     * @throws IOException
     */
    public static String signUp(String name, String password) throws IOException {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setName(name);
        userRegisterRequest.setPassword(password);

        Call<String> signUp = userAPI.signUp(userRegisterRequest);
        Response<String> signUpResult = signUp.execute();

        String mes;
        if (signUpResult.code() == 200) {
            mes = "注册成功";
        } else {
            mes = signUpResult.errorBody().string();
        }

        return mes;
    }

    /**
     * 更新账号密码，返回提示信息
     * @param authorization
     * @param name
     * @param password
     * @throws IOException
     */
    public static String updateAccountInfo(
            String authorization, String name, String password) throws IOException {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setName(name);
        userRegisterRequest.setPassword(password);

        Call<String> updateAccountInfo = userAPI.updateAccountInfo(authorization, userRegisterRequest);

        Response<String> updateResult = updateAccountInfo.execute();

        String mes = "";
        if (updateResult.code() == 200) {
            mes = "修改账号密码成功";
        } else {
            mes = updateResult.errorBody().string();
        }

        return mes;
    }

    /**
     * 获取账本信息
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public static PageableResponseLeague getLeaguesPageable(
            String authorization, Integer page, Integer size) throws IOException {

        Call<PageableResponseLeague> getLeaguesPage = userAPI.getLeaguesPageable(authorization, page, size);

        Response<PageableResponseLeague> leaguesPage = getLeaguesPage.execute();

        if (leaguesPage.code() == 200) {
            System.out.println("获取账本成功");
            PageableResponseLeague leaguesPages = leaguesPage.body();
            return leaguesPages;
        } else {
            System.out.println(leaguesPage.errorBody().string());
            return null;
        }
    }


    /**
     * 获取区块信息
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public static PageableResponseBlock getBlocksPageable(
            String authorization, Integer page, Integer size) throws IOException {

        Call<PageableResponseBlock> getBlocksPage = userAPI.getBlocksPageable(authorization, page, size);

        Response<PageableResponseBlock> blocksPage = getBlocksPage.execute();

        if (blocksPage.code() == 200) {
            PageableResponseBlock blocksPages = blocksPage.body();
            System.out.println("获取区块成功");
            return blocksPages;
        } else {
            System.out.println(blocksPage.errorBody().string());
            return null;
        }

    }


    /**
     * 获取倒序的区块分页信息
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public static PageableResponseBlock getDescBlocksPageable(
            String authorization, Integer page, Integer size) throws IOException {

        Call<PageableResponseBlock> getDescBlocksPage = userAPI.getDescBlockPageable(authorization,page,size);

        Response<PageableResponseBlock> descBlocksPage = getDescBlocksPage.execute();

        if (descBlocksPage.code() == 200) {
            PageableResponseBlock descBlocksPages = descBlocksPage.body();
            System.out.println("获取倒序区块成功");
            return descBlocksPages;
        } else {
            System.out.println(descBlocksPage.errorBody().string());
            return null;
        }

    }



    /**
     * 获取通道的区块
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public static PageableResponseBlock getBlocksByChannelIdPageable(
            String authorization, Integer id, Integer page, Integer size) throws IOException {

        Call<PageableResponseBlock> getBlocksPageByChannel = userAPI.getBlocksByChannelIdPageable(authorization, id, page, size);

        Response<PageableResponseBlock> blocksPageInChannel = getBlocksPageByChannel.execute();

        if (blocksPageInChannel.code() == 200) {
            PageableResponseBlock channelBlocksPages = blocksPageInChannel.body();
            System.out.println("获取通道区块成功");
            return channelBlocksPages;
        } else {
            System.out.println(blocksPageInChannel.errorBody().string());
            return null;
        }

    }


    /**
     * 倒序获取通道区块信息
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     * @throws IOException
     */
    public static PageableResponseBlock getDescBlocksByChannelIdPageable(
            String authorization, Integer id, Integer page, Integer size) throws IOException {

        Call<PageableResponseBlock> getDescBlocksPageByChannel = userAPI.getDescBlockByChannelIdPageable(authorization, id, page, size);

        Response<PageableResponseBlock> descBlocksPageInChannel = getDescBlocksPageByChannel.execute();

        if (descBlocksPageInChannel.code() == 200) {
            PageableResponseBlock descChannelBlocksPages = descBlocksPageInChannel.body();
            System.out.println("获取通道区块成功");
            return descChannelBlocksPages;
        } else {
            System.out.println(descBlocksPageInChannel.errorBody().string());
            return null;
        }

    }
    
    /**
     * 获取区块最近五天折线图数据
     * @param authorization
     * @return
     */
    public static LineChartDataResponse<Date> getBlocksLastFiveDays(String authorization) throws Exception {
    	Call<LineChartDataResponse<Date>> getBlocksLastFiveDays = userAPI.getBlocksLastFiveDays(authorization);
        Response<LineChartDataResponse<Date>> response = getBlocksLastFiveDays.execute();
        
        if(response.code()==200) {
        	LineChartDataResponse<Date> lineChartData = response.body();
        	System.out.println("获取区块最近五天折线图数据成功");
        	return lineChartData;
        }else {
        	System.out.println(response.errorBody().string());
            return null;
        }   	
    }


    /**
     * 获取orderers信息
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseOrderer getOrdererPageable(
            String authorization, Integer page, Integer size) throws Exception {
        Call<PageableResponseOrderer> getOrdererPageable = userAPI.getOrdererPageable(authorization, page, size);
        Response<PageableResponseOrderer> orderersPages = getOrdererPageable.execute();

        if (orderersPages.code() == 200) {
            PageableResponseOrderer pageableResponseOrderer = orderersPages.body();
            System.out.println("获取orderer成功");
            return pageableResponseOrderer;
        } else {
            System.out.println(orderersPages.errorBody().string());
            return null;
        }

    }


    /**
     * 通过league的id获取orderer信息
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseOrderer getOrdererByLeagueIdPageable(
            String authorization, Integer id, Integer page, Integer size) throws Exception {

        Call<PageableResponseOrderer> getOrdererByLeagueId = userAPI.getOrdererByLeagueIdPageable(authorization, id, page, size);
        Response<PageableResponseOrderer> leaguesOrderersPages = getOrdererByLeagueId.execute();

        if (leaguesOrderersPages.code() == 200) {
            PageableResponseOrderer orderersPages = leaguesOrderersPages.body();
            System.out.println("获取leagues的orderer成功");
            return orderersPages;
        } else {
            System.out.println(leaguesOrderersPages.errorBody().string());
            return null;
        }
    }


    /**
     * 获取组织信息成功
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseOrg getOrgPageable(
            String authorization, Integer page, Integer size) throws Exception {

        Call<PageableResponseOrg> getOrgPageable = userAPI.getOrgPageable(authorization, page, size);
        Response<PageableResponseOrg> getOrgsPages = getOrgPageable.execute();

        if (getOrgsPages.code() == 200) {
            PageableResponseOrg orgsPages = getOrgsPages.body();
            System.out.println("获取组织成功");
            return orgsPages;
        } else {
            System.out.println(getOrgsPages.errorBody().string());
            return null;
        }
    }


    /**
     * 通过leagues的id获取Org
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseOrg getOrgByLeagueIdPageable(
            String authorization, Integer id, Integer page, Integer size) throws Exception {
        Call<PageableResponseOrg> getOrgByLeagueIdPageable = userAPI.getOrgByLeagueIdPageable(authorization, id, page, size);
        Response<PageableResponseOrg> orgsPagesOfLeague = getOrgByLeagueIdPageable.execute();

        if (orgsPagesOfLeague.code() == 200) {
            PageableResponseOrg orgsPages = orgsPagesOfLeague.body();
            System.out.println("获取相应leagues的组织成功");
            return orgsPages;
        } else {
            System.out.println(orgsPagesOfLeague.errorBody().string());
            return null;
        }
    }


    /**
     * 获取peers信息
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponsePeer getPeersPageable(
            String authorization, Integer page, Integer size) throws Exception {

        Call<PageableResponsePeer> getPeersPageable = userAPI.getPeersPageable(authorization, page, size);
        Response<PageableResponsePeer> peersPages = getPeersPageable.execute();

        if (peersPages.code() == 200) {
            PageableResponsePeer peersPage = peersPages.body();
            System.out.println("获取peer信息成功");
            return peersPage;
        } else {
            System.out.println(peersPages.errorBody().string());
            return null;
        }
    }


    /**
     * 获取交易信息
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseTransaction getTransactionsPageable(
            String authorization, Integer page, Integer size) throws Exception {

        Call<PageableResponseTransaction> getTransactionsPageable = userAPI.getTransactionsPageable(authorization, page, size);
        Response<PageableResponseTransaction> transactionsPages = getTransactionsPageable.execute();

        if (transactionsPages.code() == 200) {
            PageableResponseTransaction transactionPage = transactionsPages.body();
            System.out.println("获取交易信息成功");
            return transactionPage;
        } else {
            System.out.println(transactionsPages.errorBody().string());
            return null;
        }

    }


    /**
     * 获取倒序交易信息
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseTransaction getDescTransactionsPageable(
            String authorization, Integer page, Integer size) throws Exception {

        Call<PageableResponseTransaction> getTransactionsPageable = userAPI.getDescTransactionPageable(authorization, page, size);
        Response<PageableResponseTransaction> transactionsPages = getTransactionsPageable.execute();

        if (transactionsPages.code() == 200) {
            PageableResponseTransaction transactionPage = transactionsPages.body();
            System.out.println("获取倒序交易信息成功");
            return transactionPage;
        } else {
            System.out.println(transactionsPages.errorBody().string());
            return null;
        }

    }



    /**
     * 获取区块的交易
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseTransaction getTransactionPageableByBlock(
            String authorization, Integer id, Integer page, Integer size) throws Exception {

        Call<PageableResponseTransaction> getTransactionPageableByBlock = userAPI.getTransactionPageableByBlock(authorization, id, page, size);
        Response<PageableResponseTransaction> transactionPageOfBlock = getTransactionPageableByBlock.execute();

        if (transactionPageOfBlock.code() == 200) {
            PageableResponseTransaction transactionPagesOfBlock = transactionPageOfBlock.body();
            System.out.println("获取区块交易信息成功");
            return transactionPagesOfBlock;
        } else {
            System.out.println(transactionPageOfBlock.errorBody().string());
            return null;
        }
    }


    /**
     * 获取倒序的区块交易信息
     * @param authorization
     * @param id
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseTransaction getDescTransactionPageableByBlock(
            String authorization, Integer id, Integer page, Integer size) throws Exception {

        Call<PageableResponseTransaction> getTransactionPageableByBlock = userAPI.getDescTransactionPageableByBlock(authorization, id, page, size);
        Response<PageableResponseTransaction> transactionPageOfBlock = getTransactionPageableByBlock.execute();

        if (transactionPageOfBlock.code() == 200) {
            PageableResponseTransaction transactionPagesOfBlock = transactionPageOfBlock.body();
            System.out.println("获取倒序区块交易信息成功");
            return transactionPagesOfBlock;
        } else {
            System.out.println(transactionPageOfBlock.errorBody().string());
            return null;
        }
    }

    /**
     * 获取交易最近五天折线图数据
     * @param authorization
     * @return
     */
    public static LineChartDataResponse<Date> getTransactionsLastFiveDays(String authorization) throws Exception {
    	Call<LineChartDataResponse<Date>> getTransactionsLastFiveDays = userAPI.getTransactionsLastFiveDays(authorization);
        Response<LineChartDataResponse<Date>> response = getTransactionsLastFiveDays.execute();
        
        if(response.code()==200) {
        	LineChartDataResponse<Date> lineChartData = response.body();
        	System.out.println("获取交易最近五天折线图数据成功");
        	return lineChartData;
        }else {
        	System.out.println(response.errorBody().string());
            return null;
        }   	
    }

    /**
     * 获取Channel的list对象
     * @param authorization
     * @return
     * @throws Exception
     */
    public static List<Channel> getChannelList(
            String authorization) throws Exception {

        Call<List<Channel>> channelList = userAPI.getChannelList(authorization);
        Response<List<Channel>> channelsList = channelList.execute();

        if (channelsList.code() == 200) {
            List<Channel> list = channelsList.body();
            System.out.println("获取通道信息成功");
            return list;
        } else {
            System.out.println(channelsList.errorBody().string());
            return null;
        }
    }


    /**
     * 获取channel分页信息
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseChannel getChannelPageable(
            String authorization, Integer page, Integer size) throws Exception {
        Call<PageableResponseChannel> getChannelPage = userAPI.getPageableChannel(authorization, page, size);
        Response<PageableResponseChannel> channelPage = getChannelPage.execute();

        if (channelPage.code() == 200) {
            PageableResponseChannel channel = channelPage.body();
            System.out.println("获取通道分页信息成功");
            return channel;
        } else {
            System.out.println(channelPage.errorBody().string());
            return null;
        }
    }

    /**
     * 获取读写集信息
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseRWSet getRWSetsPageable(
            String authorization, Integer page, Integer size) throws Exception {

        Call<PageableResponseRWSet> getRWSetsPageable = userAPI.getRWSetsPageable(authorization, page, size);
        Response<PageableResponseRWSet> rWSetPages = getRWSetsPageable.execute();

        if (rWSetPages.code() == 200) {
        	PageableResponseRWSet rWSetPage = rWSetPages.body();
            System.out.println("获取读写集信息成功");
            return rWSetPage;
        } else {
            System.out.println(rWSetPages.errorBody().string());
            return null;
        }

    }


    /**
     * 获取倒序读写集信息
     * @param authorization
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public static PageableResponseRWSet getDescRWSetsPageable(
            String authorization, Integer page, Integer size) throws Exception {

        Call<PageableResponseRWSet> getRWSetsPageable = userAPI.getDescRWSetsPageable(authorization, page, size);
        Response<PageableResponseRWSet> rWSetPages = getRWSetsPageable.execute();

        if (rWSetPages.code() == 200) {
        	PageableResponseRWSet rWSetPage = rWSetPages.body();
            System.out.println("获取倒序读写集信息成功");
            return rWSetPage;
        } else {
            System.out.println(rWSetPages.errorBody().string());
            return null;
        }

    }

    /**
     * 获取读写集最近五天折线图数据
     * @param authorization
     * @return
     */
    public static LineChartDataResponse<Date> getRWSetsLastFiveDays(String authorization) throws Exception {
    	Call<LineChartDataResponse<Date>> getRWSetsLastFiveDays = userAPI.getRWSetsLastFiveDays(authorization);
        Response<LineChartDataResponse<Date>> response = getRWSetsLastFiveDays.execute();
        
        if(response.code()==200) {
        	LineChartDataResponse<Date> lineChartData = response.body();
        	System.out.println("获取读写集最近五天折线图数据成功");
        	return lineChartData;
        }else {
        	System.out.println(response.errorBody().string());
            return null;
        }   	
    }

    /**
     * 获取当天区块
     * @param authorization
     * @return
     */
    public static List<Block> getCurrentDayBlocks(String authorization) throws Exception{
    	Call<List<Block>> getCurrentDayBlocks = userAPI.getCurrentDayBlocks(authorization);
        Response<List<Block>> response = getCurrentDayBlocks.execute();
        
        if(response.code()==200) {
        	List<Block> blockList = response.body();
        	System.out.println("获取当天区块数据成功");
        	return blockList;
        }else {
        	System.out.println(response.errorBody().string());
            return null;
        }   	
    	
    }
    
    public static PageableResponseChaincode getChaincodePageable(String authorization, Integer page, Integer size) throws Exception {
        Call<PageableResponseChaincode> getChaincodePageable = userAPI.getPageableChaincode(authorization, page, size);
        Response<PageableResponseChaincode> chaincodePages = getChaincodePageable.execute();

        if (chaincodePages.code() == 200) {
        	PageableResponseChaincode chaincodePage = chaincodePages.body();
            System.out.println("获取读写集信息成功");
            return chaincodePage;
        } else {
            System.out.println(chaincodePages.errorBody().string());
            return null;
        }
    }


}
