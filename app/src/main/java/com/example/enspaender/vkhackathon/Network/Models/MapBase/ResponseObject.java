package com.example.enspaender.vkhackathon.Network.Models.MapBase;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by enspaender on 21.10.17.
 */

public class ResponseObject {
  @SerializedName("nodes")
  @Setter @Getter List<NodeModel> nodes;
}
