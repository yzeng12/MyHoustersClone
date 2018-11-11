package com.example.yzeng.myhoustersclone.trip;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yzeng.myhoustersclone.R;

import java.util.List;

//import com.squareup.picasso.Picasso;

public class TriplistAdapter extends RecyclerView.Adapter<TriplistAdapter.ProductListViewHolder> implements View.OnClickListener{
    private Context context;
    private List<DataBaseTripList> List;
    private OnItemClickListener onItemClickListener;

    public TriplistAdapter(Context context, List<DataBaseTripList> List) {
        this.context = context;
        this.List = List;
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        ProductListViewHolder holder;
        if(List.size()<=0){
            View view = LayoutInflater.from(context).inflate(R.layout.emptylayout, parent, false);
            holder = new ProductListViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_trip, parent, false);
            view.setOnClickListener(this);
            holder = new ProductListViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        if(List.size()>0){
        holder.tv.setText( List.get(position).toString()
        );


       // Picasso.with(context).load(List.get(position).getImage()).placeholder(context.getResources().getDrawable(R.drawable.photonotavailable1)).into(holder.iv);
        holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return List.size()>0?  List.size() : 1;
    }
    @Override
    public int getItemViewType(int position) {
        if(List.size() <= 0){
            return -1;
        }
        return super.getItemViewType( position );
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if(onItemClickListener != null){
            onItemClickListener.onItemClick(v, (Integer)v.getTag());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public class ProductListViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        Context context;
        public ProductListViewHolder(@NonNull View view) {
            super(view);
            tv = view.findViewById(R.id.tv_trip_detail);

        }
    }
}
