package com.geekquote.supinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.geekquote.supinfo.model.Quote;

public class QuoteActivity extends AppCompatActivity {
    Quote quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        Bundle extras = getIntent().getExtras();

        // On retrouve les différents éléments qui composent le layout
        TextView txtQuote = findViewById(R.id.txtQuoteStr);
        TextView txtDate = findViewById(R.id.txtQuoteDate);

        final RatingBar rtbQuote = findViewById(R.id.rtbQuote);

        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnValide = findViewById(R.id.btnValide);

        if (extras != null) {
            quote = (Quote) extras.get("Quote");

            if (quote != null) {
                txtQuote.setText(quote.getStrQuote());
                txtDate.setText(quote.getCreationDate().toString());
                rtbQuote.setRating(quote.getRating().floatValue());
            }
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuoteActivity.this, QuoteListActivity.class);
                startActivity(intent);
            }
        });

        btnValide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuoteActivity.this, QuoteListActivity.class);

                if (quote != null) {
                    quote.setRating((double) rtbQuote.getRating());
                    intent.putExtra("Quote", quote);
                }
                startActivity(intent);
            }
        });
    }
}
