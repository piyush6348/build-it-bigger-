package com.udacity.gradle.builditbigger;

import com.example.dell.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by dell on 8/31/2016.
 */
public class Echo {
    private static MyApi myApiService = null;
    public static String echo(){
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-142014.appspot.com/_ah/api/");
            myApiService = builder.build();
        }
        try {
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            return null;
        }
    }
}
