package com.example.relativelayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomTabsScreen extends AppCompatActivity {
    BottomNavigationView bottomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tabs_screen);
        bottomView = findViewById(R.id.bottom_bar);
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.home_tab){
                    getFragment(new HomeFragment(),true);
                } else if (id==R.id.my_post) {
                    getFragment(new MyPostsFragment(),false);
                } else if (id==R.id.add_posts) {
                    getFragment(new FirstFragment(),false);
                } else if (id == R.id.settings_tab) {
                    getFragment(new SecondFragment(),false);
                }else {
                    getFragment(new ThirdFragment(),false);
                }
                return true; //return true to show selection of selected item or highlighting it with different color
            }
        });
        bottomView.setSelectedItemId(R.id.home_tab);
    }
    public void getFragment(Fragment fg,Boolean first){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(first){
            ft.add(R.id.bottom_bar_frame,fg);
        }else{
            ft.replace(R.id.bottom_bar_frame,fg);
        }
        ft.commit();
    }
}