package com.sourceit.task2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sourceit.task2.R;
import com.sourceit.task2.ui.BankClasses.Bank;
import com.sourceit.task2.ui.BankClasses.SystemBank;
import com.sourceit.task2.utils.L;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements CheckListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private SystemBank root;
    private Button infoButton;
    private Intent intent;

    static ArrayList<Bank> checkedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoButton = (Button) findViewById(R.id.button_info);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        checkedList = new ArrayList<>();
        intent = new Intent(getApplicationContext(), Main2Activity.class);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkedList.isEmpty()) {
                    L.d("bank: " + checkedList.get(0).currencies.EUR.ask);
                    intent.putParcelableArrayListExtra("banks", checkedList);
                    startActivity(intent);
                }
            }
        });

        Retrofit.getBanks(new Callback<SystemBank>() {
            @Override
            public void success(SystemBank system, Response response) {
                root = system;
                setAdapter();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "something went wrong :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void setAdapter() {
        MyRecyclerViewList myRecyclerViewList = new MyRecyclerViewList(root.organizations);
        myRecyclerViewList.setListener(this);
        recyclerView.setAdapter(myRecyclerViewList);
    }

    @Override
    public void checkAdd(int position) {
        checkedList.add(root.organizations.get(position));
        L.d("check add position" + position);
    }

    @Override
    public void checkRemove(int position) {
        checkedList.remove(root.organizations.get(position));
        L.d("check remove position" + position);
    }
}
