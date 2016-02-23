package com.sourceit.task2.ui.BankClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by User on 23.02.2016.
 */
public class SystemBank implements Parcelable {
    String sourceId;
    String date;
    public ArrayList<Bank> organizations;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sourceId);
        dest.writeString(this.date);
        dest.writeSerializable(this.organizations);
    }

    public SystemBank() {
    }

    private SystemBank(Parcel in) {
        this.sourceId = in.readString();
        this.date = in.readString();
        this.organizations = (ArrayList<Bank>) in.readSerializable();
    }

    public static final Creator<SystemBank> CREATOR = new Creator<SystemBank>() {
        public SystemBank createFromParcel(Parcel source) {
            return new SystemBank(source);
        }

        public SystemBank[] newArray(int size) {
            return new SystemBank[size];
        }
    };
}
