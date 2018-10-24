package com.example.admin.facedetect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<Images> arrayList;
    ImagesAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.lv_list);

        arrayList = Images.initImage();
        imageAdapter = new ImagesAdapter(MainActivity.this,R.layout.layout_list,arrayList);

        list.setAdapter(imageAdapter);
    }
}
