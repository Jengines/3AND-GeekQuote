package com.geekquote.supinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.geekquote.supinfo.adapter.QuoteListAdapter;
import com.geekquote.supinfo.helper.QuoteHelper;
import com.geekquote.supinfo.model.Quote;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuoteListActivity extends AppCompatActivity {
    private List<Quote> quoteList = new ArrayList<>();
    private String LOG = QuoteListActivity.this.getClass().getSimpleName();
    QuoteListAdapter quoteListAdapter;
    int quotePosition = -1;

    QuoteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_list);

        final EditText edtAddQuote = findViewById(R.id.edt_new_quote);

        // Initialize QuoteHelper and retrieve all quotes from DB
        helper = new QuoteHelper(this);

        try {
            quoteList = helper.doQuery();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // On initialise notre QuoteListAdapter qui va être associé à notre List de <Quote>
        quoteListAdapter = new QuoteListAdapter(QuoteListActivity.this, quoteList);

        // On associe notre adapteur à la ListView
        ListView mList = findViewById(R.id.lst_v_quotes);
        mList.setAdapter(quoteListAdapter);

        Button button = findViewById(R.id.btn_add_quote);

        // On configure la méthode setOnClickListener() du bouton d'ajout de quote,
        // pour permettre le traitement
        button.setOnClickListener(new View.OnClickListener()

        {

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

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long id) {

                quotePosition = position;
                Intent intent = new Intent(QuoteListActivity.this, QuoteActivity.class);
                intent.putExtra("Quote", quoteList.get(position));

                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Quote quote = data.getParcelableExtra("Quote");
            if (quote != null) {
                Quote quoteUpdatedOrInserted = helper.doUpdate(quote);
                quoteList.set(quotePosition, quoteUpdatedOrInserted);

                quoteListAdapter.notifyDataSetChanged();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addQuote(String strQuote) {
        Quote quote = new Quote();
        quote.setStrQuote(strQuote);
        quote.setCreationDate(new Date());

        Quote quoteInserted = helper.doInsert(quote);
        quoteList.add(quoteInserted);
    }
}
