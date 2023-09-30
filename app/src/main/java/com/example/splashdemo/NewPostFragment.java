package com.example.splashdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewPostFragment extends Fragment {
    private static final int PICK_IMAGE_REQUEST_CODE = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_post, container, false);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button galleryButton = view.findViewById(R.id.galleryButton);
        galleryButton.setOnClickListener(v -> openGallery());

        return view;
    }


    private void openGallery() {
        // Create an Intent to pick an image from the gallery
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            if (data != null) {
//                Uri selectedImageUri = data.getData();
//                // Handle the selected image here
//                // You can display it, upload it, etc.
//            }
//        }
//    }
}


//import android.app.Activity;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//// ...
//
//public class NewPostFragment extends Fragment {
//
//    private ActivityResultLauncher<Intent> galleryLauncher;
//    private static final int PICK_IMAGE_REQUEST_CODE = 1001;
//
//    // ...
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // Initialize the ActivityResultLauncher for gallery intent
//        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        Intent data = result.getData();
//                        if (data != null) {
//                            Uri selectedImageUri = data.getData();
//                            // Handle the selected image here
//                            // You can display it, upload it, etc.
//                        }
//                    }
//                });
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_new_post, container, false);
//
//        Button galleryButton = view.findViewById(R.id.galleryButton);
//        galleryButton.setOnClickListener(v -> openGallery());
//
//        return view;
//    }
//
//    private void openGallery() {
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        galleryIntent.setType("image/*");
//        galleryLauncher.launch(galleryIntent);
//    }
//}
