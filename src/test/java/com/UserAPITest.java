package com;

import com.pojo.*;
import com.pojo.responsePOJO.*;
import com.requestAPI.Impl.UserAPIImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class UserAPITest {

    private static String authorization = "Bearer da5f1b21-12ed-4fd3-b81a-9529f2811839";

    @Test
    public void getPeer() throws Exception {
        PageableResponsePeer peersPageable = UserAPIImpl.getPeersPageable(authorization, 0, 5);
        Set<Peer> contents = peersPageable.getContents();
        System.out.println("==================节点信息测试===================");
        for (Peer p : contents) {        	
            System.out.println(p.getEventhub_location());
            System.out.println(p.getLocation());
            System.out.println(p.getName());
            System.out.println(p.getOrg());
            String[] s1 = p.getName().split("\\.");
            for (String s : s1) {
                System.out.println(s);
            }
            System.out.println(p.getId());
            System.out.println(p.getEventhub_name());
        }
    }

    @Test
    public void getTX() throws Exception {
        PageableResponseTransaction descTransactionsPageable = UserAPIImpl.getDescTransactionsPageable(authorization, 2, 100);
        System.out.println("==================交易信息测试===================");
        System.out.println(descTransactionsPageable.getTotal_elements());
        System.out.println(descTransactionsPageable.getPage());
        Set<Transaction> contents = descTransactionsPageable.getContents();
        for (Transaction t : contents) {
            System.out.println(t.getCreate_mspid());
        }

    }

    @Test
    public void getDescBlockByChannel() throws IOException {
        PageableResponseBlock descBlocksByChannelIdPageable = UserAPIImpl.getDescBlocksByChannelIdPageable(authorization, 1, 0, 5);
        Set<Block> contents = descBlocksByChannelIdPageable.getContents();
        System.out.println("==================区块信息测试1===================");
        for (Block b : contents) {
            System.out.println(b.getBlockNumber());
        }
    }

    @Test
    public void getChannel() throws Exception{
        List<Channel> channelList = UserAPIImpl.getChannelList(authorization);
        System.out.println("==================通道信息测试===================");
        for (Channel channel : channelList) {
            System.out.println(channel.getBlock_height());
            System.out.println(channel.getId());
            System.out.println(channel.getName());
            System.out.println(channel.getLeague().getName());
        }
    }

    @Test
    public void getDescBlock() throws Exception{
        PageableResponseBlock descBlocksPageable = UserAPIImpl.getDescBlocksPageable(authorization, 0, 10);
        Set<Block> contents = descBlocksPageable.getContents();
        System.out.println("==================倒序区块信息测试===================");
        for (Block block : contents) {
            System.out.println(block.getPreviousHash());
            System.out.println(block.getBlockNumber());
            System.out.println(block.getChannel().getName());
        }
    }


    @Test
    public void getAccount() throws Exception {
        AccountResponse userAccountInfo = UserAPIImpl.getUserAccountInfo(authorization);
        System.out.println("==================账号信息测试===================");
        System.out.println(userAccountInfo.isVerified());
        String[] permissions = userAccountInfo.getPermissions();
        for (String s : permissions)
            System.out.println(s);
    }

    @Test
    public void signupAndupdate() throws IOException {
        String s = UserAPIImpl.signUp("tancc", "tancc");
        System.out.println(s);

//        String eee = UserAPIImpl.updateAccountInfo(authorization, "tancc", "111");
//        System.out.println(eee);
    }

    @Test
    public void getLeagues() throws IOException {

        PageableResponseLeague leaguesPageable = UserAPIImpl.getLeaguesPageable(authorization, 0, 10);
        System.out.println("==================联盟信息测试===================");
        System.out.println(leaguesPageable.getTotal_pages());
        System.out.println(leaguesPageable.getTotal_elements());
        //League[] contents = leaguesPageable.getContents();
        Set<League> contents = leaguesPageable.getContents();
        for(League l : contents){
            System.out.println(l.getName());
            System.out.println(l.getDate());
            System.out.println(l.getId());
        }
    }

    @Test
    public void getBlocks() throws IOException {
        PageableResponseBlock blocksPageable = UserAPIImpl.getBlocksPageable(authorization, 0, 5);
        System.out.println("==================区块信息测试2===================");
        System.out.println(blocksPageable.getPage());
        System.out.println(blocksPageable.getSize());
        System.out.println(blocksPageable.getTotal_elements());
        System.out.println(blocksPageable.getContents());

        Set<Block> contents = blocksPageable.getContents();
        for(Block b: contents) {
            System.out.println(b.getId());
            System.out.println(b.getTxCount());
            System.out.println(b.getBlockNumber());
            System.out.println(b.getBlockHash());
            System.out.println(b.getPreviousHash());
        }
    }

    @Test
    public void getBlockByChannel() throws IOException {
        PageableResponseBlock blocksByChannelIdPageable = UserAPIImpl.getBlocksByChannelIdPageable(authorization, 1, 0, 2);
        System.out.println("==================区块信息测试3===================");
        System.out.println(blocksByChannelIdPageable.getPage());
        System.out.println(blocksByChannelIdPageable.getSize());
        System.out.println(blocksByChannelIdPageable.getTotal_elements());
        System.out.println(blocksByChannelIdPageable.getContents());

        Set<Block> contents = blocksByChannelIdPageable.getContents();
        for(Block b: contents) {
            System.out.println(b.getId());
            System.out.println(b.getTxCount());
            System.out.println(b.getBlockNumber());
            System.out.println(b.getBlockHash());
            System.out.println(b.getPreviousHash());
        }
    }

    @Test
    public void getOrderer() throws Exception {
        PageableResponseOrderer ordererPageable = UserAPIImpl.getOrdererPageable(authorization, 0, 5);

        System.out.println(ordererPageable.getTotal_elements());
        Set<Orderer> contents = ordererPageable.getContents();

        for (Orderer o : contents) {
            System.out.println(o.getDomain_suffix());
            System.out.println(o.getName());
        }
    }

    @Test
    public void getOrdererByLeagueId() throws Exception{

        PageableResponseOrderer ordererByLeagueIdPageable = UserAPIImpl.getOrdererByLeagueIdPageable(authorization, 1, 0, 5);
        Set<Orderer> contents = ordererByLeagueIdPageable.getContents();
        System.out.println("==================排序节点信息测试===================");
        for (Orderer o : contents) {
            System.out.println(o.getDomain_suffix());
            System.out.println(o.getName());
        }
    }

    @Test
    public void getOrg() throws Exception{
        PageableResponseOrg orgPageable = UserAPIImpl.getOrgPageable(authorization, 0, 5);
        Set<Org> contents = orgPageable.getContents();
        System.out.println("==================组织信息测试1===================");
        for (Org or : contents) {

        }
    }

    @Test
    public void getOrgByLeague() throws Exception {
        PageableResponseOrg orgByLeagueIdPageable = UserAPIImpl.getOrgByLeagueIdPageable(authorization, 1, 0, 5);
        Set<Org> contents = orgByLeagueIdPageable.getContents();
        System.out.println("==================组织信息测试2===================");
        for (Org or : contents) {
            System.out.println(or.getId());
            System.out.println(or.getMsp_id());
            System.out.println(or.getOrg_admin());

            Set<Ca> cas = or.getCas();
            for (Ca ca : cas) {
                System.out.println(ca.getName());
            }
        }
    }


}
