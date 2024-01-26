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

public class receiver_adapter extends RecyclerView.Adapter<receiver_adapter.recrViewHolder>{
    Context context;
    ArrayList<receiver> list2;

    public receiver_adapter(Context context, ArrayList<receiver> list) {
        this.context=context;
        this.list2 = list;
    }

    @NonNull
    @Override
    public recrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_recr,parent,false);
        return new recrViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull recrViewHolder holder, int position) {
        receiver rec_user= list2.get(position);
        holder.name.setText(rec_user.getFullname());
        holder.bloodgr.setText(rec_user.getBloodgr());
        holder.bloodunit.setText(rec_user.getBloodunit());
        holder.status.setText(rec_user.getStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,RequestInfoVerify.class);
                intent.putExtra("fullname",rec_user.getFullname());
                intent.putExtra("bloodgr",rec_user.getBloodgr());
                intent.putExtra("address",rec_user.getAddress());
                intent.putExtra("mobile_var",rec_user.getMobile_var());
                intent.putExtra("bloodunit",rec_user.getBloodunit());
                //intent.putExtra("status",rec_user.getStatus());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public static class recrViewHolder extends RecyclerView.ViewHolder{

        TextView name,bloodgr,status,bloodunit;
        public recrViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.loadname);
            bloodgr=itemView.findViewById(R.id.load_blood);
            bloodunit=itemView.findViewById(R.id.count);
            status=itemView.findViewById(R.id.status);
        }
    }

}

