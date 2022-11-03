package com.pabiya.myinternet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HubActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textNombre;
    ImageButton navegarBoton,alarmaBoton,smsBoton,fotoBoton,llamarBoton,mapaBoton,mandarBoton;
    EditText mandarEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        String name;
        textNombre=findViewById(R.id.textViewNombre);

        navegarBoton=findViewById(R.id.navegarBoton);
        alarmaBoton=findViewById(R.id.alarmaBoton);
        smsBoton=findViewById(R.id.smsBoton);
        fotoBoton=findViewById(R.id.fotoBoton);
        llamarBoton=findViewById(R.id.llamarBoton);
        mapaBoton=findViewById(R.id.mapaBoton);
        mandarBoton=findViewById(R.id.mandarBoton);

        mandarEditText=findViewById(R.id.mandarEditText);

        Bundle bundle= getIntent().getExtras();
        name= bundle.getString("name");
        textNombre.setText("Hola "+name);


        navegarBoton.setOnClickListener(this);
        alarmaBoton.setOnClickListener(this);
        smsBoton.setOnClickListener(this);
        fotoBoton.setOnClickListener(this);
        llamarBoton.setOnClickListener(this);
        mapaBoton.setOnClickListener(this);
        mandarBoton.setOnClickListener(this);

    }
    static final int RESPUESTA_FOTO = 1;
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.navegarBoton:
                Intent navegaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.uji.es"));
                    startActivity(navegaIntent);

                break;
            case R.id.alarmaBoton:
                Intent alarmaIntent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "message")
                        .putExtra(AlarmClock.EXTRA_HOUR, 0)
                        .putExtra(AlarmClock.EXTRA_MINUTES, 45);
                    startActivity(alarmaIntent);

                break;
            case R.id.smsBoton:
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + 609000000));
                smsIntent.putExtra("sms_body", "Texto mensaje");
                    startActivity(smsIntent);

                break;
            case R.id.fotoBoton:
                Intent fotoIntent = new Intent(HubActivity.this,FotoActivity.class);
                startActivityForResult(fotoIntent,RESPUESTA_FOTO);

                break;
            case R.id.llamarBoton:
                try {
                    Intent llamaIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 963000000));
                    startActivity(llamaIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.mapaBoton:
                Uri gmmIntentUri = Uri.parse("geo:40.0, 0.0");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);

                break;
            case R.id.mandarBoton:
                String[] TO = {"emailde@destino.com"};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/html"); //text/plain
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Escribe aquí su asunto");
                emailIntent.putExtra(Intent.EXTRA_TEXT, mandarEditText.getText().toString());

                    startActivity(Intent.createChooser(emailIntent, "Enviar..."));

                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESPUESTA_FOTO ){
            String resultado= data.getExtras().getString("RESPUESTA_FOTO");
            textNombre.setText(resultado);
        }else
            Toast.makeText(this, "No hay respuesta", Toast.LENGTH_SHORT).show();
    }
}