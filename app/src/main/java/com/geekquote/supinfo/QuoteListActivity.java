package com.geekquote.supinfo;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.geekquote.supinfo.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuoteListActivity extends AppCompatActivity {
    private List<Quote> quoteList = new ArrayList<>();
    private final static int duration = Toast.LENGTH_LONG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        Resources resources = getResources();

        Context context = getApplicationContext();

        String[] quotesFromRessources = resources.getStringArray(R.array.quote_array);

        for (String quotesFromRessource : quotesFromRessources) {
            addQuote(quotesFromRessource);
        }

        //Toast toast = Toast.makeText(context, "List now contains " + quoteList.size() + " quotes !", duration);
        //toast.show();
    }

    private void addQuote(String strQuote) {
        Quote quote = new Quote();
        quote.setStrQuote(strQuote);

        quoteList.add(quote);

        //Log.d("Add_Quote", "New quote with str : " + quote.getStrQuote());
    }
}
