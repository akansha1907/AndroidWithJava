package com.example.relativelayout;

public class ContactModel {
    String name,number;
    int image;
    public ContactModel(String number,int image,String name){
        this.image = image;
        this.name = name;
        this.number = number;
    }
    public ContactModel(String number,String name){
        this.name = name;
        this.number = number;
    }
}
