package com.example.loginsignupver1;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @POST("/user/register")
    Call<User_Register> register(@Body User_Register user_register);

    @POST("user/login")
    Call<Login_Result> login(@Body User_Login user_login);


    @GET("/tour/list")
    Call<ListTour> getTour(
            @Query("rowPerPage") int row,
            @Query("pageNum") int numb,
            @Query("orderBy") String otherBy,
            @Query("isDesc") boolean isDesc,
            @HeaderMap Map<String,String> headers);


}
