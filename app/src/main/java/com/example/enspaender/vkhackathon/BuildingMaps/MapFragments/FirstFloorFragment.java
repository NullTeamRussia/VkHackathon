package com.example.enspaender.vkhackathon.BuildingMaps.MapFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.enspaender.vkhackathon.Network.Models.MapBase.MapResponse;
import com.example.enspaender.vkhackathon.Network.Models.MapBase.NodeModel;
import com.example.enspaender.vkhackathon.Network.Models.MapRouting.MapRoutingObject;
import com.example.enspaender.vkhackathon.Network.Retrofit2.Provider;
import com.example.enspaender.vkhackathon.R;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enspaender on 21.10.17.
 */

public class FirstFloorFragment extends BaseFragment {
  private static final String TAG = "1FRAGMENT";
  private static FirstFloorFragment firstFloorFragment;

  @BindView(R.id.first_floor) SubsamplingScaleImageView map;
  private View view;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    Log.e(TAG,"Create view");
    view = inflater.inflate(R.layout.first_floor,container,false);
    return view;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    Log.e(TAG,"View created");
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this,view);
    checkUpdate();
  }

  private List<NodeModel> nodes;


  @Override public void checkUpdate() {
    Provider.provideDataRepository().getResponseMap(0)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<MapResponse>() {
          @Override public void onCompleted() {
            makeMap(map,"b1f1.jpg",nodes);
          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(MapResponse mapResponse) {
            nodes = mapResponse.getRo().getNodes();
          }
        });
  }

  public static FirstFloorFragment getInstance(boolean attach) {
    if (attach) {
      if (firstFloorFragment == null) {
        synchronized (FirstFloorFragment.class) {
          if (firstFloorFragment == null) {
            firstFloorFragment = new FirstFloorFragment();
          }
        }
      }
    }else {
      firstFloorFragment = null;
      System.gc();
    }
    return firstFloorFragment;
  }

  @Override public void onDetach() {
    getInstance(false);
    Log.e("FIRST","DETACH");
    super.onDetach();

    Log.e("FIRST","DETACH +");
  }

  @Override public void getEdges() {
    Provider.provideDataRepository().getResponseRouting(5000)
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<MapRoutingObject>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(MapRoutingObject mapRoutingObject) {

          }
        });
  }

  @Override public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
    Log.e(TAG,"OnRestored");
    super.onViewStateRestored(savedInstanceState);
    SecondFloorFragment.getInstance(false);
  }
}
