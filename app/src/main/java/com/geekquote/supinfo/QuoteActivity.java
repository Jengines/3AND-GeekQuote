package com.geekquote.supinfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        final TextView txtQuote = findViewById(R.id.txtQuoteStr);
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

        txtQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Création du dialogue d'alerte
                AlertDialog.Builder alert = new AlertDialog.Builder(QuoteActivity.this);
                alert.setTitle("Edit Quote");

                // Initialisation du composant Edit Text nécessaire à l'affichage de la quote
                final EditText edtQuote = new EditText(QuoteActivity.this);
                // On met à jour l'Edit Text avec la valeur actuelle de la quote
                edtQuote.setText(txtQuote.getText());

                // On ajoute l'Edit Text à la vue de l'alerte Dialogue
                alert.setView(edtQuote);

                // On définit le bouton de validation
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // On récupère la valeur de l'edit Text
                        String newQuoteStr = edtQuote.getText().toString();

                        // On met à jour notre Text View
                        txtQuote.setText(newQuoteStr);
                        // Ainsi que l'objet quote qu'on va retourner à l'activité précédante dans l'Intent
                        quote.setStrQuote(newQuoteStr);
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Nothing to do
                    }
                });

                // Enfin, on affiche l'alerte.
                alert.show();
            }
        });
    }
}
