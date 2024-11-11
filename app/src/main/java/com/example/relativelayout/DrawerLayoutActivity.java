package com.example.relativelayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class DrawerLayoutActivity extends AppCompatActivity {
   DrawerLayout drawerLayout;
   NavigationView navigationView;
   Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.drawer_toolbar);
        //step 1 setting toolbar
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
       drawerLayout.addDrawerListener(toggle); //after this line we can see humburger icon on toolbar
       toggle.syncState();  //to manage whether drawer is open or close

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();

                     if(id == R.id.home_tab){
                         setFragment(new HomeFragment(),true);
                     } else if (id == R.id.my_post) {
                         setFragment(new MyPostsFragment(),false);
                     } else if (id == R.id.add_posts) {
                         setFragment(new FirstFragment(),false);
                     }else if (id == R.id.settings_tab) {
                         setFragment(new SecondFragment(),false);
                     }else if (id == R.id.profile_tab) {
                         setFragment(new ThirdFragment(),false);
                     }else{
                         setFragment(new HomeFragment(),false);
                     }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    public void setFragment(Fragment fragment,boolean first){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(first){
            ft.add(R.id.drawer_frame,fragment);
            ft.commit();
        }else{
            ft.replace(R.id.drawer_frame,fragment);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}