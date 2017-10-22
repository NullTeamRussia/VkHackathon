package com.example.enspaender.vkhackathon.Network.Models.News;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by enspaender on 21.10.17.
 */

public class NewsModel {
  @SerializedName("id")
  @Setter @Getter private int id;

  @SerializedName("title")
  @Setter @Getter private String title;

  @SerializedName("text")
  @Setter @Getter private String description;

  /** make request to server, to get current route of user */
  @SerializedName("route")
  @Setter @Getter private String url;

  @SerializedName("type")
  @Setter @Getter private String type;

  public NewsModel(int id,String title,String description,String url,String type){
    this.id = id;
    this.title = title;
    this.description = description;
    this.url = url;
    this.type = type;
  }
}
