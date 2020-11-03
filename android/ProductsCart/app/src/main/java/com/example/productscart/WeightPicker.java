package com.example.productscart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;

import com.example.productscart.databinding.WeightPickerDialogBinding;

public class WeightPicker {

    public void show(Context context, final OnWeightPickedListener listener) {
        WeightPickerDialogBinding b = WeightPickerDialogBinding.inflate(
                LayoutInflater.from(context)
        );

        new AlertDialog.Builder(context)
                .setTitle("Pick Weight")
                .setView(b.getRoot())
                .setPositiveButton("SELECT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        int kg = b.kgPicker.getValue();
                        int g = b.gPicker.getValue();

                        if (kg == 0 && g == 0) {
                            return;
                        }
                        listener.onWeightPicked(kg, g);
                    }
                })

                .setNegativeButton("CANCELL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        listener.onWeightPickerCancelled();
                    }
                })
                .show();

    }

    interface OnWeightPickedListener {
        void onWeightPicked(int kg, int g);

        void onWeightPickerCancelled();
    }

}