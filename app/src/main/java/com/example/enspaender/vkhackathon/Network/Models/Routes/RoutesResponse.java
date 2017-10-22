package com.example.enspaender.vkhackathon.Network.Models.Routes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by enspaender on 22.10.17.
 */

public class RoutesResponse {

  @SerializedName("_embedded")
  @Setter @Getter private RoutesObject object;
}
