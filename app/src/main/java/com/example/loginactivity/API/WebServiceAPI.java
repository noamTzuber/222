package com.example.loginactivity.API;


import com.example.loginactivity.myObjects.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

    @GET("contacts/AllUsers")
    Call<List<Contact>> getContacts();

    @POST("contacts")
    Call<Void> createContact(@Body Contact contact);

//    @GET("contacts/{id}")
//    Call<Contact> getContact(@Path("id") int id);

    @DELETE("contacts/{id}")
    Call<Void> deleteContact(@Path("id") int id);
}