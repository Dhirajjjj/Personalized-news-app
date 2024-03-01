package com.example.thyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.example.thyme.Model.Articles;
import com.example.thyme.Model.business;
import com.example.thyme.Model.headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class businessDet extends AppCompatActivity {


    RecyclerView recyclerView;
    final String API_KEY = "616c194db4fd4bdfabcce78f86cad205";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_det);


        recyclerView = findViewById(R.id.recyclerViewBusiness);
        swipeRefreshLayout = findViewById(R.id.swipeRefershBusiness);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final String country = getCountry();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson(country,API_KEY);
            }
        });

        retrieveJson(country,API_KEY);


    }


    public void retrieveJson(String country, String apiKey) {

        swipeRefreshLayout.setRefreshing(true);

        Call<business> call = ApiClient.getInstance().getApi().getbusiness(country,apiKey);
        call.enqueue(new Callback<business>() {
            @Override
            public void onResponse(Call<business> call, Response<business> response) {
                if(response.isSuccessful() && response.body().getArticles() != null) {
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(businessDet.this,articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<business> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(businessDet.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public String getCountry() {

        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }


}