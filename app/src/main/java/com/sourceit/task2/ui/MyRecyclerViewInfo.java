package com.sourceit.task2.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sourceit.task2.R;
import com.sourceit.task2.ui.model.Country;

import java.util.ArrayList;


public class MyRecyclerViewInfo extends RecyclerView.Adapter<MyRecyclerViewInfo.ViewHolder> {
    ArrayList<Country> countries;

    public MyRecyclerViewInfo(ArrayList<Country> countries) {
        this.countries = countries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.countryName.setText(countries.get(position).name);
        holder.translationDe.setText(countries.get(position).translations.de);
        holder.translationEs.setText(countries.get(position).translations.es);
        holder.translationFr.setText(countries.get(position).translations.fr);
        holder.translationJa.setText(countries.get(position).translations.ja);
        holder.translationIt.setText(countries.get(position).translations.it);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView countryName;
        private TextView translationDe;
        private TextView translationEs;
        private TextView translationFr;
        private TextView translationJa;
        private TextView translationIt;

        public ViewHolder(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.country_name);
            translationDe = (TextView) itemView.findViewById(R.id.translation_de_value);
            translationEs = (TextView) itemView.findViewById(R.id.translation_es_value);
            translationFr = (TextView) itemView.findViewById(R.id.translation_fr_value);
            translationJa = (TextView) itemView.findViewById(R.id.translation_ja_value);
            translationIt = (TextView) itemView.findViewById(R.id.translation_it_value);
        }
    }
}
