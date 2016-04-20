package com.sourceit.task2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sourceit.task2.R;
import com.sourceit.task2.ui.model.Country;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (DataBaseMaster.getInstance(this).getAllCountry().isEmpty()) {
            Retrofit.getCountries(new Callback<List<Country>>() {
                @Override
                public void success(List<Country> countries, Response response) {
                    countryList = countries;

                    setAdapter();

                    for (Country country : countries) {
                        DataBaseMaster.getInstance(getApplicationContext()).addCountry(country);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(), "something went wrong :(", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            countryList = DataBaseMaster.getInstance(this).getAllCountry();
            setAdapter();
        }
    }

    private void setAdapter() {
        recyclerView.setAdapter(new MyRecyclerViewInfo((ArrayList) countryList));
    }
}
