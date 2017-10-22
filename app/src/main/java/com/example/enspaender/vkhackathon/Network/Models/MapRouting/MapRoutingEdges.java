package com.example.enspaender.vkhackathon.Network.Models.MapRouting;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by enspaender on 21.10.17.
 */

public class MapRoutingEdges {
  @SerializedName("edges")
  private List<EdgeModel> edgeModels;
}
