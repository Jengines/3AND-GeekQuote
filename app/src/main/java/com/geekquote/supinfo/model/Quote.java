package com.geekquote.supinfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Quote implements Parcelable {
    int id = 0;
    String strQuote;
    Double rating = 0d;
    Date creationDate;

    public Quote() {
    }

    private Quote(Parcel in) {
        id = in.readInt();
        strQuote = in.readString();
        rating = in.readDouble();
        creationDate = (Date) in.readSerializable();
    }

    /* Getters && Setters */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrQuote() {
        return strQuote;
    }

    public void setStrQuote(String strQuote) {
        this.strQuote = strQuote;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    // When we associate a List of Object<> to ArrayAdapter,
    // the toString() method is used to display element on the screen
    @Override
    public String toString() {
        return strQuote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(strQuote);
        out.writeDouble(rating);
        out.writeSerializable(creationDate);
    }

    public static final Parcelable.Creator<Quote> CREATOR = new Parcelable.Creator<Quote>() {
        public Quote createFromParcel(Parcel in) {
            return new Quote(in);
        }

        public Quote[] newArray(int size) {
            return new Quote[size];
        }
    };
}
