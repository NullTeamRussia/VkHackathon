package com.example.enspaender.vkhackathon.NewsScreen;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.enspaender.vkhackathon.Network.Models.News.NewsModel;
import com.example.enspaender.vkhackathon.R;
import java.util.List;

/**
 * Created by enspaender on 21.10.17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

  private List<NewsModel> newsList;
  private LayoutInflater inflater;
  private OnNewsClickListener listener;

  public NewsAdapter(List<NewsModel> newsModel,LayoutInflater inflater,OnNewsClickListener listener){
    this.newsList = newsModel;
    this.inflater = inflater;
    this.listener = listener;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = inflater.inflate(R.layout.news_item,parent,false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    NewsModel model = newsList.get(position);
    holder.title.setText(model.getTitle());
    holder.description.setText(model.getDescription());
    holder.type.setText(model.getType());

    holder.rootView.setOnClickListener(view -> listener.onNewsClick(model));
  }

  @Override public int getItemCount() {
    return newsList.size();
  }

  public void setNewsList(List<NewsModel> newsList) {
    this.newsList = newsList;
    notifyDataSetChanged();
  }

  public interface OnNewsClickListener{
    void onNewsClick(NewsModel news);// TODO: 21.10.17 required news model
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.news_root) CardView rootView;
    @BindView(R.id.news_title) TextView title;
    @BindView(R.id.news_description) TextView description;
    @BindView(R.id.news_type) TextView type;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }
}
