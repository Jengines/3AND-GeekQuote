package com.geekquote.supinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.geekquote.supinfo.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuoteListActivity extends AppCompatActivity {
    private List<Quote> quoteList = new ArrayList<>();
    private String LOG = QuoteListActivity.this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);

        final EditText edtAddQuote = findViewById(R.id.edt_new_quote);

        // On crée notre ArrayAdapter qui va être associé à notre List de <Quote>
        final ArrayAdapter<Quote> quoteListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quoteList);

        // On associe notre adapteur à la ListView
        ListView mList = findViewById(R.id.lst_v_quotes);
        mList.setAdapter(quoteListAdapter);

        Button button = findViewById(R.id.btn_add_quote);

        // On configure la méthode setOnClickListener() du bouton d'ajout de quote,
        // pour permettre le traitement
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String quoteStr = edtAddQuote.getText().toString();

                // On vérifie que le contenu de l'Edit text contient bien quelque chose
                if (!quoteStr.isEmpty()) {
                    // 1. On crée l'objet quote à partir du texte récupéré, et on l'ajoute à notre List de <Quote>
                    addQuote(quoteStr);
                    // 2. On supprime le contenu de l'Edit Text
                    edtAddQuote.setText(null);
                    // 3. On notifie l'adapteur qu'un changement a eu lieu pour permettre l'affichage du quote qui aura été créé
                    quoteListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void addQuote(String strQuote) {
        Quote quote = new Quote();
        quote.setStrQuote(strQuote);

        quoteList.add(quote);
    }
}
