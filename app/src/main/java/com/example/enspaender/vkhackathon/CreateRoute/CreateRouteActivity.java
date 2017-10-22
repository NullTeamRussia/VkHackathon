package com.example.enspaender.vkhackathon.CreateRoute;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.CheckBox;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.enspaender.vkhackathon.Network.Models.BaseResponse;
import com.example.enspaender.vkhackathon.Network.Models.NameModel;
import com.example.enspaender.vkhackathon.Network.Retrofit2.Provider;
import com.example.enspaender.vkhackathon.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enspaender on 22.10.17.
 */

public class CreateRouteActivity extends AppCompatActivity implements NodesAdapter.ChooseListener {

  @BindView(R.id.room_recycler) RecyclerView roomRecycler;
  private NodesAdapter adapter;
  private List<String> roomsSelected;

  @Override protected void onCreate( Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_route);
    ButterKnife.bind(this);

    roomRecycler.setLayoutManager(new LinearLayoutManager(this));
    roomRecycler.setItemAnimator(new DefaultItemAnimator());
    roomsSelected = new ArrayList<>();

    getNames();

  }

  private List<String> names;
  private void getNames() {
    Provider.provideDataRepository().getNames()
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<NameModel>() {
          @Override public void onCompleted() {
            runOnUiThread(()->showInRecycler(names));
          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(NameModel nameModel) {
            names = nameModel.getNames();
          }
        });
  }

  private void showInRecycler(List<String> names) {
    adapter = new NodesAdapter(names,getLayoutInflater(),this);
    roomRecycler.setAdapter(adapter);
  }

  @OnClick(R.id.save_route)
  public void save(){
    // TODO: 22.10.17 post to helper api
    Provider.provideDataRepository().setNames(roomsSelected)
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<BaseResponse>() {
          @Override public void onCompleted() {
            Log.e("RouteActivity","OK");
          }

          @Override public void onError(Throwable e) {
            Log.e("RouteActivity","FAIL");
          }

          @Override public void onNext(BaseResponse baseResponse) {

          }
        });
  }

  @Override public void onChoose(String name, CheckBox checkBox) {
    checkBox.setOnCheckedChangeListener((view,listener)->{
      if (checkBox.isChecked()){
        roomsSelected.add(name);
        Log.e("RouteActivity",roomsSelected.toString());

      }else{
        for (String n : roomsSelected){
          if (Objects.equals(n, name)) {
            roomsSelected.remove(name);
            Log.e("RouteActivity",roomsSelected.toString());
          }
        }
      }
    });

    Log.e("RouteActivity",roomsSelected.toString());
  }

}
