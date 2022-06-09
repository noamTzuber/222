package com.example.loginactivity.API;

import com.example.loginactivity.myObjects.Contact;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface InvitationsWebServiceAPI {
    @POST("api/invitations/")
    Call<Void> inviteContact(@Body InvitationsMessage invitationsMessage,
                             @Query("connectedId") String name);

}
