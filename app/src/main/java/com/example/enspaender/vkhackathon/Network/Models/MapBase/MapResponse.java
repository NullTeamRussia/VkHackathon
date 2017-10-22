package com.example.enspaender.vkhackathon.Network.Models.MapBase;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by enspaender on 21.10.17.
 */

public class MapResponse {
  @SerializedName("_embedded")
  @Setter @Getter ResponseObject ro;

  public MapResponse(ResponseObject responseObject){
    this.ro = responseObject;
  }
}
