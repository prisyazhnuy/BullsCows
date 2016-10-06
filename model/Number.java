package com.prisyazhnuy.bullscows.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Number implements Parcelable {

    private List<Integer> mNumber;
    private int mSize;

    public Number(int size) {
        this.mSize = size;
        this.mNumber = new ArrayList<>(mSize);
    }

    private Number(Parcel in) {
        mSize = in.readInt();
    }

    public static final Creator<Number> CREATOR = new Creator<Number>() {
        @Override
        public Number createFromParcel(Parcel in) {
            return new Number(in);
        }

        @Override
        public Number[] newArray(int size) {
            return new Number[size];
        }
    };

    public int getCows(Number attempt) {
        int result = 0;
        for (int i = 0; i < mSize; i++) {
            for (int y = 0; y < mSize ; y++) {
                if (mNumber.get(i).equals(attempt.get(y)) && i != y) {
                    result ++;
                }
            }
        }
        return result;
    }
    
    public int getBulls(Number attempt) {
        int result = 0;
        for (int i = 0; i < mSize; i++) {
            for (int y = 0; y < mSize ; y++) {
                if (mNumber.get(i).equals(attempt.get(y)) && i == y) {
                    result ++;
                }
            }
        }
        return result;
    }

    private int get(int index) {
        return mNumber.get(index);
    }

    public void addDigit(int digit) {
        mNumber.add(digit);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int digit : mNumber) {
            result.append(digit);
        }
        return result.toString();
    }
    
    public void generateNumber() {
        while(mNumber.size() < mSize) {
            int randomDigit = (int) (Math.random() * 10);
            if (!mNumber.contains(randomDigit)) {
                mNumber.add(randomDigit);
            }
        }
    }

    public void clear() {
        mNumber.clear();
    }

    public void remoteLastDigit() {
        if(mNumber.size() != 0) {
            mNumber.remove(mNumber.size() - 1);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mSize);
        parcel.writeArray(mNumber.toArray());
    }

    public List<Integer> getDigits() {
        return mNumber;
    }

    public int size() {
        return mNumber.size();
    }
}
