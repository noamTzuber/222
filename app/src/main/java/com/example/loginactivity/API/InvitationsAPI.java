package com.example.loginactivity.API;


import com.example.loginactivity.MyApplication;
import com.example.loginactivity.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvitationsAPI {

    Retrofit retrofit;
    InvitationsWebServiceAPI invitationsWebServiceAPI;

    public InvitationsAPI(String server) {
        String url="http://"+server+"/";
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        invitationsWebServiceAPI = retrofit.create(InvitationsWebServiceAPI.class);
    }
    public  InvitationsWebServiceAPI getInvitationsWebServiceAPI() {
        return invitationsWebServiceAPI;
    }
}
