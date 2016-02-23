package com.sourceit.task2.ui.BankClasses;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 23.02.2016.
 */
public class Bank implements Parcelable {

    String id;
    int oldId;
    int orgType;
    boolean branch;
    public String title;
    String regionId;
    String cityId;
    String phone;
    String address;
    String link;
    public Currencies currencies;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.oldId);
        dest.writeInt(this.orgType);
        dest.writeByte(branch ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeString(this.regionId);
        dest.writeString(this.cityId);
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeString(this.link);
        dest.writeParcelable(this.currencies, flags);
    }

    public Bank() {
    }

    private Bank(Parcel in) {
        this.id = in.readString();
        this.oldId = in.readInt();
        this.orgType = in.readInt();
        this.branch = in.readByte() != 0;
        this.title = in.readString();
        this.regionId = in.readString();
        this.cityId = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.link = in.readString();
        this.currencies = in.readParcelable(Currencies.class.getClassLoader());
    }

    public static final Creator<Bank> CREATOR = new Creator<Bank>() {
        public Bank createFromParcel(Parcel source) {
            return new Bank(source);
        }

        public Bank[] newArray(int size) {
            return new Bank[size];
        }
    };
}
