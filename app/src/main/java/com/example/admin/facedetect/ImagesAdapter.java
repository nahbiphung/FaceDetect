package com.example.admin.facedetect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImagesAdapter extends ArrayAdapter {

    Context context;
    ArrayList<Images> arrayList;
    int layout;

    public ImagesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Images> objects) {
        super(context, resource, objects);

        this.context = context;
        this.arrayList = objects;
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Images image = arrayList.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,null);
        }

        ImageView img = convertView.findViewById(R.id.iv_image);
        img.setImageResource(image.getId());
        TextView txt= convertView.findViewById(R.id.tv_imageName);
        txt.setText(image.getName());

        return convertView;
    }
}
