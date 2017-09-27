package de.android.myapplication;


import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CaptureFragment extends Fragment {
    private static final int CAMERA_REQUEST = 123;
    private ImageView imageView;
    private Uri imageUri = Uri.EMPTY;
    private String curentPhotoPath;
    private DataManager dataManager;
    private Button btnSafe;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataManager = new DataManager(getActivity().getApplicationContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        btnSafe = (Button)view.findViewById(R.id.btnSave);
        btnSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                imageUri = Uri.parse("Uri.parse()");

                if (imageUri != null) {
                    if (!imageUri.equals(Uri.EMPTY)) {
                        Photo photo = new Photo();
                        photo.setTitle("title");
                        photo.setStorageLocation(imageUri);
                        photo.setTag1("tag1");
                        photo.setTag2("tag2");
                        photo.setTag3("tag3");
                        dataManager.addPhoto(photo);
                        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "No image to save", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
}
