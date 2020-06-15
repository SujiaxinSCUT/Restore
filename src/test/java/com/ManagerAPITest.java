package com;

import com.pojo.requestPOJO.OrdererCreateRequest;
import com.pojo.requestPOJO.PeerCreateRequest;
import com.pojo.requestPOJO.UserCreateRequest;
import com.pojo.requestPOJO.UserManagerUpdateRequest;
import com.pojo.responsePOJO.AccountResponse;
import com.pojo.responsePOJO.PageableResponseAccountResponse;
import com.requestAPI.Impl.ManagerAPIImpl;
import org.junit.Test;

import java.util.Set;

public class ManagerAPITest {

    private static String authorization = "Bearer 801af342-9085-458f-8580-64b92fd97e14";

    @Test
    public void createUser() throws Exception{

        UserCreateRequest userCreateRequest = new UserCreateRequest();

        String res = ManagerAPIImpl.createUser(authorization, userCreateRequest);
        System.out.println(res);

        UserManagerUpdateRequest userManagerUpdateRequest = new UserManagerUpdateRequest();

        String s = ManagerAPIImpl.updateUser(authorization, userManagerUpdateRequest);
        System.out.println(s);
    }


    @Test
    public void getUserById() throws Exception {
        AccountResponse user = ManagerAPIImpl.getUserById(authorization, 1);
        System.out.println(user.getName());
        System.out.println(user.getPermissions()[0]);
    }

    @Test
    public void getUserByName() throws Exception {
        AccountResponse fabric = ManagerAPIImpl.getUserByName(authorization, "fabric");
        System.out.println(fabric.getId());
        System.out.println(fabric.getPermissions()[0]);
    }

    @Test
    public void getAccountsPageable() throws Exception {
        PageableResponseAccountResponse accountsPageable = ManagerAPIImpl.getAccountsPageable(authorization, 0, 5);
        Set<AccountResponse> contents = accountsPageable.getContents();

        for (AccountResponse a : contents) {
            System.out.println(a.getName());
            System.out.println(a.getDate());
        }
    }

    @Test
    public void addPeer() throws Exception {
        PeerCreateRequest peerCreateRequest = new PeerCreateRequest();
        peerCreateRequest.setOrg_id(1);
        peerCreateRequest.setEventhub_location("grpc://120.78.160.145:7053");
        peerCreateRequest.setEventhub_name("peer0.org1.example.com");
        peerCreateRequest.setLocation("grpc://120.78.160.145:7051");
        peerCreateRequest.setName("peer0.org1.example.com");

        String s = ManagerAPIImpl.addPeer(authorization, peerCreateRequest);
        System.out.println(s);
    }


    @Test
    public void addOrderer() throws Exception {

        OrdererCreateRequest ordererCreateRequest = new OrdererCreateRequest();
        ordererCreateRequest.setDomain_suffix("example.com");
        ordererCreateRequest.setLeague_id(1);
        ordererCreateRequest.setLocation("grpc://120.78.160.145:7050");
        ordererCreateRequest.setName("orderer.example.com");

        String s = ManagerAPIImpl.addOrderer(authorization, ordererCreateRequest);
        System.out.println(s);

    }
}
