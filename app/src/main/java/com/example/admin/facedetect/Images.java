package com.example.admin.facedetect;

import java.util.ArrayList;

public class Images {
    int id;
    String name;

    public Images(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList initImage(){
        ArrayList<Images> arrayList = new ArrayList<>();
        arrayList.add(new Images(R.drawable.photo,"Photo Detector"));
        arrayList.add(new Images(R.drawable.photo,"Camera Detector"));
        arrayList.add(new Images(R.drawable.photo,"Barcode Detector"));
        arrayList.add(new Images(R.drawable.photo,"Text Detector"));
        return arrayList;
    }
}
