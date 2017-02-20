package com.utn.android.clase2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        findViewById(R.id.guardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtNombre = (TextView)findViewById(R.id.nombre_de_persona);
                TextView txtApellido = (TextView)findViewById(R.id.nombre_de_persona);
                TextView txtDir = (TextView)findViewById(R.id.nombre_de_persona);

                String nombre = txtNombre.getText().toString();
                String apellido = txtApellido.getText().toString();
                String dir = txtDir.getText().toString();

                Intent intent2 = new Intent();

                intent2.putExtra("nombre", nombre);
                intent2.putExtra("apellido", apellido);
                intent2.putExtra("dir", dir);


                Main2Activity.this.setResult(RESULT_OK, intent2);

                Main2Activity.this.finish();

            }
        });

    }
}
