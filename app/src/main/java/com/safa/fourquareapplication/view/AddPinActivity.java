package com.safa.fourquareapplication.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.safa.fourquareapplication.MapsActivity;
import com.safa.fourquareapplication.R;
import com.safa.fourquareapplication.model.PinArea;

import java.io.IOException;

public class AddPinActivity extends AppCompatActivity {

    private EditText areaNameEditText, areaTypeEditText, areaAtmosphereEditText;
    private ImageView areaImageView;
    private PinArea pinArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pin);

        areaNameEditText = findViewById(R.id.areaName);
        areaTypeEditText = findViewById(R.id.areaType);
        areaAtmosphereEditText = findViewById(R.id.areaAtmosphere);
        areaImageView = findViewById(R.id.pinAreaImageView);

       pinArea = PinArea.instance();

    }

    public void onAddPin(View view) {
        if(areaNameEditText.getText().toString().equals("")){
            Toast.makeText(this, "Please enter place name", Toast.LENGTH_LONG).show();
        }else if (areaTypeEditText.getText().toString().equals("")){
            Toast.makeText(this, "Please enter place type", Toast.LENGTH_LONG).show();
        }else if (areaAtmosphereEditText.getText().toString().equals("")){
            Toast.makeText(this, "Please enter place atmosphere", Toast.LENGTH_LONG).show();
        }else if(areaImageView.getDrawable() == null){
            Toast.makeText(this, "Please choose place image", Toast.LENGTH_LONG).show();
        }else{

            pinArea.setAreaName(areaNameEditText.getText().toString());
            pinArea.setAreaType(areaTypeEditText.getText().toString());
            pinArea.setAreaAtmosphere(areaAtmosphereEditText.getText().toString());

            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);

        }

    }

    public void onChooseImage(View view) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else{
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults.length > 0){
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            System.out.println("image uri "+ uri);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                areaImageView.setImageBitmap(bitmap);
                pinArea.setAreaImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
