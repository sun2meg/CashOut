package com.megacoreapps.cashout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.installreferrer.BuildConfig;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";
    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    private Handler mHandler = new Handler();
    View rootLayout;
    private int revealX;
    private int revealY;
    AdRequest adRequest;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
        adRequest = new AdRequest.Builder().build();
        adView = findViewById(R.id.adView);
        MobileAds.initialize(this, "ca-app-pub-5836526993277102~6434973492");
//        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); // google id
        adView.loadAd(adRequest);
        ImageView share = findViewById(R.id.imageView3);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + "com.megacoreapps.cashout&pli=1" +"\n\n";
//                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "Choose one"));
                } catch(Exception e) {}
            }
        });

        final Button b1 = findViewById(R.id.instructionsbtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackgroundResource(R.drawable.back1now);
                int imgResource = R.drawable.instructioniconactivate;
                b1.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                b1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundColor));
                final Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent openInstructions = new Intent(getApplicationContext(), Instructions.class);
                        startActivity(openInstructions);
                        b1.setBackgroundColor(Color.TRANSPARENT);
                        int imgResource = R.drawable.instructionicon;
                        b1.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                        b1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundColor));
                    }
                }, 130);
            }
        });
        final Button b2 = findViewById(R.id.reedembtn);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setBackgroundResource(R.drawable.back1now);
                int imgResource = R.drawable.redeemiconactive;
                b2.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                b2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundColor));
                final Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent1 = new Intent(SettingsActivity.this, Redeem.class);
                        startActivity(intent1);
                        b2.setBackgroundColor(Color.TRANSPARENT);
                        int imgResource = R.drawable.redeemicon;
                        b2.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                        b2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundColor));
                    }
                }, 130);
            }
        });
        final Button b3 = findViewById(R.id.aboutusbtn);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b3.setBackgroundResource(R.drawable.back1now);
                int imgResource = R.drawable.abouticonactivity;
                b3.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                b3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundColor));
                final Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent i = new Intent(getApplicationContext(),AboutActivity.class);
//
//
//                        Intent i = new Intent(Intent.ACTION_VIEW);
//                        i.setData(Uri.parse(url));
                        startActivity(i);
                        b3.setBackgroundColor(Color.TRANSPARENT);
                        int imgResource = R.drawable.abouticon;
                        b3.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                        b3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundColor));
                    }
                }, 130);
            }
        });

        final Button b4 = findViewById(R.id.contactbtn);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b4.setBackgroundResource(R.drawable.back1now);
                int imgResource = R.drawable.contacticonactivate;
                b4.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                b4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundColor));
                final Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        Intent contact = new Intent(Intent.ACTION_SENDTO);

        Intent contact = new Intent(getApplicationContext(), ContactActivity.class);
//

//                        contact.setData(Uri.parse("mailto:strba.dev@gmail.com"));
                        startActivity(contact);
                        b4.setBackgroundColor(Color.TRANSPARENT);
                        int imgResource = R.drawable.contacticon;
                        b4.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                        b4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundColor));
                    }
                }, 130);
            }
        });

        ////////////////////////////////////////////



        final Button b5 = findViewById(R.id.sign);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b5.setBackgroundResource(R.drawable.back1now);
                int imgResource = R.drawable.contacticonactivate;
                b5.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
                b5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.backgroundColor));

                FirebaseAuth.getInstance().signOut();
                // After signing out, you can navigate the user to the login or main activity.
                Intent intent = new Intent(SettingsActivity.this, EmailPasswordActivity.class);
                startActivity(intent);
                finish();

//                FirebaseAuth.getInstance().signOut();
//                 startActivity(new Intent(getApplicationContext(), AgreeActivity.class));

//                final Handler h = new Handler();
//                h.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        FirebaseAuth.getInstance().signOut();
//                        finish();
// // Start a new activity on the UI thread
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        startActivity(new Intent(getApplicationContext(), AgreeActivity.class));
////                    }
////                });
//                    }
//                }, 130);
            }
        });





        //////////////////////////////////////////////////
        final Intent intent = getIntent();
        rootLayout = findViewById(R.id.rootView);
        ImageView settingbtn = (ImageView) findViewById(R.id.imageView11);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unRevealActivity();
            }
        });
        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(EXTRA_CIRCULAR_REVEAL_Y)) {
            rootLayout.setVisibility(View.INVISIBLE);
            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);
            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);
            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(revealX, revealY);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }
    }
    protected void revealActivity(int x, int y) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(400);
            circularReveal.setInterpolator(new AccelerateInterpolator());
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        } else {
            finish();
        }
    }
    protected void unRevealActivity() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            finish();
        } else {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, revealX, revealY, finalRadius, 0);
            circularReveal.setDuration(400);
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) { rootLayout.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(getApplicationContext(), ChoiceSelection.class);
                    startActivity(intent);
                }
            });
            circularReveal.start();
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChoiceSelection.class);
        startActivity(intent);
    }
}