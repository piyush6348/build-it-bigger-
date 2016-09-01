package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dell.myapplication.backend.myApi.MyApi;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import piyush6348.builditbigger.com.mylibrary.ImageActivity;


public class MainActivity extends ActionBarActivity {
    private static MyApi myApiService = null;
    private ProgressBar spinner;
    private String joke;
    private RelativeLayout relativeLayout;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout=(RelativeLayout)findViewById(R.id.relative);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute();
        
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-8037008543529602/2054128571");

        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        interstitialAd.loadAd(adRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
       // Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                interstitialAd.show();
                Log.e("add loaded","done");
            }
            @Override
            public void onAdClosed() {
                // requestNewInterstitial();
                newActivity();
            }
        });
      /*  if(interstitialAd.isLoaded())
            interstitialAd.show();
        else
        {
           newActivity();
        }*/

    }
    public void newActivity()
    {
        Bundle b=new Bundle();
        b.putString("joke",joke);
        Intent i=new Intent(this,ImageActivity.class);
        i.putExtras(b);
        startActivity(i);
    }

   public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        public String doInBackground(Void... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("https://build-it-bigger-142014.appspot.com/_ah/api/");
                      /*  .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });*/
                // end options for devappserver

                myApiService = builder.build();
            }

          /*  context = params[0].first;
            String name = params[0].second;*/

            try {
                return myApiService.sayHi().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            spinner.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);
            joke=result;
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }

}
