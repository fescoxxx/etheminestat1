package com.rijsoft.ethermine.etherminestats.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rijsoft.ethermine.etherminestats.R;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;

import java.util.Locale;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.OverviewViewHolder> {
    // Set numbers of Card in RecyclerView.
    private static final int LENGTH = 4;
    private Context context;

    private final String[] mTitleOverview;

    private CurrentStats currentStats;

    public OverviewAdapter(Context context, CurrentStats currentStats) {
        this.currentStats = currentStats;
        this.context = context;
        Resources resources = context.getResources();
        mTitleOverview = resources.getStringArray(R.array.title_overview);
    }

    @Override
    public OverviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OverviewViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(OverviewViewHolder holder, int position) {
        Double heshreit;
        String strHeshreit = "";
        String addReit = "";
        String percent = "";
        String setStr;

        Double valid ;
        Double stales;
        Double invalid;
        Double summ;

        switch (position) {
            //REPORTED h\s
            case 0:
                try {
                    heshreit = Double.valueOf(currentStats.getData().getReportedHashrate());
                    if (heshreit*0.000001 < 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000001) + " MH/s";
                    } else if (heshreit*0.000001 >= 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000000001) + " GH/s";
                    }
                    valid = Double.valueOf(currentStats.getData().getValidShares());
                    stales = Double.valueOf(currentStats.getData().getStaleShares());
                    invalid = Double.valueOf(currentStats.getData().getInvalidShares());
                    summ = stales+invalid;
                    Double prcnt =  100 - ((summ / valid) *100);
                    percent = "Valid "+currentStats.getData().getValidShares() + "("+ String.format( Locale.US, "%.2f", prcnt)+"%)";
                } catch (NullPointerException ex) {
                    heshreit = 0d;
                    addReit = " H/s";
                    percent = "Valid 0(0%)";
                }
                setStr = strHeshreit + addReit;
                holder.gh_name.setText(setStr);
                holder.procent.setText(percent);
                holder.title_card_backround.setBackgroundColor(context.getResources().getColor(R.color.reported_green));
                break;
            //CURRENT h\s
            case 1:
                try {
                    heshreit = Double.valueOf(currentStats.getData().getCurrentHashrate());
                    if (heshreit*0.000001 < 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000001) + " MH/s";
                    } else if (heshreit*0.000001 >= 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000000001) + " GH/s";
                    }
                    valid = Double.valueOf(currentStats.getData().getValidShares());
                    stales = Double.valueOf(currentStats.getData().getStaleShares());
                    invalid = Double.valueOf(currentStats.getData().getInvalidShares());
                    summ = valid+invalid;

                    Double prcnt =  ((stales / summ) *100);
                    percent = "Stale "+currentStats.getData().getStaleShares() + "("+ String.format( Locale.US, "%.2f", prcnt)+"%)";
                } catch (NullPointerException ex) {
                    heshreit = 0d;
                    addReit = " H/s";
                    percent = "0(0%)";
                }
                setStr = strHeshreit + addReit;
                holder.gh_name.setText(setStr);
                holder.procent.setText(percent);
                holder.title_card_backround.setBackgroundColor(context.getResources().getColor(R.color.current_blue));
                break;
            //AVERANGE h\s
            case 2:
                try {
                    heshreit = Double.valueOf(currentStats.getData().getAverageHashrate());
                    if (heshreit*0.000001 < 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000001) + " MH/s";
                    } else if (heshreit*0.000001 >= 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000000001) + " GH/s";
                    }
                } catch (NullPointerException ex) {
                    heshreit = 0d;
                    addReit = " H/s";
                    percent = "0(0%)";
                }
                setStr = strHeshreit + addReit;
                holder.gh_name.setText(setStr);
                holder.procent.setText(percent);
                holder.title_card_backround.setBackgroundColor(context.getResources().getColor(R.color.average_orange));
                break;
            //ACTIVE WORKERS
            case 3:
                try {
                    heshreit = Double.valueOf(currentStats.getData().getActiveWorkers());
                } catch (NullPointerException ex) {
                    heshreit = 0d;
                    addReit = "H/s";
                    percent = "0(0%)";
                }
                setStr = String.valueOf(heshreit) + addReit;
                holder.gh_name.setText(setStr);
                holder.procent.setText(percent);
                holder.title_card_backround.setBackgroundColor(context.getResources().getColor(R.color.workers_reg));
                break;
        }
        holder.title_card.setText(mTitleOverview[position % mTitleOverview.length]);
    }


    @Override
    public int getItemCount() {
        return LENGTH;
    }

    public class OverviewViewHolder extends RecyclerView.ViewHolder {
        public TextView title_card, gh_name, procent;
        public RelativeLayout title_card_backround;
        public OverviewViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card_two, parent, false));
            title_card = (TextView) itemView.findViewById(R.id.card_title);
            gh_name = (TextView) itemView.findViewById(R.id.gh_name);
            procent = (TextView) itemView.findViewById(R.id.procent);
            title_card_backround = (RelativeLayout) itemView.findViewById(R.id.card_title_backround);
        }
    }

}




