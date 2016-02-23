package com.sourceit.task2.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sourceit.task2.R;
import com.sourceit.task2.ui.BankClasses.Bank;

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
        holder.bankTitle.setText(banks.get(position).title);
        holder.currencyEurBuy.setText(banks.get(position).currencies.EUR.bid);
        holder.currencyEurSale.setText(banks.get(position).currencies.EUR.ask);
        holder.currencyUsdBuy.setText(banks.get(position).currencies.USD.bid);
        holder.currencyUsdSale.setText(banks.get(position).currencies.USD.ask);
        holder.currencyRubBuy.setText((banks.get(position).currencies.RUB.bid));
        holder.currencyRubSale.setText((banks.get(position).currencies.RUB.ask));
    }

    @Override
    public int getItemCount() {
        return banks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bankTitle;
        private TextView currencyEurBuy;
        private TextView currencyEurSale;
        private TextView currencyUsdBuy;
        private TextView currencyUsdSale;
        private TextView currencyRubBuy;
        private TextView currencyRubSale;


        public ViewHolder(View itemView) {
            super(itemView);
            bankTitle = (TextView) itemView.findViewById(R.id.bank_title);
            currencyEurBuy = (TextView) itemView.findViewById(R.id.currency_eur_buy_value);
            currencyEurSale = (TextView) itemView.findViewById(R.id.currency_eur_sale_value);
            currencyUsdBuy = (TextView) itemView.findViewById(R.id.currency_usd_buy_value);
            currencyUsdSale = (TextView) itemView.findViewById(R.id.currency_usd_sale_value);
            currencyRubBuy = (TextView) itemView.findViewById(R.id.currency_rub_buy_value);
            currencyRubSale = (TextView) itemView.findViewById(R.id.currency_rub_sale_value);
        }
    }
}
