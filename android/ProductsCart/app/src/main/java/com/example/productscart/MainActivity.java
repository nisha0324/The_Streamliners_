package com.example.productscart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.productscart.databinding.ActivityMainBinding;
import com.example.productscart.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTheAdapator();
        setData();
    }

    private void setData() {
        List<Product> products = new ArrayList<>(
                Arrays.asList(
                        new Product("Apple", 10),
                        new Product("Banana", 20),
                        new Product("Mango", 30),
                        new Product("Orange", 40),
                        new Product("Strawberry", 50)
                )
        );

        ProductsAdaptor adaptor = new ProductsAdaptor(MainActivity.this,products);

        binding.productList.setAdapter(adaptor);
        binding.productList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void setTheAdapator() {


    }
}