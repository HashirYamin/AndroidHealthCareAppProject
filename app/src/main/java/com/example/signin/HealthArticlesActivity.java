package com.example.signin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HealthArticlesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Article> articleList;
    NewsRecyclerAdapter adapter;
    LinearProgressIndicator progressIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        articleList = new ArrayList<>();

        recyclerView = findViewById(R.id.news_recycler_view);
        progressIndicator = findViewById(R.id.progress_bar_health);

        setupRecyclerView();
        getNews();

    }

    void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsRecyclerAdapter(articleList);
        recyclerView.setAdapter(adapter);
    }

    void changeInProgress(boolean show){
        if(show)
            progressIndicator.setVisibility(View.VISIBLE);
        else
            progressIndicator.setVisibility(View.INVISIBLE);
    }

    void getNews(){
        changeInProgress(true);
        NewsApiClient newsApiClient = new NewsApiClient("638e09c9d8df4924a82a40a77454fcae");
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        runOnUiThread(() -> {
                            changeInProgress(false); // Hide the progress indicator
                            articleList = response.getArticles();
                            if (articleList != null) {
                                adapter.updateData(articleList); // Update the RecyclerView with new data
                            }
                        });
                    }

//                    @Override
//                    public void onSuccess(ArticleResponse response) {
//                       runOnUiThread(()->{
//                           changeInProgress(false);
//                           articleList = response.getArticles();
//                           adapter.updateData(articleList);
//                           adapter.notifyDataSetChanged();
//                       });
//                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.i("GOT FAILURE",throwable.getMessage());
                    }
                }
        );
    }

}