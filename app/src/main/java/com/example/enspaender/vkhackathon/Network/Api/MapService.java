package com.example.enspaender.vkhackathon.Network.Api;

import com.example.enspaender.vkhackathon.Network.Models.MapBase.MapResponse;
import com.example.enspaender.vkhackathon.Network.Models.MapRouting.MapRoutingObject;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by enspaender on 21.10.17.
 */

public interface MapService {

  @GET("/nodes") Observable<MapResponse> getNodes(@Query("size") int size);

  @GET("/edges") Observable<MapRoutingObject> getEdges(@Query("size") int size);

}
