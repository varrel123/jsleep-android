package com.MohammadVarrelBramastaJSleepDN.jsleep_android.request;

import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Account;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.BedType;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.City;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Facility;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Renter;
import com.MohammadVarrelBramastaJSleepDN.jsleep_android.model.Room;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {

    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int Id);
    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int Id);
    @GET("renter/{id}")
    Call<Renter> getRenter (@Path("id") int Id);
    @POST("account/register")
    Call<Account> register(@Query("name") String name, @Query("email") String email, @Query("password") String password);
    @POST("account/login")
    Call<Account> login(@Query("email") String email, @Query("password") String password);
    @POST("account/{id}/registerRenter")
    Call<Renter> registerRenter(@Path("id") int id,
                                @Query("username") String username,
                                @Query("address") String address,
                                @Query("phoneNumber") String phoneNumber);
    @GET("room/getAllRoom")
    Call<List<Room>> getAllRoom (@Query("page") int page,
                                 @Query("pageSize")int pageSize);

    @POST("account/{id}/topUp")
    Call<Account> topUp(@Path("id") int id, @Query("balance") int balance);

    @GET("room/create")
    Call<List<Room>> create(@Query("accountId") Account Accountid,
                            @Query("name") String name,
                            @Query("size") int size,
                            @Query("price") double price,
                            @Query("facility") ArrayList<Facility> facility,
                            @Query("city") City city,
                            @Query("address") String address,
                            @Query("bedType") BedType bedType);
}
