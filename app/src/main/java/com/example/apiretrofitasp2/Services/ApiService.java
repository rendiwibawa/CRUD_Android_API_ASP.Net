package com.example.apiretrofitasp2.Services;


import com.example.apiretrofitasp2.Entities.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {

    @GET("findall")
    Call<List<Model>> findall();

    @GET("find/{id}")
    Call<List<Model>> find(@Query("id") int id);

    @POST("create")
    Call<Void> create(@Body Model model);

    @PUT("update")
    Call<Void> update(@Body Model model);

    @DELETE("delete/{id}")
    Call<Void> delete(@Query("id") int id);




}
