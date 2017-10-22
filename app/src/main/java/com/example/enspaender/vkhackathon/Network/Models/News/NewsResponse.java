package com.example.enspaender.vkhackathon.Network.Models.News;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by enspaender on 21.10.17.
 */

public class NewsResponse {
  @SerializedName("_embedded")
  @Setter @Getter private NewsObject newsObject;
}
