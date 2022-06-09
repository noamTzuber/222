package com.example.loginactivity.API;


import com.example.loginactivity.myObjects.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserWebServiceAPI {

    @GET("contacts/AllUsers")
    Call<List<User>> getUsers();

    @POST("contacts/User/")
    Call<Void> createUser(@Body User user);
}