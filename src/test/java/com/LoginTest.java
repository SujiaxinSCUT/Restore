package com;

import com.pojo.responsePOJO.LoginResponse;
import com.requestAPI.Impl.LoginAPIImpl;
import org.junit.Test;

public class LoginTest {

    @Test
    public void login() throws Exception {
        LoginResponse login = LoginAPIImpl.login("fabric", "111");
        System.out.println(login.getAccess_token());
        System.out.println(login.getScope());
        System.out.println(login.getRefresh_token());
    }

    @Test
    public void refreshToken() throws Exception {
        LoginResponse loginResponse = LoginAPIImpl.refreshToken("9f1de186-c2e4-4ade-bd02-9676f5c81048", "read");
        System.out.println(loginResponse.getAccess_token());
    }
}
