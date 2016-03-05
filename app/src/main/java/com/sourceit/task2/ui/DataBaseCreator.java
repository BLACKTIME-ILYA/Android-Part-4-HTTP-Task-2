package com.sourceit.task2.ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by User on 29.02.2016.
 */
public class DataBaseCreator extends SQLiteOpenHelper {

    public static final String DB_NAME = "countries";

    public static final int DB_VERSION = 1;

    public static class CountryCoulumns implements BaseColumns {
        public static final String TABLE_NAME_COUNTRIES = "countries";
        public static final String NAME = "name";
        public static final String CAPITAL = "capital";
        public static final String POPULATION = "population";
    }

    public static class RegionColumns implements BaseColumns {
        public static final String TABLE_NAME_REGIONS = "regions";
        public static final String REGION = "region";
    }

    public static class SubregionColumns implements BaseColumns {
        public static final String TABLE_NAME_SUBREGIONS = "subregions";
        public static final String SUBREGION = "subregion";
    }

    public static class CommunicationColumns implements BaseColumns {
        public static final String TABLE_NAME_COMMUNICATIONS = "communications";
        public static final String IDCOUNTRY = "idcountry";
        public static final String IDREGION = "idregion";
        public static final String IDSUBREGION = "idsubregion";
    }

    private static String CREATE_TABLE_COUNTRIES =
            "CREATE TABLE " + CountryCoulumns.TABLE_NAME_COUNTRIES +
                    " (" +
                    CountryCoulumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CountryCoulumns.NAME + " TEXT, " +
                    CountryCoulumns.CAPITAL + " TEXT, " +
                    CountryCoulumns.POPULATION + " TEXT" + ");";

    private static String CREATE_TABLE_REGIONS =
            "CREATE TABLE " + RegionColumns.TABLE_NAME_REGIONS +
                    " (" +
                    RegionColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RegionColumns.REGION + " TEXT" + ");";

    private static String CREATE_TABLE_SUBREGIONS =
            "CREATE TABLE " + SubregionColumns.TABLE_NAME_SUBREGIONS +
                    " (" +
                    SubregionColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SubregionColumns.SUBREGION + " TEXT" + ");";

    private static String CREATE_TABLE_COMMUNICATIONS =
            "CREATE TABLE " + CommunicationColumns.TABLE_NAME_COMMUNICATIONS +
                    " (" +
                    CommunicationColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CommunicationColumns.IDCOUNTRY + " INTEGER, " +
                    CommunicationColumns.IDREGION + " INTEGER, " +
                    CommunicationColumns.IDSUBREGION + " INTEGER" + ");";

    public DataBaseCreator(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COUNTRIES);
        db.execSQL(CREATE_TABLE_REGIONS);
        db.execSQL(CREATE_TABLE_SUBREGIONS);
        db.execSQL(CREATE_TABLE_COMMUNICATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
