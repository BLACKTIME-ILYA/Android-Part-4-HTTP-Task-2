package com.sourceit.task2.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sourceit.task2.R;

import java.util.ArrayList;


public class MyRecyclerViewInfo extends RecyclerView.Adapter<MyRecyclerViewInfo.ViewHolder> {
    ArrayList<Bank> banks;

    public MyRecyclerViewInfo(ArrayList<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bankTitle.setText(banks.get(position).name);
        holder.bankCity.setText(banks.get(position).city);
        holder.bankAddress.setText(banks.get(position).address);
        holder.bankPhone.setText(banks.get(position).phone);
    }

    @Override
    public int getItemCount() {
        return banks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bankTitle;
        private TextView bankCity;
        private TextView bankAddress;
        private TextView bankPhone;


        public ViewHolder(View itemView) {
            super(itemView);
            bankTitle = (TextView) itemView.findViewById(R.id.bank_name);
            bankCity = (TextView) itemView.findViewById(R.id.bank_city);
            bankAddress = (TextView) itemView.findViewById(R.id.bank_address);
            bankPhone = (TextView) itemView.findViewById(R.id.bank_phone);
        }
    }
}
