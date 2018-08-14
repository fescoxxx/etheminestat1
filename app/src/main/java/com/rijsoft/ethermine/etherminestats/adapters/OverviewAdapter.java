package com.rijsoft.ethermine.etherminestats.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rijsoft.ethermine.etherminestats.R;
import com.rijsoft.ethermine.etherminestats.model.currentStats.CurrentStats;

import java.util.Date;
import java.util.Locale;

public class OverviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // Set numbers of Card in RecyclerView.
    private static final int LENGTH = 4;
    private Context context;

    private final String[] mTitleOverview_1;
    private final String[] mTitleOverview_2;

    private CurrentStats currentStats;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public OverviewAdapter(Context context, CurrentStats currentStats) {
        this.currentStats = currentStats;
        this.context = context;
        Resources resources = context.getResources();
        mTitleOverview_1 = resources.getStringArray(R.array.title_overview_1);
        mTitleOverview_2 = resources.getStringArray(R.array.title_overview_2);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == TYPE_ITEM)
        {
            return new OverviewItemViewHolder(LayoutInflater.from(parent.getContext()), parent);
        } else if (viewType == TYPE_HEADER) {
            return new OverviewHeaderViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof OverviewHeaderViewHolder)
        {
            Double unpaid;
            String unpaidStr = "";

            OverviewHeaderViewHolder header = (OverviewHeaderViewHolder)holder;
         //   header.card_title_backround.setBackgroundColor(context.getResources().getColor(R.color.reported_green));
            header.title_card.setText("Unpaid Balance");
            try {
                unpaid = Double.valueOf(currentStats.getData().getUnpaid())*0.000000000000000001;
                unpaidStr = String.format(Locale.US,"%.5g%n", unpaid)+ " ETH";
            }
            catch (Exception ex) {
                unpaidStr = "0.0 ETH";
            }
            header.unpaid.setText(unpaidStr.replace("\n", ""));
        }
        else  if(holder instanceof OverviewItemViewHolder)
        {
            OverviewItemViewHolder item = (OverviewItemViewHolder)holder;
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
                case 1:
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
                    item.gh_name_left.setText(setStr);
           //         item.title_card_backround_left.setBackgroundColor(context.getResources().getColor(R.color.reported_green));


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
                    item.gh_name_right.setText(setStr);
          //          item.title_card_backround_right.setBackgroundColor(context.getResources().getColor(R.color.reported_green));
                    break;
                case 2:
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
                    item.gh_name_left.setText(setStr);
         //           item.title_card_backround_left.setBackgroundColor(context.getResources().getColor(R.color.average_orange));

                    //ACTIVE WORKERS RIGHT
                    try {
                        setStr = currentStats.getData().getActiveWorkers();
                    } catch (NullPointerException ex) {
                        setStr = "0";
                    }
                    item.gh_name_right.setText(setStr);
          //          item.title_card_backround_right.setBackgroundColor(context.getResources().getColor(R.color.average_orange));
                    break;
                case 3:
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
                    item.gh_name_left.setText(percent);
           //         item.title_card_backround_left.setBackgroundColor(context.getResources().getColor(R.color.current_blue));

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
                    item.gh_name_right.setText(percent);
             //       item.title_card_backround_right.setBackgroundColor(context.getResources().getColor(R.color.current_blue));
                    break;
                case 4:
                    //INVALID LEFT
                    try {
                        valid = Double.valueOf(currentStats.getData().getValidShares());
                        stales = Double.valueOf(currentStats.getData().getStaleShares());
                        invalid = Double.valueOf(currentStats.getData().getInvalidShares());

                        summ = valid+stales;

                        Double prcnt =  ((invalid / summ) *100);
                        percent = currentStats.getData().getInvalidShares() + "("+ String.format( Locale.US, "%.2f", prcnt)+"%)";
                    } catch (NullPointerException ex) {
                        percent = "0(0%)";
                    }
                    item.gh_name_left.setText(percent);
             //       item.title_card_backround_left.setBackgroundColor(context.getResources().getColor(R.color.workers_reg));

                    //LAST SEEN RIGHT

                    try {
                        Date today = new Date();
                        Long lastSeen = Long.valueOf(currentStats.getData().getLastSeen());
                        Date time=new java.util.Date((long)lastSeen*1000);

                        long milliseconds = today.getTime() - time.getTime();
                        setStr = String.valueOf(milliseconds / (60 * 1000)) + " m";
                        item.gh_name_right.setText(setStr);
                    }catch (NullPointerException ex) {

                        item.gh_name_right.setText("-");
                    }


            //        item.title_card_backround_right.setBackgroundColor(context.getResources().getColor(R.color.workers_reg));
                    break;
            }
            item.title_card_left.setText(mTitleOverview_1[position-1 % mTitleOverview_1.length]);
            item.title_card_right.setText(mTitleOverview_2[position-1 % mTitleOverview_2.length]);
        }


    }


    //    need to override this method
    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position)
    {
        return position == 0;
    }


    @Override
    public int getItemCount() {
        return LENGTH+1;
    }

    public class OverviewHeaderViewHolder extends RecyclerView.ViewHolder{
        public TextView title_card, unpaid;
        public RelativeLayout card_title_backround;
        public OverviewHeaderViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_header_two_card, parent, false));
            title_card = (TextView)itemView.findViewById(R.id.card_title);
            unpaid = (TextView)itemView.findViewById(R.id.unpaid);
            card_title_backround = (RelativeLayout)itemView.findViewById(R.id.card_title_backround);
        }
    }

    public class OverviewItemViewHolder extends RecyclerView.ViewHolder {
        public TextView title_card_left, gh_name_left;
        public RelativeLayout title_card_backround_left;

        public TextView title_card_right, gh_name_right;
        public RelativeLayout title_card_backround_right;

        public OverviewItemViewHolder(LayoutInflater inflater, ViewGroup parent) {
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




