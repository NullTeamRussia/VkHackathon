package com.example.enspaender.vkhackathon.Network.Retrofit2;

import com.example.enspaender.vkhackathon.Network.Models.BaseResponse;
import com.example.enspaender.vkhackathon.Network.Models.MapBase.MapResponse;
import com.example.enspaender.vkhackathon.Network.Models.MapRouting.MapRoutingObject;
import com.example.enspaender.vkhackathon.Network.Models.NameModel;
import com.example.enspaender.vkhackathon.Network.Models.News.NewsResponse;
import java.util.List;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by enspa on 09.02.2017.
 */

public class OnlineDataRepository implements DataRepository {

  @Override public Observable<MapResponse> getResponseMap(int pageNum) {
    return RetrofitManager.getmMapService().getNodes(pageNum)
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread());
  }

  @Override public Observable<MapRoutingObject> getResponseRouting(int page) {
    return RetrofitManager.getmMapService().getEdges(page)
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread());
  }

  @Override public Observable<NewsResponse> getNews(int size) {
    return RetrofitManager.getmExtraService().getNews(size)
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread());
  }

  @Override public Observable<NameModel> getNames() {
    return RetrofitManager.getExtraHelperService().getNames()
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread());
  }

  @Override public Observable<BaseResponse> setNames(List<String> choosenRooms) {
    return RetrofitManager.getExtraHelperService().setNames(choosenRooms)
        .observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread());
  }

  //@Override
    //public Observable<ResponseTask> getChoosenTask() {
    //    return RetrofitManager.getTasksService().getTask()
    //            .observeOn(Schedulers.io())
    //            .subscribeOn(mainThread());
    //}



}
