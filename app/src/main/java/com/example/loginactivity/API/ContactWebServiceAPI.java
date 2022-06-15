package com.example.loginactivity.API;

import com.example.loginactivity.myObjects.Contact;
import com.example.loginactivity.myObjects.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ContactWebServiceAPI {
    @POST("contacts/")
    Call<Void> postContact(@Body Contact contact,
                             @Query("connectedId") String name);

    @GET("contacts/")
    Call<List<Contact>> getContacts(@Query("connectedId") String name);

    @POST("contacts/SetToken")
    Call<Void> postToken(@Body TokenToId token);
}
