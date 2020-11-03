package com.example.productrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.productrecyclerview.databinding.ActivityMainBinding;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setUpPicker();
    }

    private void setUpPicker() {
        b.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.numberPicker.setVisibility(View.VISIBLE);
                showNumberPicker(b.minEditText.getText().toString().trim(),b.maxEditText.getText().toString().trim());
            }
        });
    }

    private void showNumberPicker(String min, String max) {
        b.numberPicker.setMinValue(Integer.parseInt(min));
        b.numberPicker.setMaxValue(Integer.parseInt(max));

        b.numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return getColName(value);
            }
        });

    }

    private String getColName(int col) {
        StringBuilder builder = new StringBuilder();
        while (col > 0) {
            int remainder = col % 26;
            if (remainder == 0) {
                builder.append('Z');
                col = (col / 26) - 1;
            } else {
                builder.append((char) (64 + remainder));
                col = col / 26;
            }
        }

        return builder.reverse().toString();
    }

  /*  private void showNumberPicker() throws NumberFormatException{

        if (!(b.minEditText.getText().toString().isEmpty()) && !(b.maxEditText.getText().toString().isEmpty())){
            startNumber();
        }
        else {
            Toast.makeText(this,"not working",Toast.LENGTH_SHORT).show();
        }
    }

    private void startNumber() {

        // int min = Integer.parseInt(minimum);
        b.numberPicker.setMinValue(Integer.parseInt(b.minEditText.getText().toString()));
        //  int max = Integer.parseInt(maximum);
        b.numberPicker.setMaxValue(Integer.parseInt(b.maxEditText.getText().toString()));

        b.numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return "word" + value;
            }
        });
    }*/
}