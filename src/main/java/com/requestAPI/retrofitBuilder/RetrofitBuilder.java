package com.requestAPI.retrofitBuilder;

import com.converterFactory.NullOnEmptyConverterFactory;
import com.requestAPI.retrofitBuilder.SSL.SslContextFactory;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class RetrofitBuilder {

    private static volatile Retrofit retrofit = null;

    private RetrofitBuilder() {

    }

    public static Retrofit getRetrofit() throws Exception {
        if (retrofit == null) {
            synchronized (RetrofitBuilder.class) {
                if (retrofit == null) {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .certificatePinner(new CertificatePinner.Builder()
//                                    .add("125.216.243.88:8843", "sha256/25QSkA5xbdlPRrAtnPgwIEiQIGJTYo9eYUZbD6BwbUA=")
                            		.add("127.0.0.1:8843", "sha256/25QSkA5xbdlPRrAtnPgwIEiQIGJTYo9eYUZbD6BwbUA=")
                                    .build())
                            .sslSocketFactory(SslContextFactory.getSSLSocketFactory())
                            .hostnameVerifier(new HostnameVerifier() {
                                @Override
                                public boolean verify(String s, SSLSession sslSession) {
                                    return true;
                                }
                            })
                            .build();

                    retrofit = new Retrofit.Builder()
//                            .baseUrl("https://125.216.243.88:8843")
                    		.baseUrl("https://127.0.0.1:8843")
                            .addConverterFactory(new NullOnEmptyConverterFactory())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
                }
            }
        }
        return retrofit;
    }
}
