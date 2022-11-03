package com.pabiya.myinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editNombre;
    ImageButton entrarBoton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editNombre=findViewById(R.id.nombreEditText);
        entrarBoton=findViewById(R.id.entrarBoton);

        entrarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(editNombre.getText().toString())){
                    editNombre.setError("Introduce tu nombre");
                }else {
                    String name = editNombre.getText().toString();
                    Intent intent= new Intent(MainActivity.this,HubActivity.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}