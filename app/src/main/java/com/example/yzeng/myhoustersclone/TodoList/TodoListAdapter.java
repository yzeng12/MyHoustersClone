package com.example.yzeng.myhoustersclone.TodoList;

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

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ProductListViewHolder> implements View.OnClickListener{
    private Context context;
    private List<DataBaseTodoList> List;
    private OnItemClickListener onItemClickListener;

    public TodoListAdapter(Context context, List<DataBaseTodoList> List) {
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
            View view = LayoutInflater.from(context).inflate(R.layout.item_document, parent, false);
            view.setOnClickListener(this);
            holder = new ProductListViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        if(List.size()>0){
     /*   holder.tv_name.setText("Document Name: " + List.get(position).getName());
        holder.tv_type.setText("Document Type: " + List.get(position).getType());
  */      holder.tv_click.setText("Click Item for Detail");
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
        TextView tv_name, tv_type,tv_click;
        Context context;
        public ProductListViewHolder(@NonNull View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_document_detail_name);
            tv_type = view.findViewById(R.id.tv_document_detail_type);
            tv_click = view.findViewById(R.id.tv_document_detail_clickfordetail);
        }
    }
}
