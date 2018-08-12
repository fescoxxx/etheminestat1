package com.rijsoft.ethermine.etherminestats.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rijsoft.ethermine.etherminestats.R;
import com.rijsoft.ethermine.etherminestats.model.workers.Data;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WorkersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    List<Data> data;
    private Context context;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public WorkersAdapter(Context context,List<Data> data ){
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == TYPE_ITEM)
        {
            return new WorkersAdapterItemViewHolder(LayoutInflater.from(parent.getContext()), parent);
        } else if (viewType == TYPE_HEADER) {
            return new WorkersHeaderViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    private String getFormatterBeautyHeshrait(String heshreitBad) {
        Double heshreit;
        String addReit = "";
        try {
            heshreit = Double.valueOf(heshreitBad);
            if (heshreit*0.000001 < 1000) {
                heshreitBad = String.format( Locale.US, "%.2f", heshreit*0.000001) + " MH/s";
            } else if (heshreit*0.000001 >= 1000) {
                heshreitBad = String.format( Locale.US, "%.2f", heshreit*0.000000001) + " GH/s";
            }
        } catch (NullPointerException ex) {
            heshreitBad = "0";
            addReit = " H/s";
        }
        return heshreitBad +addReit;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof WorkersAdapterItemViewHolder) {
            WorkersAdapterItemViewHolder item = (WorkersAdapterItemViewHolder) holder;

            String workerName ="Worker: "+data.get(position-1).getWorker();
            item.card_title_name_worker.setText(workerName);

            String validSh = "Valid: "+ (data.get(position-1).getValidShares()
                    == null ? "0" : data.get(position-1).getValidShares());
            item.valid.setText(validSh);

            String staleSh = "Stale: "+ (data.get(position-1).getStaleShares()
                    == null ? "0" : data.get(position-1).getStaleShares());
            item.stale.setText(staleSh);

            String invalidSh = "Invalid: "+ (data.get(position-1).getInvalidShares()
                    == null ? "0" : data.get(position-1).getInvalidShares());
            item.invalid.setText(invalidSh);

            String reportedHs = (data.get(position-1).getReportedHashrate()
                    == null ? "0" : data.get(position-1).getReportedHashrate());

            String currentHs = (data.get(position-1).getCurrentHashrate()
                    == null ? "0" : data.get(position-1).getCurrentHashrate());

            String averageHs = (data.get(position-1).getAverageHashrate()
                    == null ? "0" : data.get(position-1).getAverageHashrate());

            item.reported.setText(getFormatterBeautyHeshrait(reportedHs));
            item.current.setText(getFormatterBeautyHeshrait(currentHs));
            item.average.setText(getFormatterBeautyHeshrait(averageHs));

            //LAST SEEN
            try {
                Date today = new Date();
                Long lastSeen = Long.valueOf(data.get(position-1).getLastSeen());
                Date time=new java.util.Date((long)lastSeen*1000);
                long milliseconds = today.getTime() - time.getTime();
                String setStr = "Last Seen: "+String.valueOf(milliseconds / (60 * 1000)) + " m";
                item.last_seen.setText(setStr);
            }catch (NullPointerException ex) {
                item.last_seen.setText("-");
            }



        }  else if(holder instanceof WorkersHeaderViewHolder) {
            WorkersHeaderViewHolder header = (WorkersHeaderViewHolder) holder;

            String workers = String.valueOf(data.size());
            header.workers_total.setText(workers);

            Double reportedHsDouble = 0d;
            Double currentHsDouble = 0d;
            Double averageHsDouble = 0d;
            for (int i=0;i<data.size();i++){
                try {
                    reportedHsDouble = reportedHsDouble+Double.parseDouble(data.get(i).getReportedHashrate());
                } catch (Exception ex) {
                    reportedHsDouble = reportedHsDouble+0;
                }
            }
            for (int i=0;i<data.size();i++){
                try {
                    currentHsDouble = currentHsDouble+Double.parseDouble(data.get(i).getCurrentHashrate());
                } catch (Exception ex) {
                    currentHsDouble = currentHsDouble+0;
                }
            }
            for (int i=0;i<data.size();i++){
                try {
                    averageHsDouble = averageHsDouble+Double.parseDouble(data.get(i).getAverageHashrate());
                } catch (Exception ex) {
                    averageHsDouble = averageHsDouble+0;
                }
            }
            header.reported_total.setText(getFormatterBeautyHeshrait(reportedHsDouble.toString()));
            header.current_total.setText(getFormatterBeautyHeshrait(currentHsDouble.toString()));
            header.average_total.setText(getFormatterBeautyHeshrait(averageHsDouble.toString()));
        }

    }

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
        try {
            return data.size()+1;
        } catch (Exception ex) {
            return  0;
        }
    }
    public class WorkersHeaderViewHolder extends RecyclerView.ViewHolder{
        public TextView title_card, workers_total, reported_total, current_total, average_total;
        public RelativeLayout card_title_backround;
        public WorkersHeaderViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_header_worker_card, parent, false));
            title_card = (TextView)itemView.findViewById(R.id.card_title);
            workers_total = (TextView)itemView.findViewById(R.id.workers_total);
            reported_total = (TextView)itemView.findViewById(R.id.reported_total);
            current_total = (TextView)itemView.findViewById(R.id.current_total);
            average_total = (TextView)itemView.findViewById(R.id.average_total);
            card_title_backround = (RelativeLayout)itemView.findViewById(R.id.card_title_backround);
        }
    }

    public class WorkersAdapterItemViewHolder extends RecyclerView.ViewHolder {
        public TextView card_title_name_worker, reported, current, average,
                valid, stale, invalid, last_seen;
        public RelativeLayout card_title_backround;


        public WorkersAdapterItemViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.workers_item_card, parent, false));
            reported = (TextView) itemView.findViewById(R.id.reported);
            current = (TextView) itemView.findViewById(R.id.current);
            average = (TextView) itemView.findViewById(R.id.average);
            valid = (TextView) itemView.findViewById(R.id.valid);
            stale = (TextView) itemView.findViewById(R.id.stale);
            invalid = (TextView) itemView.findViewById(R.id.invalid);
            last_seen = (TextView) itemView.findViewById(R.id.last_seen);

            card_title_name_worker = (TextView) itemView.findViewById(R.id.card_title_name_worker);
            card_title_backround = (RelativeLayout) itemView.findViewById(R.id.card_title_backround);
        }
    }
}
