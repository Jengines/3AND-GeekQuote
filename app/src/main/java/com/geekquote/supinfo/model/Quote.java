package com.geekquote.supinfo.model;

import java.io.Serializable;
import java.util.Date;

public class Quote implements Serializable{
    String strQuote;
    Double rating = 0d;
    Date creationDate;

    /* Getters && Setters */

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
}
