package com.example.loginactivity.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageTransferWebServiceAPI {
    @POST("transfer")
    Call<Void> transferMessage(@Body MessageToTransfer message);
}
