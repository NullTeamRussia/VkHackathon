package com.example.enspaender.vkhackathon.Network.Models.Routes;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by enspaender on 22.10.17.
 */

public class RoutesObject {

  @SerializedName("histories")
  @Setter @Getter private List<ArrayObjectsModel> histories;
}
