package com.example.enspaender.vkhackathon.Network.Models.News;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by enspaender on 21.10.17.
 */

public class NewsObject {
  @SerializedName("news")
  @Setter @Getter private List<NewsModel> news;
}
