package com.example.enspaender.vkhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.enspaender.vkhackathon.BuildingMaps.BuildingNavigation;
import com.example.enspaender.vkhackathon.CreateRoute.CreateRouteActivity;
import com.example.enspaender.vkhackathon.NewsScreen.NewsActivity;

/**
 * Created by enspaender on 22.10.17.
 */

public class MainScreen extends AppCompatActivity {
  private static final String TAG = MainScreen.class.getSimpleName();

  @BindView(R.id.news) Button news;
  @BindView(R.id.my_routes) Button myRoutes;
  @BindView(R.id.base_map) Button map;
  @BindView(R.id.create_route) Button createRoute;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_screen);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.news)
  public void clickNews(){
    startActivity(new Intent(this, NewsActivity.class));
  }

  @OnClick(R.id.my_routes)
  public void clickMyRoutes(){
    Toast.makeText(this, "my routes click", Toast.LENGTH_SHORT).show();
  }

  @OnClick(R.id.base_map)
  public void clickBaseMap(){
    Intent intent = new Intent(this, BuildingNavigation.class);
    intent.putExtra("isMap",true);
    startActivity(intent);
  }

  @OnClick(R.id.create_route)
  public void clickCreateRoute(){
    startActivity(new Intent(this, CreateRouteActivity.class));
  }
}
