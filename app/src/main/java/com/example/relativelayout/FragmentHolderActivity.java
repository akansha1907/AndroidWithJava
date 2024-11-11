package com.example.relativelayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class FragmentHolderActivity extends AppCompatActivity {
 Button btn1,btn2,btn3;
 FrameLayout frameLayout;
    private static final String ROOT_FRAGMENT_TAG = "root_fragment";  // Define the root tag here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_holder);
        btn1 = findViewById(R.id.first_frg_btn);
        btn2 = findViewById(R.id.second_frg_btn);
        btn3 = findViewById(R.id.third_frg_btn);

        //To show a default fragment when screen is load
        loadFragment(new FirstFragment(),true);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new FirstFragment(),true);
                //we send data from activity to fragment also like this
                //loadFragment(new FirstFragment(),true,FirstFragment.getInstance("Akanksha",22));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new SecondFragment(),false);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fg = new ThirdFragment();
                loadFragment(fg,false);
            }
        });
    }
    public void loadFragment(Fragment fgToBeSet,boolean isFirst){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        //sending data from one fragment to another
        Bundle bundle = new Bundle();
        bundle.putString("Name","Himanshu Singh");
        bundle.putInt("Age",24);
        fgToBeSet.setArguments(bundle);

        if(isFirst){
            //if it is first fragment we will at it in framelayout
            // otherwise every new fragment will replace with new fragment
            fm.popBackStack(ROOT_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            ft.add(R.id.container, fgToBeSet);
          //  fm.popBackStack(ROOT_FRAGMENT_TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);//INCLUSIVE Mean this fragment will also pop out from stack
            //we can give EXCLUSIVE To keep first fragment in stack
            ft.addToBackStack(ROOT_FRAGMENT_TAG);
        }
        else{
            //if there is already atleast single fragment loaded replace new fragment with that
            //here also add will not gave error but replace is good to avoid unnecessary fragment load
            ft.replace(R.id.container, fgToBeSet);
            ft.addToBackStack(null);//giving null here means each fragment will push to stack and pressing back they will popped out one by one

        }
        ft.commit();        //very important line to set fragment commit is important
    }

    public void callThisMethodFromFragment(){
        Log.d("Activity Method","It is passed from activity to fragment");
    }
}