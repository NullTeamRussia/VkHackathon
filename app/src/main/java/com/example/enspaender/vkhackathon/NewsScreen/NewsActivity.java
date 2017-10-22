package com.example.enspaender.vkhackathon.NewsScreen;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.enspaender.vkhackathon.Network.Models.News.NewsModel;
import com.example.enspaender.vkhackathon.Network.Models.News.NewsResponse;
import com.example.enspaender.vkhackathon.Network.Retrofit2.Provider;
import com.example.enspaender.vkhackathon.R;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enspaender on 21.10.17.
 */

public class NewsActivity extends AppCompatActivity
    implements NewsAdapter.OnNewsClickListener{
  private static final String TAG = NewsActivity.class.getSimpleName();

  @BindView(R.id.news_recycler) RecyclerView newsRecycler;
  @BindView(R.id.news_refresher) SwipeRefreshLayout newsRefresher;

  private NewsAdapter adapter;

  private String fcmToken;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news);
    ButterKnife.bind(this);

    fcmToken = FirebaseInstanceId.getInstance().getToken();
    Log.e(TAG,fcmToken);


    newsRefresher.setOnRefreshListener(() -> {
      newsRefresher.setRefreshing(true);
      checkUpdate();
    });

    newsRecycler.setLayoutManager(new LinearLayoutManager(this));
    newsRecycler.setItemAnimator(new DefaultItemAnimator());

    checkUpdate();
  }

  private List<NewsModel> news;
  private void checkUpdate() {
    // TODO: 21.10.17 getActualInfo from server
    Provider.provideDataRepository().getNews(5000)
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<NewsResponse>() {
          @Override public void onCompleted() {
            runOnUiThread(() -> newsRefresher.setRefreshing(false));
            showInAdapter(news);
          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(NewsResponse newsResponse) {
            news = newsResponse.getNewsObject().getNews();
          }
        });
  }

  private void initFakeNews() {
    List<NewsModel> newsList = new ArrayList<>();
    newsList.add(new NewsModel(0,"TestTitle","TestDescription","test url","test type"));
    newsList.add(new NewsModel(1,"TestTitle1","TestDescription","test url","test type"));
    newsList.add(new NewsModel(2,"TestTitle2","TestDescription","test url","test type"));
    newsList.add(new NewsModel(3,"TestTitle3","TestDescription","test url","test type"));
    newsList.add(new NewsModel(4,"TestTitle4","TestDescription","test url","test type"));
    newsList.add(new NewsModel(5,"TestTitle5","TestDescription","test url","test type"));
    newsList.add(new NewsModel(6,"TestTitle6","TestDescription","test url","test type"));

    showInAdapter(newsList);
  }

  private void showInAdapter(List<NewsModel> newsList){
    runOnUiThread(()->{
      adapter = new NewsAdapter(newsList,this.getLayoutInflater(),this);
      newsRecycler.setAdapter(adapter);
    });
  }

  @Override public void onNewsClick(NewsModel news) {
    Toast.makeText(this, news.getTitle(), Toast.LENGTH_SHORT).show();
  }
}
