package com.example.enspaender.vkhackathon.UsersRoutes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.enspaender.vkhackathon.Network.Models.Routes.ArrayObjectsModel;
import com.example.enspaender.vkhackathon.Network.Models.Routes.RoutesModel;
import com.example.enspaender.vkhackathon.Network.Models.Routes.RoutesResponse;
import com.example.enspaender.vkhackathon.Network.Retrofit2.Provider;
import com.example.enspaender.vkhackathon.R;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enspaender on 22.10.17.
 */

public class UserRoutesActivity extends AppCompatActivity implements UserRoutesAdapter.OnRoutesClickListener {

  @BindView(R.id.routes_recycler) RecyclerView recyclerView;
  private UserRoutesAdapter adapter;
  private LayoutInflater inflater;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_routes);
    ButterKnife.bind(this);

    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new UserRoutesAdapter(inflater,this);
    recyclerView.setAdapter(adapter);

    getRoutes();
  }

  private RoutesResponse response;
  private void getRoutes() {
    Provider.provideDataRepository().getRoutes()
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<RoutesResponse>() {
          @Override public void onCompleted() {

            runOnUiThread(()->{
              for (ArrayObjectsModel model : response.getObject().getHistories()){
                for (List<RoutesModel> m : model.getListRoutes()){
                  adapter.addRoute(m);
                }
              }
            });

          }

          @Override public void onError(Throwable e) {
            Log.e("ok",e.getMessage());

          }

          @Override public void onNext(RoutesResponse response1) {
            response = response1;
          }
        });
  }

  private void showInAdapter(RoutesResponse response){
    recyclerView.setAdapter(adapter);
  }


  @Override public void OnRoutesClick(List<RoutesModel> routes) {
    showAlert(routes);
  }

  private void showAlert(List<RoutesModel> routes) {
    // TODO: 22.10.17 make an alert with recycler
  }
}
