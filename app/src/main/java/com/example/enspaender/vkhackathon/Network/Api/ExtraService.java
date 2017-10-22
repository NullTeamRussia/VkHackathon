package com.example.enspaender.vkhackathon.Network.Api;

import com.example.enspaender.vkhackathon.Network.Models.BaseResponse;
import com.example.enspaender.vkhackathon.Network.Models.NameModel;
import com.example.enspaender.vkhackathon.Network.Models.News.NewsModel;
import com.example.enspaender.vkhackathon.Network.Models.News.NewsResponse;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by enspaender on 21.10.17.
 */

public interface ExtraService {

  @GET("/news") Observable<NewsResponse> getNews(@Query("size") int size);

  @GET("/names") Observable<NameModel> getNames();

  @POST("/calc") Observable<BaseResponse> setNames(@Body List<String> choosenRooms);


}
