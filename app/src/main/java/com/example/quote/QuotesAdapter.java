package com.example.quote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class QuotesAdapter extends ArrayAdapter<QuotesData> {

    private Context context;
    private List<QuotesData> quotesList;

    public QuotesAdapter(@NonNull Context context, @NonNull List<QuotesData> objects) {
        super(context, 0, objects);
        this.context = context;
        this.quotesList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.quote_item, parent, false);
        }

        QuotesData quotesData = quotesList.get(position);

        TextView quoteTextView = convertView.findViewById(R.id.quoteTextView);
        ImageButton deleteButton = convertView.findViewById(R.id.deleteButton);

        quoteTextView.setText(quotesData.getQuote());

        deleteButton.setOnClickListener(v -> {
            quotesList.remove(position);
            int id = quotesData.getId();
            SQLite sqLite = new SQLite(this.getContext());
            sqLite.delete(quotesData, id);
            notifyDataSetChanged();
        });

        return convertView;
    }
}
