package com.sourceit.task2.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.sourceit.task2.R;
import com.sourceit.task2.ui.BankClasses.Bank;
import com.sourceit.task2.utils.L;

import java.util.ArrayList;


public class MyRecyclerViewList extends RecyclerView.Adapter<MyRecyclerViewList.ViewHolder> {
    ArrayList<Bank> banks;

    public MyRecyclerViewList(ArrayList<Bank> banks) {
        this.banks = banks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bankTitle.setText(banks.get(position).title);
    }

    @Override
    public int getItemCount() {
        return banks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bankTitle;
        private CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            bankTitle = (TextView) itemView.findViewById(R.id.banklist_title);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox_bank);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        MainActivity.checkedList.add(banks.get(getAdapterPosition()));
                        L.d("array: " + MainActivity.checkedList.size());
                        L.d("checked");
                    } else {
                        MainActivity.checkedList.remove(banks.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
}
