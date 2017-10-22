package com.example.enspaender.vkhackathon.Network.Models.MapRouting;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by enspaender on 21.10.17.
 */

public class EdgeModel {
  @SerializedName("startX")
  @Setter @Getter private int xStart;

  @SerializedName("startY")
  @Setter @Getter private int yStart;

  @SerializedName("endX")
  @Setter @Getter private int xEnd;

  @SerializedName("endY")
  @Setter @Getter private int yEnd;

}
