package com.MohammadVarrelBramastaJSleepDN.jsleep_android.request;

import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Account;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BaseApiService {
    @GET("account/{id}")
    Call<Account> getAccount(@Path("id") int id);

    Call<Account> login(String toString, String toString1);

    Call<Account> register(String toString, String toString1, String toString2);
}
