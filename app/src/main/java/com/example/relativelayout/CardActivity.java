package com.example.relativelayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fButton;
    ArrayList<ContactModel> list = new ArrayList<>();
    AdapterContact adapterContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        recyclerView = findViewById(R.id.recycler_view);
        fButton = findViewById(R.id.floating_btn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //setting card view style from java file

        ContactModel contactModel = new ContactModel("983717618",R.drawable.restaurant,"Akanksha Singh");
        list.add(contactModel);         //First way to add new contactModel
        list.add(new ContactModel("7376216849",R.drawable.akanksha,"Himanshu Singh"));      //Second method to add elements in contactModel
        list.add(new ContactModel("73762176849",R.drawable.ic_launcher_background,"Akdu Singh"));
        list.add(new ContactModel("73434621848",R.drawable.akanksha,"Egomam Singh"));
        list.add(new ContactModel("73476216849",R.drawable.restaurant,"HardHearted Singh"));
        list.add(new ContactModel("73762154843",R.drawable.akanksha,"GoodPerson Singh"));
        list.add(new ContactModel("73792168841",R.drawable.restaurant,"StoneHearted Singh"));
        list.add(new ContactModel("73761216849",R.drawable.akanksha,"SweetSomeway Singh"));
        list.add(new ContactModel("95737474382",R.drawable.akanksha,"Gudiya Pawar"));
        list.add(new ContactModel("98048538427",R.drawable.restaurant,"Harpareet Kaur Singh"));
        list.add(new ContactModel("53642736828",R.drawable.akanksha,"Gaurav Singh"));
        adapterContact = new AdapterContact(this,list);
        recyclerView.setAdapter(adapterContact);

        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(CardActivity.this);
                dialog.setContentView(R.layout.add_update_layout);
                EditText et1 = dialog.findViewById(R.id.name_et);
                EditText et2 = dialog.findViewById(R.id.number_et);
                Button btn = dialog.findViewById(R.id.add_btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "";
                        String number="";
                        if(!et1.getText().toString().equals("")){
                             name = et1.getText().toString();
                        }else{
                            Toast.makeText(CardActivity.this,"Please enter name",Toast.LENGTH_SHORT).show();
                        }
                        if(!et2.getText().toString().equals("")){
                             number = et2.getText().toString();
                        }else{
                            Toast.makeText(CardActivity.this,"Please enter number",Toast.LENGTH_SHORT).show();
                        }
                        list.add(new ContactModel(number,name));
                        adapterContact.notifyItemInserted(list.size());
                        //to notice user on the newly added item in the list
                        recyclerView.scrollToPosition(list.size()-1);

                        //to close dialog when work of dialog has done
                        dialog.dismiss();
                    }
                });
                //below line is necessary to show dialog box
                dialog.show();
            }
        });
    }
}