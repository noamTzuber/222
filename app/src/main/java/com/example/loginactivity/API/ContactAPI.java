package com.example.loginactivity.API;

import androidx.room.Room;

import com.example.loginactivity.AppDBIdUser;
import com.example.loginactivity.IdUserDao;
import com.example.loginactivity.MyApplication;
import com.example.loginactivity.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {
    UserAPI dbUser;
    IdUserDao idUserDao;
    Retrofit retrofit;
    ContactWebServiceAPI contactWebServiceAPI;

    public ContactAPI(String server) {

        String s=server.replace("localhost","10.0.2.2");
        String currentServer="http://"+s+"/api/";
        retrofit = new Retrofit.Builder()
                .baseUrl(currentServer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        contactWebServiceAPI = retrofit.create(ContactWebServiceAPI.class);
    }
    public  ContactWebServiceAPI getContactWebServiceAPI() {
        return contactWebServiceAPI;
    }
}
