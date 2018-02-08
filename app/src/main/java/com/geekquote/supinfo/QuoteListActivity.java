package com.geekquote.supinfo;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geekquote.supinfo.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuoteListActivity extends AppCompatActivity {
    private List<Quote> quoteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);
        Resources resources = getResources();

        String[] quotesFromRessources = resources.getStringArray(R.array.quote_array);

        for (String quotesFromRessource : quotesFromRessources) {
            addQuote(quotesFromRessource);
        }
    }

    private void addQuote(String strQuote) {
        Quote quote = new Quote();
        quote.setStrQuote(strQuote);

        quoteList.add(quote);
        createTextView(quote);
    }

    private void createTextView(Quote quote) {
        // On retrouve le LinearLayout grâce à son id
        LinearLayout layout = findViewById(R.id.linear_vertical);

        // On crée un élément TextView à chaque ajout d'un Quote, qui contiendra la valeur du quote en texte
        TextView textView = new TextView(this);
        textView.setText(quote.getStrQuote());

        // On initialise la couleur à partir des ressources, suivant si la liste contient un nombre pair ou impair de valeurs
        int color = quoteList.size() % 2 == 0 ? R.color.lightgray : R.color.gray;
        // On set la couleur de fond de notre élément TextView créé précédemment
        textView.setBackgroundColor(getResources().getColor(color));

        // Enfin, on ajoute notre TextView créé au LinearLayout vertical (parent)
        layout.addView(textView);
    }
}
