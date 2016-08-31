package piyush6348.builditbigger.com.mylibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by dell on 8/30/2016.
 */
public class ImageActivityFragment extends Fragment {
    private TextView libraryWelcomeTextView;
    private String joke="";
    public ImageActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_image, container, false);
      //  joke=getArguments().getString("joke");
        libraryWelcomeTextView=(TextView)v.findViewById(R.id.libraryWelcomeTextView);
        libraryWelcomeTextView.setText(ImageActivity.joke);
        return v;
    }
}
