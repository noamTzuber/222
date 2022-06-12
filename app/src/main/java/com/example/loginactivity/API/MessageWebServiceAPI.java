package com.example.loginactivity.API;

import com.example.loginactivity.myObjects.Contact;
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
    Call<Void> postContact(@Body Contact contact, @Path("id") String id,
                           @Query("connectedId") String name);

    @GET("contacts/{id}/messages")
    Call<List<Contact>> getContacts(@Query("connectedId") String name,
                                    @Path("id") String id);
}
