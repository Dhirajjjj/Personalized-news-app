package com.example.thyme;

import com.example.thyme.Model.Recommended;
import com.example.thyme.Model.SpecificTopic;
import com.example.thyme.Model.business;
import com.example.thyme.Model.entertainment;
import com.example.thyme.Model.headlines;
import com.example.thyme.Model.health;
import com.example.thyme.Model.science;
import com.example.thyme.Model.sports;
import com.example.thyme.Model.technology;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<headlines> getheadlines(
          @Query("country") String country,
          @Query("apiKey") String apiKey
    );

    @GET("top-headlines?category=business")
    Call<business> getbusiness(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines?category=science")
    Call<science> getscience(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines?category=sports")
    Call<sports> getsports(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines?category=health")
    Call<health> gethealth(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines?category=technology")
    Call<technology> gettechnology(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines?category=entertainment")
    Call<entertainment> getentertainment(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<headlines> getSpecificData(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines?category=general&health")
    Call<Recommended> getRecommended(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<SpecificTopic> getSpecificTopic(
            @Query("q") String q,
            @Query("apiKey") String apiKey
    );

}
