package com.prisyazhnuy.bullscows.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Attempt implements Parcelable {

    private final String mNumber;
    private final int mCows;
    private final int mBulls;
    private final int mNumAttempt;

    public Attempt(String number, int cows, int bulls, int numAttempt) {
        this.mNumber = number;
        this.mBulls = bulls;
        this.mCows =cows;
        this.mNumAttempt = numAttempt;
    }

    private Attempt(Parcel in) {
        mNumber = in.readString();
        mCows = in.readInt();
        mBulls = in.readInt();
        mNumAttempt = in.readInt();
    }

    public static final Creator<Attempt> CREATOR = new Creator<Attempt>() {
        @Override
        public Attempt createFromParcel(Parcel in) {
            return new Attempt(in);
        }

        @Override
        public Attempt[] newArray(int size) {
            return new Attempt[size];
        }
    };

    public String getNumber() {
        return mNumber;
    }

    public int getCows() {
        return mCows;
    }

    public int getBulls() {
        return mBulls;
    }

    public int getNumAttempt() {
        return mNumAttempt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mNumAttempt);
        parcel.writeString(mNumber);
        parcel.writeInt(mCows);
        parcel.writeInt(mBulls);
    }
}
