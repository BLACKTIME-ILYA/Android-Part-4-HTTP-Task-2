package com.sourceit.task2.ui.BankClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 23.02.2016.
 */
public class Eur implements Parcelable {
    public String ask;
    public String bid;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ask);
        dest.writeString(this.bid);
    }

    public Eur() {
    }

    private Eur(Parcel in) {
        this.ask = in.readString();
        this.bid = in.readString();
    }

    public static final Creator<Eur> CREATOR = new Creator<Eur>() {
        public Eur createFromParcel(Parcel source) {
            return new Eur(source);
        }

        public Eur[] newArray(int size) {
            return new Eur[size];
        }
    };
}
