package com.sourceit.task2.ui;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by User on 17.02.2016.
 */
public class Retrofit {
    private static final String ENDPOINT = "http://resources.finance.ua/ru/public/";
    private static ApiInterface apiInterface;

    static {
        initialize();
    }

    interface ApiInterface {
        //        @Headers({
//                "Content-type: application/json"
//        })
        @GET("/currency-cash.json")
        void getBanks(Callback<System> callback);
    }

    public static void initialize() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        apiInterface = restAdapter.create(ApiInterface.class);
    }

    public static void getBanks(Callback<System> callback) {
        apiInterface.getBanks(callback);
    }
}
