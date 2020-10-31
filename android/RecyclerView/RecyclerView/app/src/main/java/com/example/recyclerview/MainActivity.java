package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.recyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding b;
    private WordListAdapter adapter;
    private List<String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        createInitialData();
        setupAdapter();
        setupFAB();
    }

    private void setupFAB() {

        b.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newWord = "+ Word" + words.size();
                words.add(newWord);

                adapter.notifyItemInserted(words.size() - 1);
                b.recyclerView.smoothScrollToPosition(words.size() - 1);
            }
        });
    }

    private void setupAdapter() {
        adapter = new WordListAdapter(this, words);

        //Setup RecyclerView
        b.recyclerView.setAdapter(adapter);
        b.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createInitialData() {
        words = new ArrayList<>();
        for (int i = 0; i < 20; i++)
            words.add("Word " + i);

    }


}