package com.example.relativelayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FirstFragment extends Fragment {
  private static final String ARG1 = "argument1";
    private static final String ARG2 = "argument2";

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment getInstance(String value,int age){
        FirstFragment firstFragment = new FirstFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG1,value);
        bundle.putInt(ARG2,age);
        firstFragment.setArguments(bundle);
        return  firstFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView text1;
        Button setDataBtn,getDataBtn,updateBtn,deleteBtn;
        // Inflate the layout for this fragment

        //receiving values from other fragment
        String name="";
        int age = 0;
        if(getArguments()!=null){
            name =  getArguments().getString("Name");
            age = getArguments().getInt("Age");
            Log.d("values are ",name+"----"+age);
        }
        String name1 = getArguments().getString(ARG1);
        int age2 = getArguments().getInt(ARG2);
        Log.d("Second Values ",name1+"----"+age2);
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        text1 = view.findViewById(R.id.text_frag2);
        setDataBtn = view.findViewById(R.id.add_data_btn);
        getDataBtn = view.findViewById(R.id.get_data_btn);
        updateBtn = view.findViewById(R.id.update_data_btn);
        deleteBtn = view.findViewById(R.id.delete_data_btn);
        text1.setText(name+"...."+age);

        MySQLDatabase mySQLDatabase = new MySQLDatabase(requireActivity().getApplicationContext());

        setDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySQLDatabase.addNewDataToDb("Akanksha","6395909884");
                mySQLDatabase.addNewDataToDb("Himanshu Singh","9837717618");
                mySQLDatabase.addNewDataToDb("Shraddha Khapra","7376216849");
            }
        });

        getDataBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ArrayList<ContactModelForDb> fetchedDataFromDb =  mySQLDatabase.fetchDataFromDb();
                for(int i=0;i<fetchedDataFromDb.size();i++){
                    Log.d("contacts are",fetchedDataFromDb.get(i).id+"  "+fetchedDataFromDb.get(i).name+"  "+fetchedDataFromDb.get(i).phone_num);
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {

            ContactModelForDb contactModelForDb = new ContactModelForDb();
            @Override
            public void onClick(View view) {
                contactModelForDb.id = 1;
                contactModelForDb.name="Akanksha Singh";
               mySQLDatabase.updateDbData(contactModelForDb);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySQLDatabase.deleteDataFromDb("Shraddha Khapra");
            }
        });
        //getActivity() method refers to parent activity of fragment
       //accessing user defined methods of activity in fragments
        //first need to explicitly typecast activity
        ((FragmentHolderActivity)getActivity()).callThisMethodFromFragment();
        return view;
    }
}