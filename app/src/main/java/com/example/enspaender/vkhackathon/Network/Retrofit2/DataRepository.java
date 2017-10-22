package com.example.enspaender.vkhackathon.Network.Retrofit2;

import com.example.enspaender.vkhackathon.Network.Models.BaseResponse;
import com.example.enspaender.vkhackathon.Network.Models.MapBase.MapResponse;
import com.example.enspaender.vkhackathon.Network.Models.MapRouting.MapRoutingObject;
import com.example.enspaender.vkhackathon.Network.Models.NameModel;
import com.example.enspaender.vkhackathon.Network.Models.News.NewsResponse;
import com.example.enspaender.vkhackathon.Network.Models.Routes.RoutesModel;
import com.example.enspaender.vkhackathon.Network.Models.Routes.RoutesResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by enspa on 09.02.2017.
 */

public interface DataRepository {

//    TASK METHOD
//    Observable<ResponseTask> getChoosenTask();

  /** methods to working with map */
  Observable<MapResponse> getResponseMap(int size);

  Observable<MapRoutingObject> getResponseRouting(int size);

  Observable<NewsResponse> getNews(int size);

  Observable<NameModel> getNames();

  Observable<BaseResponse> setNames(List<String> choosenRooms);

  Observable<RoutesResponse> getRoutes();

}
