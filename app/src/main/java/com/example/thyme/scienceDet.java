package com.example.thyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.example.thyme.Model.Articles;
import com.example.thyme.Model.business;
import com.example.thyme.Model.science;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class scienceDet extends AppCompatActivity {


    RecyclerView recyclerView;
    final String API_KEY = "<API_KEY>";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_det);

        recyclerView = findViewById(R.id.recyclerViewScience);
        swipeRefreshLayout = findViewById(R.id.swipeRefershScience);
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

        Call<science> call = ApiClient.getInstance().getApi().getscience(country,apiKey);
        call.enqueue(new Callback<science>() {
            @Override
            public void onResponse(Call<science> call, Response<science> response) {
                if(response.isSuccessful() && response.body().getArticles() != null) {
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(scienceDet.this,articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<science> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(scienceDet.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public String getCountry() {

        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }



}
