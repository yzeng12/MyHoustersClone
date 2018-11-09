package com.example.yzeng.myhoustersclone.homepage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yzeng.myhoustersclone.R;
import com.example.yzeng.myhoustersclone.pojo.PropertyListPOJO;

import java.util.List;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.ViewHolder>{

    private ClickListener clickListener;
    private List<PropertyListPOJO> propertyList;
    Context ctx;

    public PropertyListAdapter(List<PropertyListPOJO> propertyList, Context ctx) {
        this.propertyList = propertyList;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleitem_propertylistadapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        PropertyListPOJO propertyListPOJO = propertyList.get(position);
        viewHolder.tv_PropertyList.setText("Property Id : " + propertyListPOJO.getPropertyId() + "\n" +
                "Property Address : " + propertyListPOJO.getPropertyAddress() + "\n" +
                "Property City : " + propertyListPOJO.getPropertyCity() + "\n" +
                "Property State : " + propertyListPOJO.getPropertyState() + "\n" +
                "Property Country : " + propertyListPOJO.getPropertyCountry() + "\n" +
                "Property Status : " + propertyListPOJO.getPropertyStatus() + "\n" +
                "Property purchase price : " + propertyListPOJO.getPropertyPurchasePrice() + "\n" +
                "Property Mortgage : " + propertyListPOJO.getPropertyMortgageInfo());

    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_PropertyList;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv_PropertyList = itemView.findViewById(R.id.tv_propertyList);
        }

        @Override
        public void onClick(View v) {

            if(clickListener != null)
            {
                clickListener.itemClicked(v,getAdapterPosition());
            }
        }
    }


    public interface ClickListener{

        public void itemClicked(View view, int position);
    }
}
