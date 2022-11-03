package com.pabiya.myinternet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FotoActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;

    String respuesta="No se pudo tomar la foto";
    Button volverBoton;
    ImageButton fotoImageBoton;
    ImageView fotoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

        volverBoton=findViewById(R.id.volverBoton);
        fotoImageBoton=findViewById(R.id.fotoImageBoton);
        fotoImageView=findViewById(R.id.fotoImageView);

        volverBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= getIntent();
                intent.putExtra("RESPUESTA_FOTO",respuesta);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        fotoImageBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(fotoIntent, REQUEST_IMAGE_CAPTURE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Bitmap image= data.getParcelableExtra("data");
            fotoImageView.setImageBitmap(image);
            respuesta="foto tomada";
        }
    }
}