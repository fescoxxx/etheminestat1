package com.rijsoft.ethermine.etherminestats.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rijsoft.ethermine.etherminestats.R;
import com.rijsoft.ethermine.etherminestats.model.payouts.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PayoutsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Data> data;
    private Context context;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public PayoutsAdapter(Context context, List<Data> data){
        this.data = data;
        this.context = context;
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


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM)
        {
            return new PayoutsItemViewHolder(LayoutInflater.from(parent.getContext()), parent);
        } else if (viewType == TYPE_HEADER) {
            return new PayoutsHeaderViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }


    public class PayoutsItemViewHolder extends RecyclerView.ViewHolder {

        public TextView ethPay, datePay, timePay, durationPay, txHash;
        public RelativeLayout card_title_backround;

        public PayoutsItemViewHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.payment_item_card, parent, false));
            ethPay = (TextView)itemView.findViewById(R.id.ethPay);
            datePay = (TextView)itemView.findViewById(R.id.datePay);
            timePay = (TextView)itemView.findViewById(R.id.timePay);
            durationPay = (TextView)itemView.findViewById(R.id.durationPay);
            txHash = (TextView)itemView.findViewById(R.id.txHash);
        }

    }

    public class PayoutsHeaderViewHolder extends RecyclerView.ViewHolder{
        public TextView title_card, unpaid;
        public RelativeLayout card_title_backround;
        public PayoutsHeaderViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_header_two_card, parent, false));
            title_card = (TextView)itemView.findViewById(R.id.card_title);
            unpaid = (TextView)itemView.findViewById(R.id.unpaid);
            card_title_backround = (RelativeLayout)itemView.findViewById(R.id.card_title_backround);
        }
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof PayoutsItemViewHolder) {
            int pos = position-1;
            PayoutsItemViewHolder item = (PayoutsItemViewHolder) holder;
            String ethPayStr;
            Double ethPayDouble;
            try {
                ethPayDouble = Double.valueOf(data.get(pos).getAmount())*0.000000000000000001;
                ethPayStr = String.format(Locale.US,"%.5g%n", ethPayDouble)+ " ETH";
            }
            catch (Exception ex) {
                ethPayStr = "0.0 ETH";
            }
            item.ethPay.setText(ethPayStr.replace("\n", ""));

            try {
                Long lastSeen1 = Long.valueOf(data.get(pos).getPaidOn());
                Long lastSeen2 = Long.valueOf(data.get(pos+1).getPaidOn());
                Date time1=new java.util.Date((long)lastSeen1*1000);
                Date time2=new java.util.Date((long)lastSeen2*1000);
                String dateF = new SimpleDateFormat("yyyy-MM-dd").format(time1);
                String timeF = new SimpleDateFormat("HH:mm:ss").format(time1);
                item.datePay.setText(dateF);
                item.timePay.setText(timeF);
                long milliseconds = time1.getTime() - time2.getTime();
                String setStr = String.valueOf(milliseconds / (60*60 * 1000)) + " h";
                item.durationPay.setText(setStr);
            } catch (Exception ex) {
                item.durationPay.setText("0");
            }
            Spanned textTxHash = Html.fromHtml("<a href='https://www.etherchain.org/tx/"
                    + data.get(pos).getTxHash()+"/'>" +data.get(pos).getTxHash().substring(0,40)+"...</a>");
            item.txHash.setText(textTxHash);
            item.txHash.setMovementMethod(LinkMovementMethod.getInstance());
        }  else  if(holder instanceof PayoutsHeaderViewHolder) {
            PayoutsHeaderViewHolder header = (PayoutsHeaderViewHolder) holder;
            header.title_card.setText("Payments");
            header.unpaid.setText("");

        }
    }

    @Override
    public int getItemCount() {
        try {
            return data.size()+1;
        } catch (Exception ex) {
            return 0;
        }
    }
}
