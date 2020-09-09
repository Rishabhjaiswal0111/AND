package com.example.bookmart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddBook extends AppCompatActivity {
    private static final int PERMISSION_CODE = 1000;
    private static final int SELECT_CODE = 2000;
    Button camera_btn;
    ImageView display_img;
    Uri image_uri;
    Bitmap bitmap_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Intent intent=getIntent();
        display_img=findViewById(R.id.imagetoadd);
    }
    public void take_pic(View view) {
        Intent image_intent=new Intent((MediaStore.ACTION_IMAGE_CAPTURE));

        if(image_intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(image_intent,PERMISSION_CODE);
        }

    }
    public void select_pic(View view) {
        Intent select_img=new Intent(Intent.ACTION_PICK);
        select_img.setType("image/*");
        startActivityForResult(select_img,SELECT_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap_img = (Bitmap) extras.get("data");
            display_img.setImageBitmap(bitmap_img);
        }
        else if (requestCode == SELECT_CODE && resultCode == RESULT_OK){
            Uri picked_img=data.getData();
            InputStream inputStream= null;
            try {
                inputStream = getContentResolver().openInputStream(picked_img);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bitmap_img= BitmapFactory.decodeStream(inputStream);
            display_img.setImageBitmap(bitmap_img);
        }
    }
}
