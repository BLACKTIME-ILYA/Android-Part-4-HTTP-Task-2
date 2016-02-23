package com.sourceit.task2.ui.BankClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 23.02.2016.
 */
public class Currencies implements Parcelable {
    public Eur EUR;
    public Rub RUB;
    public Usd USD;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.EUR, flags);
        dest.writeParcelable(this.USD, flags);
        dest.writeParcelable(this.RUB, flags);
    }

    public Currencies() {
    }

    private Currencies(Parcel in) {
        this.EUR = in.readParcelable(Eur.class.getClassLoader());
        this.USD = in.readParcelable(Usd.class.getClassLoader());
        this.RUB = in.readParcelable(Rub.class.getClassLoader());
    }

    public static final Creator<Currencies> CREATOR = new Creator<Currencies>() {
        public Currencies createFromParcel(Parcel source) {
            return new Currencies(source);
        }

        public Currencies[] newArray(int size) {
            return new Currencies[size];
        }
    };
}
