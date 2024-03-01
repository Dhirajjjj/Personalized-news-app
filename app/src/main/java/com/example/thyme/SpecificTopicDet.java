package com.example.thyme;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.thyme.Model.Articles;
import com.example.thyme.Model.Recommended;
import com.example.thyme.Model.SpecificTopic;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecificTopicDet extends AppCompatActivity {

    RecyclerView recyclerView;
    final String API_KEY = "<API_KEY>";
    Adapter adapter;
    List<Articles> articles = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    String query = "coronavirus";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifictopic_det);


        recyclerView = findViewById(R.id.recyclerViewSpecificTopic);
        swipeRefreshLayout = findViewById(R.id.swipeRefershSpecificTopic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final String country = getCountry();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson(query,API_KEY);
            }
        });

        retrieveJson(query,API_KEY);


    }


    public void retrieveJson(String country, String apiKey) {

        swipeRefreshLayout.setRefreshing(true);



        Call<SpecificTopic> call = ApiClient.getInstance().getApi().getSpecificTopic(query,apiKey);
        call.enqueue(new Callback<SpecificTopic>() {
            @Override
            public void onResponse(Call<SpecificTopic> call, Response<SpecificTopic> response) {
                if(response.isSuccessful() && response.body().getArticles() != null) {
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(SpecificTopicDet.this,articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<SpecificTopic> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(SpecificTopicDet.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public String getCountry() {

        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();

    }

}

