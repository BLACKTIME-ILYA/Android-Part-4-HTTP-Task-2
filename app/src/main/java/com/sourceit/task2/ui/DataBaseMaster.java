package com.sourceit.task2.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sourceit.task2.ui.model.Country;
import com.sourceit.task2.utils.L;

import java.util.ArrayList;
import java.util.List;

import static com.sourceit.task2.ui.DataBaseCreator.CommunicationColumns.IDCOUNTRY;
import static com.sourceit.task2.ui.DataBaseCreator.CommunicationColumns.IDREGION;
import static com.sourceit.task2.ui.DataBaseCreator.CommunicationColumns.IDSUBREGION;
import static com.sourceit.task2.ui.DataBaseCreator.CommunicationColumns.TABLE_NAME_COMMUNICATIONS;
import static com.sourceit.task2.ui.DataBaseCreator.CountryCoulumns.CAPITAL;
import static com.sourceit.task2.ui.DataBaseCreator.CountryCoulumns.NAME;
import static com.sourceit.task2.ui.DataBaseCreator.CountryCoulumns.POPULATION;
import static com.sourceit.task2.ui.DataBaseCreator.CountryCoulumns.TABLE_NAME_COUNTRIES;
import static com.sourceit.task2.ui.DataBaseCreator.RegionColumns.REGION;
import static com.sourceit.task2.ui.DataBaseCreator.RegionColumns.TABLE_NAME_REGIONS;
import static com.sourceit.task2.ui.DataBaseCreator.SubregionColumns.SUBREGION;
import static com.sourceit.task2.ui.DataBaseCreator.SubregionColumns.TABLE_NAME_SUBREGIONS;

/**
 * Created by User on 29.02.2016.
 */
public class DataBaseMaster {
    private SQLiteDatabase database;
    private DataBaseCreator dbCreator;

    private static DataBaseMaster instance;

    private DataBaseMaster(Context context) {
        dbCreator = new DataBaseCreator(context);
        if (database == null || !database.isOpen()) {
            database = dbCreator.getWritableDatabase();
        }
    }

    public static DataBaseMaster getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseMaster(context);
        }
        return instance;
    }

    public void addType(String value, String type){
        ContentValues cv = new ContentValues();
        if (type.equals(REGION)) {
            cv.put(REGION, value);
            database.insert(TABLE_NAME_REGIONS, null, cv);
        } else if (type.equals(SUBREGION)){
            cv.put(SUBREGION, value);
            database.insert(TABLE_NAME_SUBREGIONS, null, cv);
        }
    }

    public void addType(Country country){
        ContentValues cv = new ContentValues();
        cv.put(NAME, country.name);
        cv.put(CAPITAL, country.capital);
        cv.put(POPULATION, country.population);
        database.insert(TABLE_NAME_COUNTRIES, null, cv);
    }

    public void addType(int idcountry, int idregion, int idsubregion){
        ContentValues cv = new ContentValues();
        cv.put(IDCOUNTRY, idcountry);
        cv.put(IDREGION, idregion);
        cv.put(IDSUBREGION, idsubregion);
        database.insert(TABLE_NAME_COMMUNICATIONS, null, cv);
    }

    public List<Country> getAllCountry() {
        final String MY_QUERY = "SELECT * FROM " + TABLE_NAME_COMMUNICATIONS + " INNER JOIN " + TABLE_NAME_COUNTRIES + " ON "
                + TABLE_NAME_COMMUNICATIONS + "." + "IDCOUNTRY" + " = " +
                TABLE_NAME_COUNTRIES + "." + "_ID" +
                " INNER JOIN " + TABLE_NAME_REGIONS + " ON "
                + TABLE_NAME_COMMUNICATIONS + "." + "IDREGION" + " = " +
                TABLE_NAME_REGIONS + "." + "_ID" +
                " INNER JOIN " + TABLE_NAME_SUBREGIONS + " ON "
                + TABLE_NAME_COMMUNICATIONS + "." + "IDSUBREGION" + " = " +
                TABLE_NAME_SUBREGIONS + "." + "_ID";

        Cursor cursor = database.rawQuery(MY_QUERY, null);
        List<Country> list = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Country country = new Country();
            country.name = cursor.getString(cursor.getColumnIndex(NAME));
            country.capital = cursor.getString(cursor.getColumnIndex(CAPITAL));
            country.population = cursor.getString(cursor.getColumnIndex(POPULATION));
            country.region = cursor.getString(cursor.getColumnIndex(REGION));
            country.subregion = cursor.getString(cursor.getColumnIndex(SUBREGION));

            L.d("name: " + country.name);
            L.d("capital: " + country.capital);
            L.d("subregion: " + country.subregion);
            list.add(country);
            cursor.moveToNext();
        }

        cursor.close();
        return list;
    }
}
