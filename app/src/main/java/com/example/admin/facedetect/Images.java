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
        arrayList.add(new Images(R.drawable.picture,"Photo Detector"));
        arrayList.add(new Images(R.drawable.camera,"Camera Detector"));
        arrayList.add(new Images(R.drawable.barcode,"Barcode Detector"));
        arrayList.add(new Images(R.drawable.text,"Text Detector"));
        return arrayList;
    }
}
