package com.example.enspaender.vkhackathon.CreateRoute;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.enspaender.vkhackathon.R;
import java.util.List;

/**
 * Created by enspaender on 22.10.17.
 */

public class NodesAdapter extends RecyclerView.Adapter<NodesAdapter.ViewHolder> {
  private List<String> names;
  private LayoutInflater inflater;
  private ChooseListener listener;

  public NodesAdapter(List<String> names,LayoutInflater inflater,ChooseListener listener)
  {
    this.names = names;
    this.inflater = inflater;
    this.listener = listener;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
  {
    View view = inflater.inflate(R.layout.item_existing_room,parent,false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    String name = names.get(position);
    holder.name.setText(name);

    listener.onChoose(name,holder.checkBox);
  }

  @Override public int getItemCount() {
    return names.size();
  }

  public interface ChooseListener{
    void onChoose(String name, CheckBox checkBox);
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.checked) CheckBox checkBox;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.room_root) View rootView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this,itemView);
    }
  }

}
