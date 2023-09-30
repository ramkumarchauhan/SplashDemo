package com.example.splashdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();


        bottomNavigationView.setOnItemSelectedListener(
                item -> {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    if (item.getItemId() == R.id.navigation_home) {
                        // Start the PostActivity when "Home" is clicked
                        startActivity(new Intent(getApplicationContext(), HomeFragment.class));
                    } else if (item.getItemId() == R.id.navigation_dashboard) {
                        transaction.replace(R.id.fragment_container, new DashboardFragment());
                    } else if (item.getItemId() == R.id.navigation_new_post) {
                        transaction.replace(R.id.fragment_container, new NewPostFragment());
                    }
                    return true;
                });

        if (bottomNavigationView != null) {
            // Now, it's safe to work with bottomNavigationView
            bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // Handle item selection here
                    return true;
                }
            });
        }

        postList = new ArrayList<>();

            int[] imageResources = {
                    R.drawable.sample_img1,
                    R.drawable.sample_img2,
                    R.drawable.sample_img3,
                    R.drawable.sample_img4,
                    R.drawable.sample_img5,
            };

            for (int i = 0; i < imageResources.length; i++) {
                String postContent = "Sample post " + (i + 1);
                int imageResource = imageResources[i];

                Post post = new Post(postContent, imageResource);
                postList.add(post);
        }


        postAdapter = new PostAdapter(postList);
        recyclerView.setAdapter(postAdapter);
    }

}