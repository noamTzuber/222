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
    public List<User> lastUserList;

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

//    public void get() {
//        Call<List<User>> call = userWebServiceAPI.getUsers();
//        call.enqueue(new Callback<List<User>>() {
//            @Override
//            public void onResponse( Call<List<User>> call, Response<List<User>> response) {
//                List<User> users = response.body();
//                lastUserList = users;
//
//            }
//            @Override
//            public void onFailure( Call<List<User>> call,  Throwable t) {
//            }
//        });
//    }
}