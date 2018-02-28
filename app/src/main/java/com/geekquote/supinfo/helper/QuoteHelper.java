package com.geekquote.supinfo.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.geekquote.supinfo.model.Quote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jérôme on 28/02/2018.
 */

public class QuoteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "geekquotes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "quote";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "content TEXT NOT NULL," +
                    "rating DOUBLE NOT NULL," +
                    "creation_date TEXT NOT NULL);";

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public QuoteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Quote doInsert(Quote quote) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("content", quote.getStrQuote());
        values.put("rating", quote.getRating());
        values.put("creation_date", dateFormat.format(quote.getCreationDate()));

        long idInserted = db.insert(TABLE_NAME, null, values);

        quote.setId((int) idInserted);

        return quote;
    }


    public Quote doUpdate(Quote quote) {
        if (quote.getId() != 0) {
            SQLiteDatabase db = getReadableDatabase();

            ContentValues values = new ContentValues();
            values.put("content", quote.getStrQuote());
            values.put("rating", quote.getRating());
            values.put("creation_date", dateFormat.format(quote.getCreationDate()));

            String[] whereArgs = {String.valueOf(quote.getId())};

            db.update(TABLE_NAME, values, "id=?", whereArgs);

            return quote;
        } else {
            return doInsert(quote);
        }
    }


    public List<Quote> doQuery() throws ParseException {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {"id", "content", "rating", "creation_date"};

        Cursor result = db.query(TABLE_NAME, columns, null,
                null, null, null, null);
        List<Quote> quotes = new ArrayList<>();
        result.moveToFirst();
        while (!result.isAfterLast()) {
            Quote quote = new Quote();
            quote.setId(result.getInt(0));
            quote.setStrQuote(result.getString(1));
            quote.setRating(result.getDouble(2));
            quote.setCreationDate(dateFormat.parse(result.getString(3)));
            quotes.add(quote);
            result.moveToNext();
        }
        result.close();
        return quotes;
    }
}
