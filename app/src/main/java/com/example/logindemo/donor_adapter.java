package com.example.logindemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class donor_adapter extends RecyclerView .Adapter<donor_adapter.donorViewHolder>{
    Context context;
    ArrayList<donor> list;

    public donor_adapter(Context context,ArrayList<donor> list) {
        this.context=context;
        this.list = list;
    }

    @NonNull
    @Override
    public donorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new donorViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull donorViewHolder holder, int position) {
        donor donor_user= list.get(position);
        holder.name.setText(donor_user.getFullname());
        holder.bloodgr.setText(donor_user.getBloodgr());
        holder.status.setText(donor_user.getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DonorInfoVerify.class);
                intent.putExtra("fullname",donor_user.getFullname());
                intent.putExtra("bloodgr",donor_user.getBloodgr());
                intent.putExtra("address",donor_user.getAddress());
                intent.putExtra("mobile_var",donor_user.getMobile_var());
                intent.putExtra("healthissue",donor_user.getHealthissue());
                intent.putExtra("status",donor_user.getStatus());
                //intent.putExtra("donation_count",donor_user.getDonation_count());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class donorViewHolder extends RecyclerView.ViewHolder{

        TextView name,bloodgr,status;
        public donorViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.load_name);
            bloodgr=itemView.findViewById(R.id.load_bloodgr);
            status=itemView.findViewById(R.id.status);
        }
    }

}
