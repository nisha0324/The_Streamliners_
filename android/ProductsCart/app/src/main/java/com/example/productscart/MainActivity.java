package com.example.productscart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.productscart.databinding.ActivityMainBinding;
import com.example.productscart.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ProductsAdaptor adaptor;
    private ArrayList<Product> products;
    private SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

      /* new WeightPicker().show(MainActivity.this, new WeightPicker.OnWeightPickedListener() {
           @Override
           public void onWeightPicked(int kg, int g) {
               Toast.makeText(MainActivity.this,kg + " " +" kg" + g * 50 + " "+ "g",Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onWeightPickerCancelled() {
               Toast.makeText(MainActivity.this, "Cancel Clicked!", Toast.LENGTH_LONG).show();

           }
       }); */

        setUpProductList();
    }

    private void setUpProductList() {
        products = new ArrayList<>(Arrays.asList(
                new Product("Apple", 100, 1)
                , new Product("Orange", 100, 1)
                , new Product("Grapes", 100, 1)
                , new Product("Kiwi", 100, 1)
        ));

        adaptor = new ProductsAdaptor(MainActivity.this,products);

        binding.productList.setAdapter(adaptor);
        binding.productList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.productList.addItemDecoration(
                new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL)
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_catalog_options, menu);

         searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        //Meta Data
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String query) {
                Log.i("MyLog", "onQueryTextChange : " +  query);
                adaptor.filter(query);
                return true;

            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("MyLog", "onQueryTextSubmit : " +  query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_item){
            showMenu();
        return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.product_edit :
                editLastSelectedItem();
                return true;

            case R.id.product_remove :
                removeLastSelectedItem();

                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void removeLastSelectedItem() {
        new AlertDialog.Builder(this)
                .setTitle("Do you really want to remove this product?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Product productToBeRemoved = adaptor.visibleProducts.get(adaptor.lastSelectedItemPosition);

                        adaptor.visibleProducts.remove(productToBeRemoved);
                        adaptor.productList.remove(productToBeRemoved);

                        adaptor.notifyItemRemoved(adaptor.lastSelectedItemPosition);

                        Toast.makeText(MainActivity.this, "Removed", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }



    private void editLastSelectedItem() {

        Product lastSelectedProduct = adaptor.visibleProducts.get(adaptor.lastSelectedItemPosition);
        ProductEditorDialog productEditorDialog = new ProductEditorDialog();
        productEditorDialog.product = lastSelectedProduct;


        new ProductEditorDialog().show(this, lastSelectedProduct, new ProductEditorDialog.OnProductEditedListener() {
            @Override
            public void onProductEdited(Product product) {
                //Update View
                adaptor.visibleProducts.set(adaptor.lastSelectedItemPosition, product);
                adaptor.notifyItemChanged(adaptor.lastSelectedItemPosition);
            }

            @Override
            public void onCancelled() {
                Toast.makeText(MainActivity.this, "Cancelled!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showMenu() {

        new ProductEditorDialog().show(MainActivity.this, new Product(), new ProductEditorDialog.OnProductEditedListener() {
            @Override
            public void onProductEdited(Product product) {
                adaptor.visibleProducts.add(product);
                adaptor.productList.add(product);
                adaptor.notifyItemChanged(products.size()-1);
            }

            @Override
            public void onCancelled() {

                Toast.makeText(MainActivity.this,"Cancelled",Toast.LENGTH_SHORT).show();

            }
        });
    }
}