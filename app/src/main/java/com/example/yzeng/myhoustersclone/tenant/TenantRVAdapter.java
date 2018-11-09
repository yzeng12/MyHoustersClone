package com.example.yzeng.myhoustersclone.tenant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yzeng.myhoustersclone.R;

import java.util.List;

public class TenantRVAdapter extends RecyclerView.Adapter<TenantRVAdapter.ViewHolder> {

    List<TenantPOJO> mList;

    public TenantRVAdapter(List<TenantPOJO> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tenant_adapter_layout,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        TenantPOJO tenantPOJO = mList.get(position);
        viewHolder.tv_name.setText(tenantPOJO.getTenantname());
        viewHolder.tv_email.setText(tenantPOJO.getTenantemail());
        viewHolder.tv_mobile.setText(tenantPOJO.getTenantmobile());
        viewHolder.tv_propertyid.setText(tenantPOJO.getPropertyid());
        viewHolder.tv_address.setText(tenantPOJO.getTenantaddress());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv_name, tv_email, tv_mobile, tv_propertyid, tv_address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.imageview_productimage);
            tv_name = itemView.findViewById(R.id.tv_tenantname);
            tv_email = itemView.findViewById(R.id.tv_tenant_propertyid);
            tv_mobile = itemView.findViewById(R.id.tv_tenant_mobile);
            tv_propertyid = itemView.findViewById(R.id.tv_tenant_email);
            tv_address = itemView.findViewById(R.id.tv_tenant_address);

        }
    }
}
