package com.example.ricoramars.numberstrivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TriviaAdapter extends RecyclerView.Adapter<TriviaAdapter.TriviaViewholder> {

    private Context context;
    private List<NumberQuoteItem> numberQuoteItemList;

    public TriviaAdapter(Context context, List<NumberQuoteItem> numberQuoteItemList) {
        this.context = context;
        this.numberQuoteItemList = numberQuoteItemList;
    }

    @NonNull
    @Override
    public TriviaViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trivia_item, null);
        return new TriviaViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TriviaViewholder triviaViewholder, int i) {
        NumberQuoteItem numberQuoteItem = numberQuoteItemList.get(i);

        triviaViewholder.textViewNumber.setText(String.valueOf(numberQuoteItem.getNumber()));
        triviaViewholder.textViewFact.setText(numberQuoteItem.getText());
    }

    @Override
    public int getItemCount() {
        return numberQuoteItemList.size();
    }

    class TriviaViewholder extends RecyclerView.ViewHolder {

        TextView textViewNumber, textViewFact;

        public TriviaViewholder(@NonNull View itemView) {
            super(itemView);

            textViewNumber  = itemView.findViewById(R.id.numberView);
            textViewFact    = itemView.findViewById(R.id.factView);
        }
    }
}
