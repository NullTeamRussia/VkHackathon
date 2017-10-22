package com.example.enspaender.vkhackathon.BuildingMaps;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder;
import com.example.enspaender.vkhackathon.R;

public class MainActivity extends AppCompatActivity {



  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    SubsamplingScaleImageView imageView = (SubsamplingScaleImageView)findViewById(R.id.test);
    imageView.setImage(ImageSource.asset("b1f1.jpg").tilingEnabled());
    imageView.setRegionDecoderClass(ImageRegionDecoder.class);
    imageView.setScaleAndCenter(50,new PointF(400,400));
    imageView.setZoomEnabled(false);
  }
}
