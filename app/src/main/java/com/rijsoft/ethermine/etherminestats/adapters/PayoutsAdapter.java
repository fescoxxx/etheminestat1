package com.rijsoft.ethermine.etherminestats.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rijsoft.ethermine.etherminestats.R;
import com.rijsoft.ethermine.etherminestats.model.payouts.Data;

import java.util.List;

public class PayoutsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Data> data;
    private Context context;

    public PayoutsAdapter(Context context, List<Data> data){
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PayoutsItemHeaderViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }


    public class PayoutsItemHeaderViewHolder extends RecyclerView.ViewHolder {

        public TextView ethPay, datePay, timePay, durationPay, txHash;
        public RelativeLayout card_title_backround;

        public PayoutsItemHeaderViewHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.payment_item_card, parent, false));
            ethPay = (TextView)itemView.findViewById(R.id.ethPay);
            datePay = (TextView)itemView.findViewById(R.id.datePay);
            timePay = (TextView)itemView.findViewById(R.id.timePay);
            durationPay = (TextView)itemView.findViewById(R.id.durationPay);
            txHash = (TextView)itemView.findViewById(R.id.txHash);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PayoutsItemHeaderViewHolder) {
            PayoutsItemHeaderViewHolder item = (PayoutsItemHeaderViewHolder) holder;
            String workerName ="Amount: "+data.get(position).getAmount();
            item.datePay.setText(workerName);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
