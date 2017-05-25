package com.tbumateguide1.tbuguide;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.tbumateguide1.R;

/**
 * Created by Prashant on 10-12-2016.
 */
public class DetailActivity extends Activity {

    InterstitialAd mInterstitialAd;
    private AutoCompleteTextView search_et;

    TextView action_text,tvDetail;
    RelativeLayout rlDetail,rlToolbar,rlBack;


    ProgressDialog progressDialog;
   // AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.act_sayrilist_detail);

            action_text = (TextView)findViewById(R.id.action_text);
            tvDetail = (TextView)findViewById(R.id.tvDetail);
            rlToolbar = (RelativeLayout)findViewById(R.id.rlToolbar);
            rlBack = (RelativeLayout)findViewById(R.id.rlBack);
            rlDetail = (RelativeLayout)findViewById(R.id.rlDetail);
            rlDetail.setVisibility(View.VISIBLE);


            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait for servers to load...");


            if(getIntent().getExtras().getString("title") != null)
            {
                action_text.setText(getIntent().getExtras().getString("title"));
            }
            if(getIntent().getExtras().getString("detail") != null)
            {
                tvDetail.setText(getIntent().getExtras().getString("detail"));
            }


                //ivImage.setImageResource(getIntent().getExtras().getInt("image"));

            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(getString(R.string.app_interstitial_id));
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    requestNewInterstitial();

                }
            });

           // requestNewInterstitial();
            requestNewShowInterstitial();



            rlBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                    else
                    {
                        requestNewShowInterstitial();
                    }

                    onBackPressed();
                }
            });

           progressDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                    rlDetail.setVisibility(View.VISIBLE);

                   /* if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }*/
                }
            },5000);



            AdView mAdView = (AdView) findViewById(R.id.adView);

            AdRequest request = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                    .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")  // My Galaxy Nexus test phone
                    .build();


            mAdView.loadAd(request);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void requestNewInterstitial() {



        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


    private void requestNewShowInterstitial() {
        Log.i("====tag====","=11111====requestNewShowInterstitial=");

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.app_interstitial_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });


        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                .build();

        mInterstitialAd.loadAd(adRequest);

        // Set an AdListener.
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i("====onAdLoaded====","=11111====onAdLoaded=");
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                Log.i("====onAdClosed====","=11111====onAdClosed=");
            }
        });

    }


}
