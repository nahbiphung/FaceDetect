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
import android.media.FaceDetector;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

public class PhotoDetector extends AppCompatActivity {

    private Button btnChoose,btnCheck;
    private ImageView imgView;

    private Uri filePath;
    private  final  int PICK_IMAGE_REQUSET =71;

    private Paint paint;
    private Bitmap myBitmap,tempBitmap;

//    private static final int RQS_LOADIMAGE = 1;
//    private Bitmap myBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detector);

        btnChoose = findViewById(R.id.btn_choose);
        btnCheck = findViewById(R.id.btn_check);
        imgView = findViewById(R.id.imagesView);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Canvas tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                com.google.android.gms.vision.face.FaceDetector faceDetector = new com.google.android.gms.vision.face.FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(com.google.android.gms.vision.face.FaceDetector.ALL_LANDMARKS)
                        .setClassificationType(com.google.android.gms.vision.face.FaceDetector.ALL_CLASSIFICATIONS)
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
//                    tempCanvas.drawRoundRect(new RectF(x1, y1, x2, y2), 2, 2, paint);
                    RectF rectF = new RectF(x1,y1,x2,y2);
                    tempCanvas.drawRoundRect(rectF,2,2,paint);
                }

                imgView.setImageDrawable(new BitmapDrawable(getResources(),tempBitmap));


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
//        if (requestCode == RQS_LOADIMAGE
//                && resultCode == RESULT_OK){
//
//            if(myBitmap != null){
//                myBitmap.recycle();
//            }
//
//            try {
//                InputStream inputStream =
//                        getContentResolver().openInputStream(data.getData());
//                myBitmap = BitmapFactory.decodeStream(inputStream);
//                inputStream.close();
//                imgView.setImageBitmap(myBitmap);
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUSET && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();
            try{
                myBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imgView.setImageBitmap(myBitmap);

            }
            catch (IOException e){
                e.printStackTrace();
            }
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
