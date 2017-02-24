package com.utn.android.clase2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.utn.android.clase2.R.id.nombre;

public class MainActivity extends AppCompatActivity {


    List<Persona> personas = new ArrayList<>();

    DbHelper dbHelper;
    SQLiteDatabase dbRead;
    SQLiteDatabase dbWrite;

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);

        dbRead = dbHelper.getReadableDatabase();
        dbWrite = dbHelper.getWritableDatabase();


        //personas.add(new Persona("Juan Manuel", "Couso", "lezica 1234", null));
        //addPersona(personas.get(0));

        loadDb();

        lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(miAdapter);
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

        if (requestCode == 85) {
            if (resultCode == Activity.RESULT_OK) {

                String nombre = data.getStringExtra("nombre");
                String apellido = data.getStringExtra("apellido");
                String dir = data.getStringExtra("dir");
                String img = data.getStringExtra("img");
                personas.add(new Persona(nombre, apellido, dir, img));

                if (personas != null && !personas.isEmpty()) {
                    addPersona(personas.get(personas.size() - 1));
                }

                miAdapter.notifyDataSetChanged();
            }
        }
    }

    private BaseAdapter miAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return personas.size();
        }

        @Override
        public Persona getItem(int position) {
            return personas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Persona datos = getItem(position);


            if (convertView == null) {
                Log.d("Lista De Ejemplo", "(" + position + ") Estoy creando un nuevo objeto");
                LayoutInflater li = getLayoutInflater();
                convertView = li.inflate(R.layout.row_persona, parent, false);
            } else {
                Log.d("Lista De Ejemplo", "(" + position + ") Estoy reciclando un objeto");
            }

            TextView nombreView = (TextView) convertView.findViewById(R.id.nombre_de_persona);
            TextView dirView = (TextView) convertView.findViewById(R.id.direccion_de_persona);
            ImageView avatar = (ImageView) convertView.findViewById(R.id.smallAvatar);
            nombreView.setText(datos.getNombre() + ", " + datos.getApellido());
            dirView.setText(datos.getDireccion());
            if (datos.getImagen() != null) {
                setPic(datos, avatar);
            } else {
                avatar.setBackground(getDrawable(R.mipmap.ic_launcher));
                avatar.setImageBitmap(null);
            }

            return convertView;
        }
    };

    private void setPic(Persona persona, ImageView mImageView) {
        // Get the dimensions of the View
        int targetW = mImageView.getMeasuredWidth();

        int targetH = mImageView.getMeasuredHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(persona.getImagen(), bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(persona.getImagen(), bmOptions);
        mImageView.setBackground(null);
        mImageView.setImageBitmap(bitmap);
    }

    private void loadDb() {


        Cursor cursor = dbWrite.rawQuery("SELECT nombre, apellido, direccion, imagen FROM " + DbHelper.TABLE_PERSONAS, null);

        cursor.moveToFirst();
        //if (cursor != null && cursor.getCount() > 0) {


        while (!cursor.isAfterLast()) {
            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            String apellido = cursor.getString(cursor.getColumnIndex("apellido"));
            String direccion = cursor.getString(cursor.getColumnIndex("direccion"));
            String imagen = cursor.getString(cursor.getColumnIndex("imagen"));

            personas.add(new Persona(nombre, apellido, direccion, imagen));

            cursor.moveToNext();
        }
        //}


        cursor.close();
    }

    public void addPersona(Persona persona) {//(String nombre,String apellido,String direccion,String imagen){

        try {
            //Cursor cursor = dbWrite.rawQuery("INSERT INTO " + dbHelper.TABLE_PERSONAS +
            //                " ([nombre], [apellido], [imagen], [direccion]) VALUES (?, ?, ?, ?)",
            //       new String[]{persona.getNombre(), persona.getApellido(), persona.getImagen(), persona.getDireccion()});

            Cursor cursor = dbWrite.rawQuery("INSERT INTO " + dbHelper.TABLE_PERSONAS + " VALUES ('" + "', '" + persona.getNombre() + "', '" + persona.getApellido() + "', '" + persona.getImagen() + "', '" + persona.getDireccion() + "')", null);
            cursor.close();
        } catch (Exception e) {
            Log.e("app Lista SQL", "error en clase add persona (" + persona.getNombre() + " " + persona.getApellido() + ")");
            e.printStackTrace();
        }


    }

}
