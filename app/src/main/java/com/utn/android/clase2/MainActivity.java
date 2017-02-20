package com.utn.android.clase2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<String> nombres = new  ArrayList<String>();
    List<String> direcciones = new  ArrayList<String>();

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombres.add("Juan Couso");
        direcciones.add("Lezica 1234");

        lista = (ListView)findViewById(R.id.lista);
        lista.setAdapter( miAdapter);
        findViewById(R.id.agregar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent, 85);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 85){
            if(resultCode == Activity.RESULT_OK){
                String nombre = data.getStringExtra("nombre");
                String apellido = data.getStringExtra("apellido");
                String dir = data.getStringExtra("dir");

                nombres.add(nombre + " " + apellido);
                direcciones.add(dir);

                miAdapter.notifyDataSetChanged();
            }
        }
    }

    private BaseAdapter miAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return nombres.size();
        }

        @Override
        public String getItem(int position) {
            return nombres.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            String nombre = getItem(position);
            String direccion = direcciones.get(position);

            if(convertView == null){
                Log.d("Lista De Ejemplo", "(" + position + ") Estoy creando un nuevo objeto");
                LayoutInflater li = getLayoutInflater();
                convertView = li.inflate(R.layout.row_persona, parent, false);
            }else{
                Log.d("Lista De Ejemplo", "(" + position + ") Estoy reciclando un objeto");
            }

            TextView nombreView = (TextView) convertView.findViewById(R.id.nombre_de_persona);
            TextView dirView = (TextView) convertView.findViewById(R.id.direccion_de_persona);
            nombreView.setText(nombre);
            dirView.setText(direccion);

            return convertView;
        }
    };

}
