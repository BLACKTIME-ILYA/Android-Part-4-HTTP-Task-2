package com.sourceit.task2.ui;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by User on 17.02.2016.
 */
public class System implements Parcelable {
    String sourceId;
    String date;
    ArrayList<Bank> organizations;


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

    public System() {
    }

    private System(Parcel in) {
        this.sourceId = in.readString();
        this.date = in.readString();
        this.organizations = (ArrayList<Bank>) in.readSerializable();
    }

    public static final Creator<System> CREATOR = new Creator<System>() {
        public System createFromParcel(Parcel source) {
            return new System(source);
        }

        public System[] newArray(int size) {
            return new System[size];
        }
    };
}

class Bank implements Parcelable {

    String id;
    int oldId;
    int orgType;
    boolean branch;
    String title;
    String regionId;
    String cityId;
    String phone;
    String address;
    String link;
    Currencies currencies;


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

class Currencies implements Parcelable {
    Eur EUR;
    Rub RUB;
    Usd USD;


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

class Eur implements Parcelable {
    String ask;
    String bid;


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

class Rub implements Parcelable {
    String ask;
    String bid;


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

class Usd implements Parcelable {
    String ask;
    String bid;


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