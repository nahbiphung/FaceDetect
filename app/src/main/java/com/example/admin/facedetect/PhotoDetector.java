package com.example.admin.facedetect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

import java.io.IOException;
import java.net.URI;

public class PhotoDetector extends AppCompatActivity {

    private Button btnChoose,btnCheck;
    private ImageView imgView;
    private Uri filePath;
    private  final  int PICK_IMAGE_REQUSET =71;
    private Paint paint;
    private Bitmap bitmap,tmpBitmap;
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
                com.google.android.gms.vision.face.FaceDetector faceDetector = new com.google.android.gms.vision.face.FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(com.google.android.gms.vision.face.FaceDetector.ALL_LANDMARKS)
                        .setMode(com.google.android.gms.vision.face.FaceDetector.FAST_MODE)
                        .build();
                if(!faceDetector.isOperational()){
                    Toast.makeText(PhotoDetector.this,"Face Detector could not be set up on your devices",Toast.LENGTH_SHORT).show();
                    return;
                }
                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<Face>  sparseArray = faceDetector.detect(frame);

                for(int i=0;i<sparseArray.size();i++){
                    Face face = (Face)sparseArray.valueAt(i);
                    float x1 = face.getPosition().x;
                    float y1 = face.getPosition().y;
                    float x2 = x1 + face.getWidth();
                    float y2 = y1 + face.getWidth();
                    RectF rectF = new RectF(x1,y1,x2,y2);
                    Canvas canvas = new Canvas(tmpBitmap);
                    canvas.drawRoundRect(rectF,2,2,paint);
                }

                imgView.setImageDrawable(new BitmapDrawable(getResources(),tmpBitmap));
            }
        });
    }

    private void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUSET);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUSET && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imgView.setImageBitmap(bitmap);

                paint = new Paint();
                paint.setStrokeWidth(5);
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.STROKE);

                tmpBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(tmpBitmap);
                canvas.drawBitmap(bitmap,0,0,null);

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
