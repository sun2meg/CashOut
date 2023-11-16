package com.megacoreapps.cashout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;

public class DailyCheckins extends AppCompatActivity implements RewardedVideoAdListener {

    private Calendar calendar;
    private int weekday;
    private SharedPreferences coins;
    private Button sunday, mon, tue, wed, thu, fri, sat;
    private String todayString;
    private RewardedVideoAd mRewardedVideoAd;
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    AdRequest adRequest;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_checkins);

        MobileAds.initialize(this, "ca-app-pub-9714848108381860~8630608609");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        } else {

        }
        adRequest = new AdRequest.Builder().build();
        adView = findViewById(R.id.adView);
        MobileAds.initialize(this, "ca-app-pub-5836526993277102~6434973492");
//        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); // google id
        adView.loadAd(adRequest);
        ImageView imageView = findViewById(R.id.imageView8);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        coins = getSharedPreferences("Rewards", MODE_PRIVATE);
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        weekday = calendar.get(Calendar.DAY_OF_WEEK);
        todayString = year + "" + month + "" + day;
        sunday = (Button) findViewById(R.id.btSun);
        mon = (Button) findViewById(R.id.btMon);
        tue = (Button) findViewById(R.id.btTue);
        wed = (Button) findViewById(R.id.btWed);
        thu = (Button) findViewById(R.id.btThu);
        fri = (Button) findViewById(R.id.btFri);
        sat = (Button) findViewById(R.id.btSat);
        sunday.setEnabled(false);
        mon.setEnabled(false);
        tue.setEnabled(false);
        wed.setEnabled(false);
        thu.setEnabled(false);
        fri.setEnabled(false);
        sat.setEnabled(false);
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (weekday==1){
            if (currentDay){
                sunday.setBackground(getResources().getDrawable(R.drawable.back2));
            }else {
            sunday.setEnabled(true);
            sunday.setAlpha(0f);
            sunday.setBackground(getResources().getDrawable(R.drawable.backgreen));
            sunday.setTextColor(Color.WHITE);
        }}
        else if (weekday==2){
            if (currentDay){
                mon.setBackground(getResources().getDrawable(R.drawable.back2));
            }else {
            mon.setEnabled(true);
            mon.setAlpha(1f);
            mon.setBackground(getResources().getDrawable(R.drawable.backgreen));
            mon.setTextColor(Color.WHITE);
        }}
        else if (weekday==3){
            if (currentDay){
                tue.setBackground(getResources().getDrawable(R.drawable.back2));
            }else {
            tue.setEnabled(true);
            tue.setAlpha(1f);
            tue.setBackground(getResources().getDrawable(R.drawable.backgreen));
            tue.setTextColor(Color.WHITE);
        }}
        else if (weekday==4){
            if (currentDay){
                wed.setBackground(getResources().getDrawable(R.drawable.back2));
            }else {
            wed.setEnabled(true);
            wed.setAlpha(1f);
            wed.setBackground(getResources().getDrawable(R.drawable.backgreen));
            wed.setTextColor(Color.WHITE);
        }}
        else if (weekday==5){
            if (currentDay){
                thu.setBackground(getResources().getDrawable(R.drawable.back1));
            }else {
            thu.setEnabled(true);
            thu.setAlpha(1f);
            thu.setBackground(getResources().getDrawable(R.drawable.backgreen));
            thu.setTextColor(Color.WHITE);
        }}
        else if (weekday==6){
            if (currentDay){
                fri.setBackground(getResources().getDrawable(R.drawable.back2));
            }else {
            fri.setEnabled(true);
            fri.setAlpha(1f);
            fri.setBackground(getResources().getDrawable(R.drawable.backgreen));
            fri.setTextColor(Color.WHITE);
        }}
        else if (weekday==7){
            if (currentDay){
                sat.setBackground(getResources().getDrawable(R.drawable.back2));
            }else {
            sat.setEnabled(true);
            sat.setAlpha(1f);
            sat.setBackground(getResources().getDrawable(R.drawable.backgreen));
            sat.setTextColor(Color.WHITE);
        }}
    }
    private void loadRewardedVideoAd() {
        if (!mRewardedVideoAd.isLoaded()){

//            mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build()); //google
            mRewardedVideoAd.loadAd("ca-app-pub-9714848108381860/6835145356", new AdRequest.Builder().build());
        }
    }
    public void monCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){

            if(mRewardedVideoAd.isLoaded()){
                mRewardedVideoAd.show();
            }  else {
                Log.d("TAG", "The mRewardedVideoAd wasn't loaded yet.");

            }
        } else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }
    }
    public void tueCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){

            if(mRewardedVideoAd.isLoaded()){
                mRewardedVideoAd.show();
            }  else {
                Log.d("TAG", "The mRewardedVideoAd wasn't loaded yet.");

            }

          } else {
            Toast.makeText(this, "Reward already received", Toast.LENGTH_SHORT).show();
        }}
    public void wedCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){
            Toast.makeText(this, "Please Wait!", Toast.LENGTH_SHORT).show();
            if(mRewardedVideoAd.isLoaded()){
                mRewardedVideoAd.show();
            }  else {

                Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "The mRewardedVideoAd wasn't loaded yet.");

            }
           } else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}

    public void thuCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){

            Toast.makeText(this, "Please Wait!", Toast.LENGTH_SHORT).show();
            if(mRewardedVideoAd.isLoaded()){
                mRewardedVideoAd.show();
            }  else {
                Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show();

                Log.d("TAG", "The mRewardedVideoAd wasn't loaded yet.");

            }

//            Toast.makeText(this, "20 Coins Recieved!", Toast.LENGTH_SHORT).show();
//            SharedPreferences.Editor daily = dailyChecks.edit();
//            daily.putBoolean(todayString, true);
//            daily.apply();
//            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
//            coinCount = coinCount + 20;
//            SharedPreferences.Editor coinsEdit = coins.edit();
//            coinsEdit.putString("Coins", String.valueOf(coinCount));
//            coinsEdit.apply();
        } else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}

    public void friCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){
            if(mRewardedVideoAd.isLoaded()){
                mRewardedVideoAd.show();
            }  else {
                Log.d("TAG", "The mRewardedVideoAd wasn't loaded yet.");

            }

//            Toast.makeText(this, "30 Coins Recieved!", Toast.LENGTH_SHORT).show();
//            SharedPreferences.Editor daily = dailyChecks.edit();
//            daily.putBoolean(todayString, true);
//            daily.apply();
//            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
//            coinCount = coinCount + 30;
//            SharedPreferences.Editor coinsEdit = coins.edit();
//            coinsEdit.putString("Coins", String.valueOf(coinCount));
//            coinsEdit.apply();
          } else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}

    public void satCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){
            if(mRewardedVideoAd.isLoaded()){
                mRewardedVideoAd.show();
            }  else {
                Log.d("TAG", "The mRewardedVideoAd wasn't loaded yet.");

            }

//            Toast.makeText(this, "30 Coins Recieved!", Toast.LENGTH_SHORT).show();
//            SharedPreferences.Editor daily = dailyChecks.edit();
//            daily.putBoolean(todayString, true);
//            daily.apply();
//            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
//            coinCount = coinCount + 30;
//            SharedPreferences.Editor coinsEdit = coins.edit();
//            coinsEdit.putString("Coins", String.valueOf(coinCount));
//            coinsEdit.apply();
         }else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}

    public void sunCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){
            if(mRewardedVideoAd.isLoaded()){
                mRewardedVideoAd.show();
            }  else {
     Log.d("TAG", "The mRewardedVideoAd wasn't loaded yet.");

            }

//            Toast.makeText(this, "50 Coins Recieved!", Toast.LENGTH_SHORT).show();
//            SharedPreferences.Editor daily = dailyChecks.edit();
//            daily.putBoolean(todayString, true);
//            daily.apply();
//            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
//            coinCount = coinCount + 50;
//            SharedPreferences.Editor coinsEdit = coins.edit();
//            coinsEdit.putString("Coins", String.valueOf(coinCount));
//            coinsEdit.apply();
        } else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,ChoiceSelection.class);
        startActivity(intent);
    }
    @Override
    public void onRewarded(RewardItem reward) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        Toast.makeText(this, "10 Coins Recieved!", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor daily = dailyChecks.edit();
        daily.putBoolean(todayString, true);
        daily.apply();
        int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
        coinCount = coinCount + 10;
        SharedPreferences.Editor coinsEdit = coins.edit();
        coinsEdit.putString("Coins", String.valueOf(coinCount));
        coinsEdit.apply();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {}
    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
    }
    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {}
    @Override
    public void onRewardedVideoAdLoaded() {}
    @Override
    public void onRewardedVideoAdOpened() {}
    @Override
    public void onRewardedVideoStarted() {}
    @Override
    public void onRewardedVideoCompleted() {

        int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
        coinCount = coinCount + 10;
        SharedPreferences.Editor coinsEdit = coins.edit();
        coinsEdit.putString("Coins", String.valueOf(coinCount));
        coinsEdit.apply();

//        mRef.child("Coins").setValue(coinCount);
        Toast.makeText(DailyCheckins.this, "10 coins received", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }
}
