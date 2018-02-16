package com.geekquote.supinfo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geekquote.supinfo.R;
import com.geekquote.supinfo.model.Quote;

import java.util.List;

/**
 * Created by Jérôme on 08/02/2018.
 */

public class QuoteListAdapter extends BaseAdapter {
    private Activity mActivity;
    private LayoutInflater mInflater;
    private List<Quote> mQuotes;
    private Quote quote = new Quote();

    public QuoteListAdapter(Activity activity, List<Quote> quotes) {
        this.mActivity = activity;
        this.mQuotes = quotes;
    }

    @Override
    public int getCount() {
        return mQuotes.size();
    }

    @Override
    public Object getItem(int i) {
        return mQuotes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (mInflater == null)
            mInflater = (LayoutInflater) mActivity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Si la vue est nulle, cela veut dire qu'on est à la première itération,
        // On associe alors le layout qu'on souhaite utiliser
        if (view == null) {
            view = mInflater.inflate(R.layout.quote_element, viewGroup, false);
        }

        // On récupère notre élément TextView
        TextView txtQuote = view.findViewById(R.id.quote_element);

        // On récupère l'objet quote qui correspond à la position i pour intéragir avec
        quote = mQuotes.get(i);
        // On met à jour le contenu du TextView avec une méthode toString()
        txtQuote.setText(toString(quote));

        // On initialise la couleur à partir des ressources, suivant si la liste contient un nombre pair ou impair de valeurs
        int color = i % 2 == 0 ? R.color.lightgray : R.color.gray;
        // On set la couleur de fond de notre élément TextView créé précédemment
        txtQuote.setBackgroundColor(mActivity.getResources().getColor(color));

        // Enfin, on retourne la vue qui correspond dans ce cas présent à une ligne de notre ListView
        // Correspondant au layout quote_element.xml qui contient uniquement un Textview
        return view;
    }

    private String toString(Quote quote){
        return quote.getStrQuote();
    }


}
