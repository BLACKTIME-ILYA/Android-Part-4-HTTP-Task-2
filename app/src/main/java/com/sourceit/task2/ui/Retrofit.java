package com.sourceit.task2.ui;

import com.sourceit.task2.ui.model.Country;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by User on 17.02.2016.
 */
public class Retrofit {
    private static final String ENDPOINT = "https://restcountries.eu/rest/v1/all";
    private static ApiInterface apiInterface;

    static {
        initialize();
    }

    interface ApiInterface {
        //        @Headers({
//                "Content-type: application/json"
//        })
        @GET("/")
        void getBanks(Callback<List<Country>> callback);
    }

    public static void initialize() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        apiInterface = restAdapter.create(ApiInterface.class);
    }

    public static void getBanks(Callback<List<Country>> callback) {
        apiInterface.getBanks(callback);
    }
}
