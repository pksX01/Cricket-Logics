package com.ojasxlabs.cricketlogics;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterForLiveScore extends RecyclerView.Adapter<AdapterForLiveScore.ViewHolder> {
    private List<ListItemForLiveScore> listItems;
    private Context context;

    public AdapterForLiveScore(List<ListItemForLiveScore> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewTypes) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.matches_list,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForLiveScore.ViewHolder viewHolder, int i) {
       final ListItemForLiveScore listItem = listItems.get(i);

        //viewHolder.textUniqueId.setText(listItem.getUniqueId());
        viewHolder.textDate.setText(listItem.getDate());
        viewHolder.textType.setText("Match Type: " + listItem.getMatch_type());
        viewHolder.textTeam2.setText(listItem.getTeam2());
        viewHolder.textTeam1.setText(listItem.getTeam1());
        viewHolder.textMatchStarted.setText("Match Status : "+listItem.getMatchStarted());
        /*viewHolder.cardview_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String match_id = listItem.getUniqueId();
                Intent intent = new Intent(context, ScoreHelper.class);
                intent.putExtra("uniqueId", match_id);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       // public TextView textUniqueId;
        public TextView textDate;
       public TextView textType;
        public TextView textTeam2;
        public TextView textTeam1;
        public TextView textMatchStarted;
      //  public CardView cardview_match;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // textUniqueId = (TextView) itemView.findViewById(R.id.uniqueId);
            textDate = (TextView) itemView.findViewById(R.id.date);
           textType = (TextView) itemView.findViewById(R.id.matchType);
            textTeam2 = (TextView) itemView.findViewById(R.id.team2);
            textTeam1 = (TextView) itemView.findViewById(R.id.team1);
            textMatchStarted = (TextView) itemView.findViewById(R.id.matchStarted);
          //  cardview_match = (CardView) itemView.findViewById(R.id.Match_cardView);
        }
    }
}
