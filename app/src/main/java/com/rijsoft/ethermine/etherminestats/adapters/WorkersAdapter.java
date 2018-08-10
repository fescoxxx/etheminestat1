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

import java.util.List;

public class WorkersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    List<Data> data;
    private Context context;

    public WorkersAdapter(Context context,List<Data> data ){
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkersAdapterItemViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        String workerName ="Worker: "+data.get(position).getWorker();
        WorkersAdapterItemViewHolder item = (WorkersAdapterItemViewHolder)holder;
        item.card_title_name_worker.setText(workerName);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class WorkersAdapterItemViewHolder extends RecyclerView.ViewHolder {
        public TextView card_title_name_worker;
        public RelativeLayout card_title_backround;


        public WorkersAdapterItemViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.workers_item_card, parent, false));

            card_title_name_worker = (TextView) itemView.findViewById(R.id.card_title_name_worker);
            card_title_backround = (RelativeLayout) itemView.findViewById(R.id.card_title_backround);
        }
    }
}
