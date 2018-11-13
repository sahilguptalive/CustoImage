package com.sahilguptalive.customage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sahilguptalive.completeimageview.views.ImageLoaderImpl;

public class DemoActivity extends AppCompatActivity {

    private ImageView mDemoImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mDemoImageview = findViewById(R.id.demo_image_view);
        ImageLoaderImpl.newInstance()
                .with(this)
                .src("https://homepages.cae.wisc.edu/~ece533/images/airplane.png")
                .placeholder(R.drawable.ic_launcher_background)
                .into(mDemoImageview);
    }
}
