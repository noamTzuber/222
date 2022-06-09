package com.example.loginactivity.API;

import com.example.loginactivity.myObjects.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ContactWebServiceAPI {

    @GET("/contacts")
    Call<List<Contact>> getAllContacts(@Query("connectedId") String name);

    @GET("contacts/{id}")
    Call<Void> getSingleContact(@Body Contact contact);

    @POST("contacts/{id}")
    Call<Void> createContact(@Body Contact contact,@Query("connectedId") String name);

}
