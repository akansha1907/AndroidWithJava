package com.example.relativelayout;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    public SecondFragment() {
        // Required empty public constructor
    }
    private final int REQUEST_CODE_IMG_TAKE = 100;
    private  final int REQUEST_CODE_CHOOSE_IMG = 200;
    ImageView imageView,img2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_second, container, false);
        EditText et1,et2;
        Button btn,takePhoto,choosePhoto;
        et1 = view.findViewById(R.id.amount_edit);
        et2 = view.findViewById(R.id.title_edit);
        btn = view.findViewById(R.id.bill_add);
        imageView = view.findViewById(R.id.take_image);
        takePhoto = view.findViewById(R.id.take_photo_btn);
        choosePhoto = view.findViewById(R.id.choose_photo_btn);
        img2 = view.findViewById(R.id.choose_image);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = DatabaseHelper.getDb(requireContext().getApplicationContext());
                String title = et1.getText().toString();
                String amount = et2.getText().toString();
                databaseHelper.expenseDoa().addTransaction(new Expenses(title,amount));
                List<Expenses> list = new ArrayList<>();
                list = databaseHelper.expenseDoa().getAllExpense();
                for(int i=0;i<list.size();i++){
                    Log.d("Expenses",list.get(i).getTitle()+"  "+list.get(i).getAmount());
                }
            }
        });
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,REQUEST_CODE_IMG_TAKE);
            }
        });
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,REQUEST_CODE_CHOOSE_IMG);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode == REQUEST_CODE_IMG_TAKE){
                assert data != null;
                Bitmap image = (Bitmap)data.getExtras().get("data");
                imageView.setImageBitmap(image);
            } else if (requestCode==REQUEST_CODE_CHOOSE_IMG) {
                //for gallery

                assert data != null;
                img2.setImageURI(data.getData());
            }
        }
    }
}