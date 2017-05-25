package com.tbumateguide1.tbuguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    Button button1,button2,button3,button4,button5,button6;
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

    ArrayList<DataModel> dataModelArrayList = new ArrayList<>();

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
            button1 = (Button) findViewById(R.id.button1);
            button2 = (Button) findViewById(R.id.button2);
            button3 = (Button) findViewById(R.id.button3);
            button4 = (Button) findViewById(R.id.button4);
            button5 = (Button) findViewById(R.id.button5);
            button6 = (Button) findViewById(R.id.button6);


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


            dataModelArrayList.add(new DataModel("1","TBU.MATE USER GUIDE","Tbu.Mate empowers you to rapidly get to, inquiry, share, and download Videos. \n" +
                    "\n" +
                    "Since downloading dependably occurs out of sight, you can continue watching videos, surfing the Internet, tweeting, and tuning in to your music as you download.\n"));
            dataModelArrayList.add(new DataModel("2","HOW TO USE TBU.MATE APP","Open the App and tap on menu tab, situated on the upper left corner.                        \n" +
                    "Enter the link  or else it'll suggest you couple of sites from where you need to download videos. Include URL address.                        \n" +
                    "Presently Browse the video you need to download and play it for some time.\n" +
                    "Tap the green download icon and Wait for the list of video formats options to appear, then select the video quality in which you want to download the video.\n"));
            dataModelArrayList.add(new DataModel("3","CHECK YOUR DOWNLOADS","Tap The \"Downloaded\" tab and you will find your downloaded files."));
            dataModelArrayList.add(new DataModel("4","HOW TO PLAY YOUR DOWNLOADED CONTENT","Go to Downloaded Tab and simply tap on the content, or go to your local storage and you will find the downloaded videos.\n"));
            dataModelArrayList.add(new DataModel("5","FIND SAVED VIDEOS.","You can easily find your saved videos by accessing your default media player."));
            dataModelArrayList.add(new DataModel("6","TBU.MATE FEATURES","\n" +
                    "* Fast download.\n" +
                    "\n" +
                    "* Multiple download resolution and format options\n" +
                    "\n" +
                    "* Resume downloading qucikly.\n" +
                    "\n" +
                    "* Convert to MP4 and Mp3 audio.\n" +
                    "\n" +
                    "* Easy playlists maker.\n"));


            button1.setText(dataModelArrayList.get(0).title);
            button1.setTag(dataModelArrayList.get(0).detail);
            button2.setText(dataModelArrayList.get(1).title);
            button2.setTag(dataModelArrayList.get(1).detail);
            button3.setText(dataModelArrayList.get(2).title);
            button3.setTag(dataModelArrayList.get(2).detail);
            button4.setText(dataModelArrayList.get(3).title);
            button4.setTag(dataModelArrayList.get(3).detail);
            button5.setText(dataModelArrayList.get(4).title);
            button5.setTag(dataModelArrayList.get(4).detail);
            button6.setText(dataModelArrayList.get(5).title);
            button6.setTag(dataModelArrayList.get(5).detail);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setClickEvents() {
        try {
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(DashboardActivity.this,DetailActivity.class);
                    intent.putExtra("pos",1);
                    intent.putExtra("title",button1.getText());
                    intent.putExtra("detail",button1.getTag().toString());
                    startActivity(intent);
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(DashboardActivity.this,DetailActivity.class);
                    intent.putExtra("pos",2);
                    intent.putExtra("title",button2.getText());
                    intent.putExtra("detail",button2.getTag().toString());
                    startActivity(intent);
                }
            });


            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(DashboardActivity.this,DetailActivity.class);
                    intent.putExtra("pos",3);
                    intent.putExtra("title",button3.getText());
                    intent.putExtra("detail",button3.getTag().toString());
                    startActivity(intent);
                }
            });

            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(DashboardActivity.this,DetailActivity.class);
                    intent.putExtra("pos",4);
                    intent.putExtra("title",button4.getText());
                    intent.putExtra("detail",button4.getTag().toString());
                    startActivity(intent);
                }
            });

            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(DashboardActivity.this,DetailActivity.class);
                    intent.putExtra("pos",5);
                    intent.putExtra("title",button5.getText());
                    intent.putExtra("detail",button5.getTag().toString());
                    startActivity(intent);
                }
            });

            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(DashboardActivity.this,DetailActivity.class);
                    intent.putExtra("pos",6);
                    intent.putExtra("title",button6.getText());
                    intent.putExtra("detail",button6.getTag().toString());
                    startActivity(intent);
                }
            });

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