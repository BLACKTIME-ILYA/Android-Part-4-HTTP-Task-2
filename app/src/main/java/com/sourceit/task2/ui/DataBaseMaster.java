package com.sourceit.task2.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sourceit.task2.ui.model.Country;
import com.sourceit.task2.utils.L;

import java.util.ArrayList;
import java.util.List;

import static com.sourceit.task2.ui.DataBaseCreator.CountryCoulumns.NAME;
import static com.sourceit.task2.ui.DataBaseCreator.CountryCoulumns.TABLE_NAME_COUNTRIES;
import static com.sourceit.task2.ui.DataBaseCreator.TranslationsColumns.DE;
import static com.sourceit.task2.ui.DataBaseCreator.TranslationsColumns.ES;
import static com.sourceit.task2.ui.DataBaseCreator.TranslationsColumns.FR;
import static com.sourceit.task2.ui.DataBaseCreator.TranslationsColumns.IT;
import static com.sourceit.task2.ui.DataBaseCreator.TranslationsColumns.JA;
import static com.sourceit.task2.ui.DataBaseCreator.TranslationsColumns.TABLE_NAME_TRANSLATIONS;

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

    public void addCountry(Country country) {
        ContentValues cv = new ContentValues();
        ContentValues cv_translations = new ContentValues();

        cv.put(NAME, country.name);

        cv_translations.put(DE, country.translations.de);
        cv_translations.put(ES, country.translations.es);
        cv_translations.put(FR, country.translations.fr);
        cv_translations.put(JA, country.translations.ja);
        cv_translations.put(IT, country.translations.it);
        database.insert(TABLE_NAME_TRANSLATIONS, null, cv_translations);
        long id = database.insert(TABLE_NAME_COUNTRIES, null, cv);
        L.d("addCountry", String.valueOf(id));
    }

    public List<Country> getAllCountry() {
        final String MY_QUERY = "SELECT * FROM " + TABLE_NAME_COUNTRIES + " INNER JOIN " + TABLE_NAME_TRANSLATIONS + " ON "
                + TABLE_NAME_COUNTRIES + "." + "_ID" + " = " +
                TABLE_NAME_TRANSLATIONS + "." + "_ID";

        Cursor cursor = database.rawQuery(MY_QUERY, null);
        List<Country> list = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Country country = new Country();
            country.name = cursor.getString(cursor.getColumnIndex(NAME));
            country.translations.de = cursor.getString(cursor.getColumnIndex(DE));
            country.translations.es = cursor.getString(cursor.getColumnIndex(ES));
            country.translations.fr = cursor.getString(cursor.getColumnIndex(FR));
            country.translations.ja = cursor.getString(cursor.getColumnIndex(JA));
            country.translations.it = cursor.getString(cursor.getColumnIndex(IT));
            L.d("getAllCountries", "name: " + country.name);
            list.add(country);
            cursor.moveToNext();
        }

        cursor.close();
        return list;
    }
}
