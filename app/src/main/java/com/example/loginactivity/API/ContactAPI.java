package com.example.loginactivity.API;

import com.example.loginactivity.MyApplication;
import com.example.loginactivity.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {
    Retrofit retrofit;
    ContactWebServiceAPI contactWebServiceAPI;

    public ContactAPI() {

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        contactWebServiceAPI = retrofit.create(ContactWebServiceAPI.class);
    }
    public  ContactWebServiceAPI getContactWebServiceAPI() {
        return contactWebServiceAPI;
    }
}
