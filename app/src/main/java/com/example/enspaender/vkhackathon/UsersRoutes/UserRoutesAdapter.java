package com.example.enspaender.vkhackathon.UsersRoutes;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.enspaender.vkhackathon.Network.Models.Routes.RoutesModel;
import com.example.enspaender.vkhackathon.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by enspaender on 22.10.17.
 */

public class UserRoutesAdapter extends RecyclerView.Adapter<UserRoutesAdapter.ViewHolder> {
  private List<List<RoutesModel>> routes;
  private LayoutInflater inflater;
  private OnRoutesClickListener listener;

  public UserRoutesAdapter(List<List<RoutesModel>> models, LayoutInflater inflater, OnRoutesClickListener listener){
    this.routes = models;
    this.inflater = inflater;
    this.listener = listener;
  }

  public UserRoutesAdapter(LayoutInflater inflater,OnRoutesClickListener listener){
    routes = new ArrayList<>();
    this.inflater = inflater;
    this.listener = listener;
  }


  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View rootView = inflater.inflate(R.layout.item_user_route,parent,false);
    return new ViewHolder(rootView);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    RoutesModel route = routes.get(position).get(0);
    holder.title.setText(route.getTitle()+"("+getItemCount()+")");
    holder.root.setOnClickListener((view) -> listener.OnRoutesClick(routes.get(position)));

  }

  public interface OnRoutesClickListener{
    void OnRoutesClick(List<RoutesModel> routesModel);
  }

  public void addRoute(List<RoutesModel> model){
    routes.add(model);
    notifyDataSetChanged();
  }


  @Override public int getItemCount() {
    return routes.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.routes_title) TextView title;
    @BindView(R.id.routes_root) CardView root;
    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }
}
