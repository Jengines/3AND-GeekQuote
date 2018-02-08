package com.geekquote.supinfo.model;

import java.util.Date;

public class Quote {
    String strQuote;
    int rating;
    Date creationDate;


    /* Getters && Setters */

    public String getStrQuote() {
        return strQuote;
    }

    public void setStrQuote(String strQuote) {
        this.strQuote = strQuote;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
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
