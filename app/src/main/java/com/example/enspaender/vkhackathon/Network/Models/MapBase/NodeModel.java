package com.example.enspaender.vkhackathon.Network.Models.MapBase;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by enspaender on 21.10.17.
 */

public class NodeModel {

  @SerializedName("available")
  @Setter @Getter private boolean available;

  @SerializedName("name")
  @Setter @Getter private String roomName;

  @SerializedName("description")
  @Setter @Getter private String roomDescription;

  @SerializedName("x")
  @Setter @Getter private int xCoord;

  @SerializedName("y")
  @Setter @Getter private int yCoord;


}
