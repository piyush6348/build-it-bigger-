package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

/**
 * Created by dell on 8/31/2016.
 */
public class EchoTest extends AndroidTestCase {
    public void testVerifyEchoResponse(){
        assertTrue("Joke is Empty",!Echo.echo().isEmpty());
    }
}
