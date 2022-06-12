package com.example.loginactivity.API;

import com.example.loginactivity.MyApplication;
import com.example.loginactivity.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {
    Retrofit retrofit;
    MessageWebServiceAPI messageWebServiceAPI;

    public MessageAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        messageWebServiceAPI = retrofit.create(MessageWebServiceAPI.class);
    }
    public  MessageWebServiceAPI getmessageWebServiceAPI() {
        return messageWebServiceAPI;
    }
}
