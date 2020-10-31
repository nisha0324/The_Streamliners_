package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.databinding.WordlistItemBinding;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>{

    private Context context;
    private List<String> words;

    public WordListAdapter(Context context, List<String> words) {
        this.context = context;
        this.words = words;
    }



    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WordlistItemBinding b = WordlistItemBinding.inflate(
                                LayoutInflater.from(context)
                               , parent
                               , false
                       );

        return new WordViewHolder(this,b);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {

        String word = words.get(position);
        holder.b.word.setText(word);

    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       WordListAdapter adapter;
       WordlistItemBinding b;

        public WordViewHolder(@NonNull WordListAdapter adapter, WordlistItemBinding b) {
            super(b.getRoot());

            this.adapter = adapter;
            this.b = b;

            b.word.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int mPosition = getLayoutPosition();
            String element = words.get(mPosition);

            words.set(mPosition, "Clicked  " + element);

            adapter.notifyItemChanged(mPosition);
        }
    }
}
