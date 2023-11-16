package com.megacoreapps.cashout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class Instructions extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    AdRequest adRequest;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
        adRequest = new AdRequest.Builder().build();
        adView = findViewById(R.id.adView);
        MobileAds.initialize(this, "ca-app-pub-5836526993277102~6434973492");
//        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); // google id
        adView.loadAd(adRequest);

        ImageView imageView = findViewById(R.id.imageView14);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        viewFlipper = findViewById(R.id.viewFlipper);
        final Handler handler = new Handler();
        final int delay = 100; //milliseconds
        handler.postDelayed(new Runnable(){
            public void run(){
                if (viewFlipper.getDisplayedChild() == 4){
                    Button button = findViewById(R.id.play4);
                    button.setText("CLOSE INSTRUCTIONS");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Instructions.this, ChoiceSelection.class);
                            startActivity(intent);
                        }
                    });
                }
                handler.postDelayed(this, delay);
            }
        }, delay);}
    public void nextView(View v) {
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        viewFlipper.showNext();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}