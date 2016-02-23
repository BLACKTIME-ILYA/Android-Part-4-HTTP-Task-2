package com.sourceit.task2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sourceit.task2.R;
import com.sourceit.task2.ui.BankClasses.Bank;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Bank> checkedBanks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_listinfo);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        checkedBanks = getIntent().getParcelableArrayListExtra("banks");

        recyclerView.setAdapter(new MyRecyclerViewInfo(checkedBanks));
    }
}
