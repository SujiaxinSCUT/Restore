package com.requestAPI.retrofitBuilder.SSL;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

public class SslContextFactory {

        public static SSLSocketFactory getSSLSocketFactory() throws Exception {

            SSLContext sslContext = null;

            try {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                InputStream certificate = new FileInputStream(new File("tomcat.keystore"));
                keyStore.load(certificate, "somnus".toCharArray());

                sslContext = SSLContext.getInstance("TLS");
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            }catch (Exception e) {
                e.printStackTrace();
            }

            return sslContext.getSocketFactory();
        }

}
