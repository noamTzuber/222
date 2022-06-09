package com.example.loginactivity.API;


import com.example.loginactivity.MyApplication;
import com.example.loginactivity.R;
import com.example.loginactivity.myObjects.User;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {

    Retrofit retrofit;
    UserWebServiceAPI userWebServiceAPI;


    public UserAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userWebServiceAPI = retrofit.create(UserWebServiceAPI.class);
    }

    public UserWebServiceAPI getUserWebServiceAPI(){
        return userWebServiceAPI;
    }

}