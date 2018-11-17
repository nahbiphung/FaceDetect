package com.example.admin.facedetect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import static android.view.View.GONE;

public class PhotoDetector extends AppCompatActivity {

    private Button btnChoose,btnCheck;
    private ImageView imgView;

    private Uri filePath;
    private  final  int PICK_IMAGE_REQUSET =71;

    private Paint paint;
    private Bitmap myBitmap,tempBitmap;
    private Bitmap bmRedHat;

    Canvas tempCanvas;


    private static final int MASK[] = {
            R.id.no_filter,
            R.id.hair,
            R.id.op,
            R.id.snap,
            R.id.glasses2,
            R.id.glasses3,
            R.id.glasses4,
            R.id.glasses5,
            R.id.mask,
            R.id.mask2,
            R.id.mask3,
            R.id.dog,
            R.id.cat2
    };

    private  static  final  int MASK1[] ={
            R.id.no_filter
    };
//    private static final int RQS_LOADIMAGE = 1;
//    private Bitmap myBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detector);

        btnChoose = findViewById(R.id.btn_choose);
        btnCheck = findViewById(R.id.btn_check);
        imgView = findViewById(R.id.imagesView);

        myBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.photo);
        imgView.setImageBitmap(myBitmap);

        ImageButton face_photo = (ImageButton) findViewById(R.id.face_photo);
        face_photo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(findViewById(R.id.scrollView_photo).getVisibility() == GONE){
                    findViewById(R.id.scrollView_photo).setVisibility(View.VISIBLE);
                    ((ImageButton) findViewById(R.id.face_photo)).setImageResource(R.drawable.face_select);
                }else{
                    findViewById(R.id.scrollView_photo).setVisibility(GONE);
                    ((ImageButton) findViewById(R.id.face_photo)).setImageResource(R.drawable.face);
                }
            }
        });

        ImageButton no_filter = (ImageButton) findViewById(R.id.no_filter_photo);
        no_filter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[0]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[0]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton hair = (ImageButton) findViewById(R.id.hair_photo);
        hair.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[1]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[1]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton op = (ImageButton) findViewById(R.id.op_photo);
        op.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[2]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[2]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton snap = (ImageButton) findViewById(R.id.snap_photo);
        snap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[3]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[3]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton glasses2 = (ImageButton) findViewById(R.id.glasses2_photo);
        glasses2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[4]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[4]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton glasses3 = (ImageButton) findViewById(R.id.glasses3_photo);
        glasses3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[5]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[5]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton glasses4 = (ImageButton) findViewById(R.id.glasses4_photo);
        glasses4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[6]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[6]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton glasses5 = (ImageButton) findViewById(R.id.glasses5_photo);
        glasses5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[7]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[7]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton mask = (ImageButton) findViewById(R.id.mask_photo);
        mask.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[8]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[8]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton mask2 = (ImageButton) findViewById(R.id.mask2_photo);
        mask2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[9]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[9]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton mask3 = (ImageButton) findViewById(R.id.mask3_photo);
        mask3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[10]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[10]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton dog = (ImageButton) findViewById(R.id.dog_photo);
        dog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[11]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[11]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton cat2 = (ImageButton) findViewById(R.id.cat2_photo);
        cat2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK[12]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK[12]).setBackgroundResource(R.drawable.round_background_select);
            }
        });


        //When choose photo
        ImageButton red_hat  = (ImageButton) findViewById(R.id.imb_redhat);
        red_hat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(findViewById(R.id.scrollView_photo_1).getVisibility() == GONE){
                    ((ImageButton) findViewById(R.id.imb_redhat)).setImageResource(R.drawable.face_select);
                    findViewById(R.id.scrollView_photo_1).setVisibility(View.VISIBLE);
                }else{
                    findViewById(R.id.scrollView_photo_1).setVisibility(GONE);
                    ((ImageButton) findViewById(R.id.imb_redhat)).setImageResource(R.drawable.face);
                }
            }
        });

        ImageButton no_filter_1 = (ImageButton) findViewById(R.id.no_filter_photo_1);
        no_filter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK1[0]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK1[0]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        ImageButton redhat_1 = (ImageButton) findViewById(R.id.redhat_photo_1);
        redhat_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(MASK1[1]).setBackgroundResource(R.drawable.round_background);
                findViewById(MASK1[1]).setBackgroundResource(R.drawable.round_background_select);
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(findViewById(R.id.imb_redhat).getVisibility() == GONE){
                    findViewById(R.id.imb_redhat).setVisibility(View.VISIBLE);
                    findViewById(R.id.face_photo).setVisibility(GONE);
                    findViewById(R.id.scrollView_photo).setVisibility(GONE);
                }
                chooseImage();
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                paint = new Paint();
                paint.setStrokeWidth(5);
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.STROKE);


                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);

                for(int i=0; i<faces.size(); i++) {
                    Face thisFace = faces.valueAt(i);
                    float x1 = thisFace.getPosition().x;
                    float y1 = thisFace.getPosition().y;
                    float x2 = x1 + thisFace.getWidth();
                    float y2 = y1 + thisFace.getHeight();
//                  tempCanvas.drawRoundRect(new RectF(x1, y1, x2, y2), 2, 2, paint);
                    RectF rectF = new RectF(x1,y1,x2,y2);
                    tempCanvas.drawRoundRect(rectF,2,2,paint);
                }

                imgView.setImageDrawable(new BitmapDrawable(getResources(),tempBitmap));


            }
        });

        no_filter_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);

                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.transparent);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x-120,face.getPosition().y -180,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        redhat_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //bmRedHat = Bitmap.createBitmap(bmRedHat.getWidth()/2, bmRedHat.getHeight()/2, Bitmap.Config.RGB_565);

            tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
            tempCanvas = new Canvas(tempBitmap);
            tempCanvas.drawBitmap(myBitmap, 0, 0, null);

            FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                    .setTrackingEnabled(false)
                    .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                    .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                    .build();

            if(!faceDetector.isOperational()){
                Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                return;
            }
            Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
            SparseArray<Face>  faces = faceDetector.detect(frame);

            if(faces.size() > 1) {
                bmRedHat = BitmapFactory.decodeResource(getResources(), R.drawable.redhat_mini);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    detectLandmarks(face);
                }
                Toast.makeText(PhotoDetector.this, faces.size() + "", Toast.LENGTH_SHORT).show();
            }
            else{
                bmRedHat = BitmapFactory.decodeResource(getResources(), R.drawable.redhat_mini);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    detectLandmarks(face);
                }
                Toast.makeText(PhotoDetector.this, faces.size() + "", Toast.LENGTH_SHORT).show();
            }
            imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        no_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);

                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.transparent);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x-120,face.getPosition().y -180,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);

                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.hair);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x-120,face.getPosition().y -180,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);


                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.op);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x - 120,face.getPosition().y -180,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        snap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);


                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.snap);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x ,face.getPosition().y,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        glasses2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);


                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.glasses2);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x -120,face.getPosition().y -180,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        glasses3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);


                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.glasses3);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x -120,face.getPosition().y -180,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        glasses4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);


                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.glasses4);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x -120,face.getPosition().y -180,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        glasses5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);


                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.glasses5);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x -120,face.getPosition().y -180,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        mask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);


                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.mask);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x -120,face.getPosition().y -180,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        mask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);


                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.mask2);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x -120,face.getPosition().y -180,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        mask3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);


                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.mask3);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x -120,face.getPosition().y -180,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);


                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.dog);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x ,face.getPosition().y ,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });

        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(),myBitmap.getHeight(),Bitmap.Config.RGB_565);
                tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                SparseArray<Face>  faces = faceDetector.detect(frame);


                bmRedHat = BitmapFactory.decodeResource(getResources(),R.drawable.cat2);
                for (int i = 0; i < faces.size(); i++) {
                    Face face = faces.valueAt(i);
                    float x = (face.getPosition().x + face.getWidth() / 2);
                    float y = (face.getPosition().y + face.getHeight() / 2);
                    float xOffset = (face.getWidth() / 2.0f);
                    float yOffset = (face.getHeight() / 2.0f);
                    float left = x - xOffset;
                    float top = y - yOffset;
                    tempCanvas.drawBitmap(bmRedHat,face.getPosition().x ,face.getPosition().y ,new Paint());
                }
                Toast.makeText(PhotoDetector.this, faces.size()+"", Toast.LENGTH_SHORT).show();

                imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });
