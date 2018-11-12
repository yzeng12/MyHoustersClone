package com.example.yzeng.myhoustersclone.transaction;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yzeng.myhoustersclone.R;

import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.ViewHolder> {

    private List<DataBaseTransaction> list;
    private Context context;

    public TransactionListAdapter(Context context,List<DataBaseTransaction> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaction, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if(list.size()>0) {
            viewHolder.tv.setText(
                    "Summary: " + list.get(position).getSummary() + "\n" +
                    "Description: " + list.get(position).getDescription() + "\n" +
//                    "Propery: " + list.get(position).getProperty() + "\n" +
                    "Type: " + list.get(position).getType() + "\n" +
                    "Amount: " + list.get(position).getAmount() + "\n" +
                    "Date: " + list.get(position).getDate() + "\n"

            );
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_transaction_detail);
        }
    }
}
