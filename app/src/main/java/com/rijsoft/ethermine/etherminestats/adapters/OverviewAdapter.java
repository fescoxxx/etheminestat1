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

    private final String[] mTitleOverview_1;
    private final String[] mTitleOverview_2;

    private CurrentStats currentStats;

    public OverviewAdapter(Context context, CurrentStats currentStats) {
        this.currentStats = currentStats;
        this.context = context;
        Resources resources = context.getResources();
        mTitleOverview_1 = resources.getStringArray(R.array.title_overview_1);
        mTitleOverview_2 = resources.getStringArray(R.array.title_overview_2);
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
            //REPORTED LEFT
            case 0:
                try {
                    heshreit = Double.valueOf(currentStats.getData().getReportedHashrate());
                    if (heshreit*0.000001 < 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000001) + " MH/s";
                    } else if (heshreit*0.000001 >= 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000000001) + " GH/s";
                    }
                } catch (NullPointerException ex) {
                    strHeshreit = "0";
                    addReit = " H/s";
                }
                setStr = strHeshreit + addReit;
                holder.gh_name_left.setText(setStr);
                holder.title_card_backround_left.setBackgroundColor(context.getResources().getColor(R.color.reported_green));


                //CURRENT RIGHT
                try {
                    heshreit = Double.valueOf(currentStats.getData().getCurrentHashrate());
                    if (heshreit*0.000001 < 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000001) + " MH/s";
                    } else if (heshreit*0.000001 >= 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000000001) + " GH/s";
                    }

                } catch (NullPointerException ex) {
                    strHeshreit = "0";
                    addReit = " H/s";
                }
                setStr = strHeshreit + addReit;
                holder.gh_name_right.setText(setStr);
                holder.title_card_backround_right.setBackgroundColor(context.getResources().getColor(R.color.reported_green));
                break;
            case 1:
                //AVERANGE LEFT
                try {
                    heshreit = Double.valueOf(currentStats.getData().getAverageHashrate());
                    if (heshreit*0.000001 < 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000001) + " MH/s";
                    } else if (heshreit*0.000001 >= 1000) {
                        strHeshreit = String.format( Locale.US, "%.2f", heshreit*0.000000001) + " GH/s";
                    }
                } catch (NullPointerException ex) {
                    strHeshreit = "0";
                    addReit = " H/s";
                }
                setStr = strHeshreit + addReit;
                holder.gh_name_left.setText(setStr);
                holder.title_card_backround_left.setBackgroundColor(context.getResources().getColor(R.color.average_orange));

                //ACTIVE WORKERS RIGHT
                try {
                    setStr = currentStats.getData().getActiveWorkers();
                } catch (NullPointerException ex) {
                    setStr = "0";
                }
                holder.gh_name_right.setText(setStr);
                holder.title_card_backround_right.setBackgroundColor(context.getResources().getColor(R.color.average_orange));
                break;
            case 2:
                //VALID SHARES LEFT
                try {
                    valid = Double.valueOf(currentStats.getData().getValidShares());
                    stales = Double.valueOf(currentStats.getData().getStaleShares());
                    invalid = Double.valueOf(currentStats.getData().getInvalidShares());
                    summ = stales+invalid;
                    Double prcnt =  100 - ((summ / valid) *100);
                    percent = ""+currentStats.getData().getValidShares() + "("+ String.format( Locale.US, "%.2f", prcnt)+"%)";
                } catch (NullPointerException ex) {
                    percent = "0(0%)";
                }
                holder.gh_name_left.setText(percent);
                holder.title_card_backround_left.setBackgroundColor(context.getResources().getColor(R.color.current_blue));

                //STALE SHARES RIGHT
                try {
                    valid = Double.valueOf(currentStats.getData().getValidShares());
                    stales = Double.valueOf(currentStats.getData().getStaleShares());
                    invalid = Double.valueOf(currentStats.getData().getInvalidShares());
                    summ = valid+invalid;

                    Double prcnt =  ((stales / summ) *100);
                    percent = currentStats.getData().getStaleShares() + "("+ String.format( Locale.US, "%.2f", prcnt)+"%)";
                } catch (NullPointerException ex) {
                    percent = "0(0%)";
                }
                holder.gh_name_right.setText(percent);
                holder.title_card_backround_right.setBackgroundColor(context.getResources().getColor(R.color.current_blue));
                break;
            case 3:
                //INVALID LEFT




                //LAST SEEN RIGHT



                break;
        }
        holder.title_card_left.setText(mTitleOverview_1[position % mTitleOverview_1.length]);
        holder.title_card_right.setText(mTitleOverview_2[position % mTitleOverview_2.length]);
    }


    @Override
    public int getItemCount() {
        return LENGTH;
    }

    public class OverviewViewHolder extends RecyclerView.ViewHolder {
        public TextView title_card_left, gh_name_left;
        public RelativeLayout title_card_backround_left;

        public TextView title_card_right, gh_name_right;
        public RelativeLayout title_card_backround_right;

        public OverviewViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_card_two, parent, false));

            //left
            title_card_left = (TextView) itemView.findViewById(R.id.card_title_left);
            gh_name_left = (TextView) itemView.findViewById(R.id.gh_name_left);
            title_card_backround_left = (RelativeLayout) itemView.findViewById(R.id.card_title_backround_left);


            //right
            title_card_right = (TextView) itemView.findViewById(R.id.card_title_right);
            gh_name_right  = (TextView) itemView.findViewById(R.id.gh_name_right);
            title_card_backround_right  = (RelativeLayout) itemView.findViewById(R.id.card_title_backround_right);
        }
    }

}




