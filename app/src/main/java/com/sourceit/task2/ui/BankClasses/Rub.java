package com.sourceit.task2.ui.BankClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 23.02.2016.
 */
public class Rub implements Parcelable {
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

    public Rub() {
    }

    private Rub(Parcel in) {
        this.ask = in.readString();
        this.bid = in.readString();
    }

    public static final Creator<Rub> CREATOR = new Creator<Rub>() {
        public Rub createFromParcel(Parcel source) {
            return new Rub(source);
        }

        public Rub[] newArray(int size) {
            return new Rub[size];
        }
    };
}
