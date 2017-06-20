package com.example.kerkouche.tp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.kerkouche.tp.util.Consts.DATABASE_NAME;

/**
 * Created by kerkouche on 16/06/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String CREATE_TABLE = "CREATE TABLE RDV " +
            "(Id_rdv integer PRIMARY KEY," +
            "Valider VARCHAR(20), syncstatus integer);";

    public static final String DROP_TABLE="drop table if exists RDV;";

    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void saveToLocalDatabase(int Id_rdv,String Valider,int syncstatus,SQLiteDatabase database){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Id_rdv",Id_rdv);
        contentValues.put("Valider",Valider);
        contentValues.put("syncstatus",syncstatus);
        database.insert("RDV",null,contentValues);
    }

    public Cursor readFromLoaclDatabase(SQLiteDatabase database){
        String[] projection = {"Id_rdv","Valider","syncstatus"};
        return database.query("RDV",projection,null,null,null,null,null);
    }

    public void updateLocalDatabase(int Id_rdv,int syncstatus,SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put("syncstatus",syncstatus);
        String selection = "Id_rdv LIKE ?";
        String[] selection_args = {String.valueOf(Id_rdv)};
        database.update("RDV",contentValues,selection,selection_args);
    }
}
















