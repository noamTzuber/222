package com.example.loginactivity.API;

import com.example.loginactivity.MyApplication;
import com.example.loginactivity.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {
    Retrofit retrofit;
    MessageWebServiceAPI messageWebServiceAPI;

    public MessageAPI(String server) {
        String s=server.replace("localhost","10.0.2.2");
        String serverC="http://"+s+"/api/";

        retrofit = new Retrofit.Builder()
                .baseUrl(serverC)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        messageWebServiceAPI = retrofit.create(MessageWebServiceAPI.class);
    }

    public  MessageWebServiceAPI getMessageWebServiceAPI() {
        return messageWebServiceAPI;
    }
}
