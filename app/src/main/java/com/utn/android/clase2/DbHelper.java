package com.utn.android.clase2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diseño multimedial on 20/02/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_FILENAME = "mi_archivo_de_base_de_datos.db";
    private static final int CURRENT_VERSION = 2;

    public static final String TABLE_PERSONAS = "persona";

    private static final String CREATE_USER_TABLE_SQL = "CREATE TABLE " + TABLE_PERSONAS + " ("
            + "  id integer primary key autoincrement,"
            + "  nombre varchar(64) NOT NULL,"
            + "  apellido varchar(64) DEFAULT NULL,"
            + "	 imagen varchar(64)	DEFAULT NULL,"
            + "	 direccion varchar(64) DEFAULT NULL)";

    public DbHelper(Context context) {
        super(context, DATABASE_FILENAME, null, CURRENT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE_SQL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE " + TABLE_PERSONAS + ";");
        //db.execSQL(CREATE_USER_TABLE_SQL);
    }
}
