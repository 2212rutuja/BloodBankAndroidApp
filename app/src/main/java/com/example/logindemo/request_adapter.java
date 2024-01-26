package com.example.logindemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class request_adapter extends RecyclerView.Adapter<request_adapter.requestViewHolder>{
    Context context;
    ArrayList<receiver> list;

    public request_adapter(Context context, ArrayList<receiver> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public request_adapter.requestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_recr,parent,false);
        return new request_adapter.requestViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull requestViewHolder holder, int position) {
        receiver rec_user= list.get(position);
        holder.name.setText(rec_user.getFullname());
        holder.bloodgr.setText(rec_user.getBloodgr());
        holder.bloodunit.setText(rec_user.getBloodunit());
        holder.status.setText(rec_user.getStatus());
/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,RequestInfoVerify.class);
                intent.putExtra("fullname",rec_user.getFullname());
                intent.putExtra("bloodgr",rec_user.getBloodgr());
                intent.putExtra("address",rec_user.getAddress());
                intent.putExtra("mobile_var",rec_user.getMobile_var());
                intent.putExtra("bloodunit",rec_user.getBloodunit());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });*/

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class requestViewHolder extends RecyclerView.ViewHolder{

        TextView name,bloodgr,status,bloodunit;
        public requestViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.loadname);
            bloodgr=itemView.findViewById(R.id.load_blood);
            bloodunit=itemView.findViewById(R.id.count);
            status=itemView.findViewById(R.id.status);
        }
    }

}
