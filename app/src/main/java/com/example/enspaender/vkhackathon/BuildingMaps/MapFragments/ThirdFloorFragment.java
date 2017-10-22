package com.example.enspaender.vkhackathon.BuildingMaps.MapFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.enspaender.vkhackathon.Network.Models.MapBase.MapResponse;
import com.example.enspaender.vkhackathon.Network.Models.MapBase.NodeModel;
import com.example.enspaender.vkhackathon.Network.Retrofit2.Provider;
import com.example.enspaender.vkhackathon.R;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enspaender on 21.10.17.
 */

public class ThirdFloorFragment extends BaseFragment {
  private static ThirdFloorFragment thirdFloorFragment;

  @BindView(R.id.third_floor) SubsamplingScaleImageView map;
  private View view;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.third_floor,container,false);
    return view;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this,view);
    checkUpdate();
  }

  public static ThirdFloorFragment getInstance(boolean attach) {
    if (attach) {
      if (thirdFloorFragment == null) {
        synchronized (ThirdFloorFragment.class) {
          thirdFloorFragment = new ThirdFloorFragment();
        }
      }
    }else{
      thirdFloorFragment = null;
      System.gc();
    }
    return thirdFloorFragment;
  }

  private List<NodeModel> nodes;

  @Override public void checkUpdate() {
    Provider.provideDataRepository().getResponseMap(0)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<MapResponse>() {
          @Override public void onCompleted() {
            makeMap(map,"b1f3.jpg",nodes);
          }

          @Override public void onError(Throwable e) {

          }

          @Override public void onNext(MapResponse responseObject) {
            nodes = responseObject.getRo().getNodes();
          }
        });

    // TODO: 21.10.17 make call to the server and update map if needed
  }

  @Override public void getEdges() {

  }

  @Override public void onDetach() {
    getInstance(false);
    super.onDetach();
  }

  @Override public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
    super.onViewStateRestored(savedInstanceState);
    SecondFloorFragment.getInstance(false);
  }
}