//        btnChoose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(intent, RQS_LOADIMAGE);
//            }
//        });
//
//        btnCheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(myBitmap == null){
//                    Toast.makeText(PhotoDetector.this, "myBitmap == null", Toast.LENGTH_LONG).show();
//                }else{
//                    detectFace();
//                }
//            }
//        });
    }

    private void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUSET);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == PICK_IMAGE_REQUSET && resultCode == RESULT_OK && data != null && data.getData() != null){
//            filePath = data.getData();
//            try{
//                myBitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
//                imgView.setImageBitmap(myBitmap);
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        }

        if (requestCode == PICK_IMAGE_REQUSET
                && resultCode == RESULT_OK){

            if(myBitmap != null){
                myBitmap.recycle();
            }

            try {
                InputStream inputStream =
                        getContentResolver().openInputStream(data.getData());
                myBitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                imgView.setImageBitmap(myBitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private  void detectLandmarks(Face face){
        for(Landmark landmark:face.getLandmarks()){
            int cx = (int)(landmark.getPosition().x);
            int cy = (int)(landmark.getPosition().y);

            drawOnImageView(landmark.getType(),cx,cy);
        }
    }

    private void drawOnImageView(int type,int cx,int cy){
        if(type == Landmark.NOSE_BASE){
            int scaleWidth = bmRedHat.getScaledWidth(tempCanvas);
            int scaleHeight = bmRedHat.getScaledHeight(tempCanvas);
            tempCanvas.drawBitmap(bmRedHat,cx - (scaleWidth/2),cy - (scaleHeight),null);
          //  tempCanvas.drawBitmap(bmRedHat,cx,cy,null);
        }
    }
//    private void detectFace(){
//
//        //Create a Paint object for drawing with
//        Paint myRectPaint = new Paint();
//        myRectPaint.setStrokeWidth(5);
//        myRectPaint.setColor(Color.GREEN);
//        myRectPaint.setStyle(Paint.Style.STROKE);
//
////        Paint landmarksPaint = new Paint();
////        landmarksPaint.setStrokeWidth(10);
////        landmarksPaint.setColor(Color.RED);
////        landmarksPaint.setStyle(Paint.Style.STROKE);
////
////        Paint smilingPaint = new Paint();
////        smilingPaint.setStrokeWidth(4);
////        smilingPaint.setColor(Color.YELLOW);
////        smilingPaint.setStyle(Paint.Style.STROKE);
//
//        boolean somebodySmiling = false;
//
//        //Create a Canvas object for drawing on
//        Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.RGB_565);
//        Canvas tempCanvas = new Canvas(tempBitmap);
//        tempCanvas.drawBitmap(myBitmap, 0, 0, null);
//
//        //Detect the Faces
//
//        //!!!
//        //Cannot resolve method setTrackingEnabled(boolean)
//        //FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext()).build();
//        //faceDetector.setTrackingEnabled(false);
//
//        com.google.android.gms.vision.face.FaceDetector faceDetector =
//                new com.google.android.gms.vision.face.FaceDetector.Builder(getApplicationContext())
//                        .setTrackingEnabled(false)
//                        .setLandmarkType(com.google.android.gms.vision.face.FaceDetector.ALL_LANDMARKS)
//                        .setClassificationType(com.google.android.gms.vision.face.FaceDetector.ALL_CLASSIFICATIONS)
//                        .build();
//
//        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
//        SparseArray<Face> faces = faceDetector.detect(frame);
//
//        //Draw Rectangles on the Faces
//        for(int i=0; i<faces.size(); i++) {
//            Face thisFace = faces.valueAt(i);
//            float x1 = thisFace.getPosition().x;
//            float y1 = thisFace.getPosition().y;
//            float x2 = x1 + thisFace.getWidth();
//            float y2 = y1 + thisFace.getHeight();
//            tempCanvas.drawRoundRect(new RectF(x1, y1, x2, y2), 2, 2, myRectPaint);
//
//            //get Landmarks for the first face
//            List<Landmark> landmarks = thisFace.getLandmarks();
//            for(int l=0; l<landmarks.size(); l++){
//                PointF pos = landmarks.get(l).getPosition();
//                tempCanvas.drawPoint(pos.x, pos.y, landmarksPaint);
//            }
//
//            //check if this face is Smiling
//            final float smilingAcceptProbability = 0.5f;
//            float smilingProbability = thisFace.getIsSmilingProbability();
//            if(smilingProbability > smilingAcceptProbability){
//                tempCanvas.drawOval(new RectF(x1, y1, x2, y2), smilingPaint);
//                somebodySmiling = true;
//            }
//        }
//
//        imgView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
//
//        if(somebodySmiling){
//            Toast.makeText(PhotoDetector.this,
//                    "Done - somebody is Smiling",
//                    Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(PhotoDetector.this,
//                    "Done - nobody is Smiling",
//                    Toast.LENGTH_LONG).show();
//        }
//    }
}
