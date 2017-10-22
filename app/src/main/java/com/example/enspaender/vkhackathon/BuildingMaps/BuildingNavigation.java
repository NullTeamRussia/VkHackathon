package com.example.enspaender.vkhackathon.BuildingMaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.enspaender.vkhackathon.BuildingMaps.MapFragments.BaseFragment;
import com.example.enspaender.vkhackathon.BuildingMaps.MapFragments.FirstFloorFragment;
import com.example.enspaender.vkhackathon.BuildingMaps.MapFragments.SecondFloorFragment;
import com.example.enspaender.vkhackathon.BuildingMaps.MapFragments.ThirdFloorFragment;
import com.example.enspaender.vkhackathon.R;
import com.example.enspaender.vkhackathon.Utils.ActivityUtils;

/**
 * Created by enspaender on 21.10.17.
 */

public class BuildingNavigation extends AppCompatActivity {

  @BindView(R.id.floor_navigation) BottomNavigationView floorNavBar;
  private FragmentManager fm;
  private int container = R.id.map_container;
  int i = 1;
  private BaseFragment fragment;
  private boolean isMap;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map);
    ButterKnife.bind(this);
    fragment = FirstFloorFragment.getInstance(true);

    Intent intent = getIntent();
    /** if is map => just look map | else => routing */
    isMap = intent.getExtras().getBoolean("isMap");


    initNavigation();
  }

  private void initNavigation(){
    fm = getSupportFragmentManager();
    ActivityUtils.addFragmentToActivity(fm, fragment, container);

    floorNavBar.setOnNavigationItemSelectedListener((item) -> {
      switch (item.getItemId()) {
        case R.id.first_floor:
          if (i != 1) {
            i=1;
            fragment = FirstFloorFragment.getInstance(true);

            if (!fragment.isAdded()) ActivityUtils.addFragmentToActivity(fm, fragment, container);
            else ActivityUtils.replaceFragment(fm, container, fragment);
          }

          //}

          return true;
        case R.id.second_floor:
          if (i != 2) {
            i = 2;
            fragment = SecondFloorFragment.getInstance(true);

            if (!fragment.isAdded()) ActivityUtils.addFragmentToActivity(fm, fragment, container);
            else ActivityUtils.replaceFragment(fm, container, fragment);
          }

          return true;
        case R.id.third_floor:
          if (i != 3) {
            i=3;
            fragment = ThirdFloorFragment.getInstance(true);
            if (!fragment.isAdded()) ActivityUtils.addFragmentToActivity(fm, fragment, container);
            else ActivityUtils.replaceFragment(fm, container, fragment);
          }
          return true;
        default:
          return false;
      }
    });
  }
}
