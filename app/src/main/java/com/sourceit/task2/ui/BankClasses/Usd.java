package com.sourceit.task2.ui.BankClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 23.02.2016.
 */
public class Usd implements Parcelable {
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

    public Usd() {
    }

    private Usd(Parcel in) {
        this.ask = in.readString();
        this.bid = in.readString();
    }

    public static final Creator<Usd> CREATOR = new Creator<Usd>() {
        public Usd createFromParcel(Parcel source) {
            return new Usd(source);
        }

        public Usd[] newArray(int size) {
            return new Usd[size];
        }
    };
}
