package piyush6348.builditbigger.com.mylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ImageActivity extends AppCompatActivity {

    public static String joke="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        joke=getIntent().getStringExtra("joke");
        setContentView(R.layout.activity_image);

        Log.e("ImageActivity",joke);

      /*  Bundle b=new Bundle();
        b.putString("joke",joke);
        ImageActivityFragment imageActivityFragment=new ImageActivityFragment();
        imageActivityFragment.setArguments(b);*/
    }
}
