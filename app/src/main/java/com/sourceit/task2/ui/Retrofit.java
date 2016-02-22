package com.sourceit.task2.ui;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by User on 17.02.2016.
 */
public class Retrofit {
    private static final String ENDPOINT = "https://api.privatbank.ua/p24api/pboffice?json&city=&address=";
    private static ApiInterface apiInterface;

    static {
        initialize();
    }

    interface ApiInterface {
        //        @Headers({
//                "Content-type: application/json"
//        })
        @GET("/")
        void getBanks(Callback<List<Bank>> callback);
    }

    public static void initialize() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        apiInterface = restAdapter.create(ApiInterface.class);
    }

    public static void getBanks(Callback<List<Bank>> callback) {
        apiInterface.getBanks(callback);
    }
}
