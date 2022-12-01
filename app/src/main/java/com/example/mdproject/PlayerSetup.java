package com.example.mdproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PlayerSetup extends AppCompatActivity {

    private EditText player1;
    private EditText player2;
    ImageView imageView;
    Button button;
    ImageView imageView2;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_setup);

        player1 = findViewById(R.id.editTextTextPersonName);
        player2 = findViewById(R.id.editTextTextPersonName2);

        imageView = findViewById(R.id.imageview);
        button = findViewById(R.id.button3); // Button to capture first player image
        imageView2 = findViewById(R.id.imageview2);
        button2 = findViewById(R.id.button4); // Button to capture second player image

        if (ContextCompat.checkSelfPermission(PlayerSetup.this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PlayerSetup.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        if (ContextCompat.checkSelfPermission(PlayerSetup.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PlayerSetup.this, new String[]{
                    Manifest.permission.CAMERA
            }, 101);
        }
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });

    }
        @Override
        protected void onActivityResult(int requestCode ,int resultCode ,@Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 100) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
            else if(requestCode == 101){
                Bitmap bitmap2 = (Bitmap) data.getExtras().get("data");
                imageView2.setImageBitmap(bitmap2);
            }
        }


    public void submitButtonClick(View view){
        String editTextTextPersonName = player1.getText().toString();
        String editTextTextPersonName2 = player2.getText().toString();

        Intent intent = new Intent(this,gameDisplay.class);
        intent.putExtra("PLAYER_NAME",new String[] {
                editTextTextPersonName,editTextTextPersonName2
        });
        startActivity(intent);

    }
}