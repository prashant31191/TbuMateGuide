package com.tbumateguide1.tbuguide;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.ads.nativead.NativeAdDetails;
import com.startapp.android.publish.ads.nativead.NativeAdPreferences;
import com.startapp.android.publish.ads.nativead.StartAppNativeAd;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.tbumateguide1.R;

import java.util.ArrayList;

public class DashboardActivity extends Activity {

    //for the start app ads
    /**
     * StartAppAd object declaration
     */
    private StartAppAd startAppAd = new StartAppAd(this);

    /**
     * StartApp Native Ad declaration
     */
    public StartAppNativeAd startAppNativeAd = new StartAppNativeAd(this);
    private NativeAdDetails nativeAd = null;


    /**
     * Native Ad Callback
     */
    private AdEventListener nativeAdListener = new AdEventListener() {

        @Override
        public void onReceiveAd(Ad ad) {

            // Get the native ad
            ArrayList<NativeAdDetails> nativeAdsList = startAppNativeAd.getNativeAds();
            if (nativeAdsList.size() > 0) {
                nativeAd = nativeAdsList.get(0);
            }

            // Verify that an ad was retrieved
            if (nativeAd != null) {

                // When ad is received and displayed - we MUST send impression
                nativeAd.sendImpression(DashboardActivity.this);
            }
        }

        @Override
        public void onFailedToReceiveAd(Ad ad) {

            // Error occurred while loading the native ad
            Log.e("---start app-", "===Ads load fail==");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            StartAppSDK.init(this, getString(R.string.app_name), true);// 211313263 //TODO: Replace with your Application ID

            /**
             * Load Native Ad with the following parameters:
             * 1. Only 1 Ad
             * 2. Download ad image automatically
             * 3. Image size of 150x150px
             */
            startAppNativeAd.loadAd(
                    new NativeAdPreferences()
                            .setAdsNumber(1)
                            .setAutoBitmapDownload(true)
                            .setPrimaryImageSize(2),
                    nativeAdListener);

            setContentView(R.layout.activity_dashboard);


            getIntentData();
            initialization();
            setClickEvents();
            setCommonData();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getIntentData() {
        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialization() {
        try {

            // htab_appbar = (AppBarLayout) findViewById(R.id.htab_appbar);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCommonData() {
        try {


         //   setSupportActionBar(toolbar);
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


    private void setClickEvents() {
        try {
          /*  rlSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Part of the activity's life cycle, StartAppAd should be integrated here.
     */
    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }

    /**
     * Part of the activity's life cycle, StartAppAd should be integrated here
     * for the home button exit ad integration.
     */
    @Override
    public void onPause() {
        super.onPause();
        startAppAd.onPause();
    }


    boolean blnExit = false;

    /**
     * Part of the activity's life cycle, StartAppAd should be integrated here
     * for the back button exit ad integration.
     */
    @Override
    public void onBackPressed() {
        /*startAppAd.onBackPressed();
        super.onBackPressed();*/

        if (blnExit) {
            super.onBackPressed();
            return;
        }
        this.blnExit = true;
        //  startAppAd.onBackPressed();


        Toast.makeText(this, "Please click BACK again to EXIT.", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                blnExit = false;
            }
        }, 2000);
    }
}