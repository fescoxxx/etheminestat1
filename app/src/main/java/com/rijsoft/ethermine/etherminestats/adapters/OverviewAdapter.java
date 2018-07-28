package com.rijsoft.ethermine.etherminestats.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rijsoft.ethermine.etherminestats.R;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.OverviewViewHolder> {
    // Set numbers of Card in RecyclerView.
    private static final int LENGTH = 4;
    private Context context;

    private final String[] mTitleOverview;
    private final String[] mPlaceDesc;


    private CurrentStats currentStats;

    public OverviewAdapter(Context context, CurrentStats currentStats) {
        this.currentStats = currentStats;
        this.context = context;
        Resources resources = context.getResources();
        mTitleOverview = resources.getStringArray(R.array.title_overview);
        mPlaceDesc = resources.getStringArray(R.array.place_desc);
    }

    @Override
    public OverviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OverviewViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(OverviewViewHolder holder, int position) {

        if (position == 0){
            holder.title_card_backround.setBackgroundColor(context.getResources().getColor(R.color.average_orange));
        }
        else if (position == 1){
            holder.title_card_backround.setBackgroundColor(context.getResources().getColor(R.color.current_blue));
        }
        else if (position == 2){
            holder.title_card_backround.setBackgroundColor(context.getResources().getColor(R.color.reported_green));
        }
        else if (position == 3){
            holder.title_card_backround.setBackgroundColor(context.getResources().getColor(R.color.workers_reg));
        }

        holder.title_card.setText(mTitleOverview[position % mTitleOverview.length]);
        holder.description.setText(mPlaceDesc[position % mPlaceDesc.length]);
    }


    @Override
    public int getItemCount() {
        return LENGTH;
    }

    public  class OverviewViewHolder extends RecyclerView.ViewHolder {
        public TextView title_card;
        public RelativeLayout title_card_backround;
        public TextView description;
        public OverviewViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card, parent, false));
            title_card = (TextView) itemView.findViewById(R.id.card_title);
            title_card_backround = (RelativeLayout) itemView.findViewById(R.id.card_title_backround);


            description = (TextView) itemView.findViewById(R.id.card_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            // Adding Snackbar to Action Button inside card
            Button button = (Button)itemView.findViewById(R.id.action_button);
            button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Action is pressed",
                            Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

}




