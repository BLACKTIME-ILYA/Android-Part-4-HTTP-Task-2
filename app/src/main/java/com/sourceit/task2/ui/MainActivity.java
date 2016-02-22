package com.sourceit.task2.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sourceit.task2.R;
import com.sourceit.task2.utils.L;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private static final int MILISEC_IN_SEC = 1000;
    private static final int SEC_IN_MIN = 60;
    private static final int DAY_HOURS = 24;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Bank> banksList;

    private String fileName;
    private Gson gson;
    private boolean write;
    private final String SAVED_TIME = "saved time";
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        fileName = getString(R.string.fileName_string);
        gson = new Gson();
        sp = getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.d("onResume");
        write = false;
        if (!fileExistance(fileName)) {
            L.d("!file exist");
            retrofit();
        } else {
            L.d("file exist");
            L.d("time: " + (System.currentTimeMillis() - sp.getLong(SAVED_TIME, 0)) / MILISEC_IN_SEC / SEC_IN_MIN / SEC_IN_MIN);
            if (((System.currentTimeMillis() - sp.getLong(SAVED_TIME, 0)) / MILISEC_IN_SEC / SEC_IN_MIN / SEC_IN_MIN) >= DAY_HOURS) {
                retrofit();
            } else {
                new MyAsyncTask().execute();
            }
        }
    }

    private boolean fileExistance(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    private void setAdapter() {
        recyclerView.setAdapter(new MyRecyclerViewInfo((ArrayList) banksList));
    }

    private void retrofit() {
        Retrofit.getBanks(new Callback<List<Bank>>() {
            @Override
            public void success(List<Bank> banks, Response response) {
                banksList = banks;
                setAdapter();
                write = true;
                new MyAsyncTask().execute();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "something went wrong :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            if (write) {
                String jsonBanks = gson.toJson(banksList);
                try {
                    FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fos.write(jsonBanks.getBytes());
                    fos.close();

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putLong(SAVED_TIME, System.currentTimeMillis());
                    editor.apply();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    FileInputStream fis = openFileInput(fileName);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                    StringBuilder text = new StringBuilder();
                    String line;
                    try {
                        while ((line = reader.readLine()) != null) {
                            text.append(line);
                        }
                        fis.close();
                        Type collectionType = new TypeToken<List<Bank>>() {
                        }.getType();
                        banksList = gson.fromJson(text.toString(), collectionType);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (!write) {
                setAdapter();
            }
        }
    }
}
