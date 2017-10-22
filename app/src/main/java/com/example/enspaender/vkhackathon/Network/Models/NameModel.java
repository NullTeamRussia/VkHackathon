package com.example.enspaender.vkhackathon.Network.Models;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by enspaender on 22.10.17.
 */

public class NameModel {

  @SerializedName("result")
  @Setter @Getter private List<String> names;
}
