package com.example.thyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.example.thyme.Model.Articles;
import com.example.thyme.Model.business;
import com.example.thyme.Model.entertainment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class entertainmentDet extends AppCompatActivity {

    RecyclerView recyclerView;
    final String API_KEY = "616c194db4fd4bdfabcce78f86cad205";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment_det);

        recyclerView = findViewById(R.id.recyclerViewEntertainment);
        swipeRefreshLayout = findViewById(R.id.swipeRefershEntertainment);
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

        Call<entertainment> call = ApiClient.getInstance().getApi().getentertainment(country,apiKey);
        call.enqueue(new Callback<entertainment>() {
            @Override
            public void onResponse(Call<entertainment> call, Response<entertainment> response) {
                if(response.isSuccessful() && response.body().getArticles() != null) {
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(entertainmentDet.this,articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<entertainment> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(entertainmentDet.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public String getCountry() {

        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }


}