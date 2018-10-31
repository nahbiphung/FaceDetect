package com.example.admin.facedetect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        Intent intent = new Intent(MainActivity.this,PhotoDetector.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this,"Photo Detector",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this,FaceTrackerActivity.class);
                        startActivity(intent1);
                        Toast.makeText(MainActivity.this,"Camera Detector",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this,"ccc",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this,"ddd",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
