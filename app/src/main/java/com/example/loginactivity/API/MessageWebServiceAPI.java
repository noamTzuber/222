package com.example.loginactivity.API;

import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.Message;
import com.example.loginactivity.myObjects.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MessageWebServiceAPI {
    @POST("contacts/{id}/messages")
    Call<Void> postMessage(@Body MessageForServer message, @Path("id") String id,
                           @Query("connectedId") String name);

    @GET("contacts/{id}/messages")
    Call<List<Message>> getMessages(@Path("id") String id,@Query("connectedId") String connectedId);

}
