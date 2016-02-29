package com.sourceit.task2.ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by User on 29.02.2016.
 */
public class DataBaseCreator extends SQLiteOpenHelper {

    public static final String DB_NAME = "countries_upd3";

    public static final int DB_VERSION = 1;

    public static class CountryCoulumns implements BaseColumns {
        public static final String TABLE_NAME_COUNTRIES = "countries";
        public static final String NAME = "name";
    }

    public static class TranslationsColumns implements BaseColumns {
        public static final String TABLE_NAME_TRANSLATIONS = "translations";
        public static final String DE = "de";
        public static final String ES = "es";
        public static final String FR = "fr";
        public static final String JA = "ja";
        public static final String IT = "it";
    }

    private static String CREATE_TABLE_COUNTRIES =
            "CREATE TABLE " + CountryCoulumns.TABLE_NAME_COUNTRIES +
                    " (" +
                    CountryCoulumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CountryCoulumns.NAME + " TEXT" + ");";

    private static String CREATE_TABLE_TRANSLATIONS =
            "CREATE TABLE " + TranslationsColumns.TABLE_NAME_TRANSLATIONS +
                    " (" +
                    TranslationsColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TranslationsColumns.DE + " TEXT, " +
                    TranslationsColumns.ES + " TEXT, " +
                    TranslationsColumns.FR + " TEXT, " +
                    TranslationsColumns.JA + " TEXT, " +
                    TranslationsColumns.IT + " TEXT" + ");";

    public DataBaseCreator(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COUNTRIES);
        db.execSQL(CREATE_TABLE_TRANSLATIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
