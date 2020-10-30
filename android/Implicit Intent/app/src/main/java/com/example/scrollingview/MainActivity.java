package com.example.scrollingview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.example.scrollingview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void openWebsite(View view) {
        String url = binding.websiteEdittext.getText().toString();
        //parse the string into Uri object
        Uri webpage = Uri.parse("https://www.google.com/search?q=" + url);
        //defined action that is to view the given data that is to view the webpage
        Intent intent = new Intent(Intent.ACTION_VIEW,webpage);

        //check if any activity can handle the intent
        if (intent.resolveActivity(getPackageManager()) != null){
            // if yes then start the activity
            startActivity(intent);
        }
        else{
            Log.d("ImplicitIntents","can't handle this!");
        }
    }

    public void openLocation(View view) {
        String loc = binding.locationEdittext.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        Intent i = new Intent(Intent.ACTION_VIEW,addressUri);
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {

        String txt = binding.shareEdittext.getText().toString();

        String mimeType = "text/plane";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }

    public void clickPhoto(View view) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivity(takePictureIntent);
    }
}