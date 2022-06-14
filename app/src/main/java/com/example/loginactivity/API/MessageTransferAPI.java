package com.example.loginactivity.API;

import com.example.loginactivity.MyApplication;
import com.example.loginactivity.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageTransferAPI {

        Retrofit retrofit;
        MessageTransferWebServiceAPI messageTransferWebServiceAPI;

        public MessageTransferAPI(String server) {
            String url="http://"+server+"/api/transfer/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            messageTransferWebServiceAPI = retrofit.create(MessageTransferWebServiceAPI.class);
        }

        public  MessageTransferWebServiceAPI getMessageTransferServiceAPI() {
            return messageTransferWebServiceAPI;
        }
    }
