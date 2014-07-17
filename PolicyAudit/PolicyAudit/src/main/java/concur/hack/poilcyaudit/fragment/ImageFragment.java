package concur.hack.poilcyaudit.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import concur.hack.policyaudit.R;
import concur.hack.policyaudit.activity.ImageAdapter;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ImageFragment extends Fragment {

    private View root;
    private ImageAdapter imageAdapter;

    public void setImageAdapter(ImageAdapter imageAdapter) {
        this.imageAdapter = imageAdapter;
    }

    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_image, container, false);

        //render the images
        renederImages();

        return root;
    }


    private void renederImages() {
        GridView gridView = (GridView) root.findViewById(R.id.imageGridView);

        gridView.setAdapter(imageAdapter);
    }

}
