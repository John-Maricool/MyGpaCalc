package com.example.johnbatista.mygpacalc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyDatabase extends SQLiteOpenHelper {


    private static final String TABLE_NAME = "database";
    private static final int VERSION_NUMBER = 1;
     private SQLiteDatabase db = this.getWritableDatabase();
    private static final String ID = "MY_ID";
    private static final String GPA = "MY_GPA";
    private static final String NAME = "MY_NAME";
    Context context;

    public MyDatabase(Context context) {
        super(context, TABLE_NAME, null, VERSION_NUMBER);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String table =
                new StringBuilder().append("CREATE TABLE ").append(TABLE_NAME).append("(").append(ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ").append(GPA).append(" TEXT, ").append(NAME).append(" TEXT").append(")").toString();
        try {
            db.execSQL(table);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_TABLE = new StringBuilder().append("DROP TABLE IF EXISTS ").append(TABLE_NAME).toString();
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }


    public boolean AddToDatabase(String name, String gp){
        ContentValues contentValues = new ContentValues();
        contentValues.put(GPA, gp);
        contentValues.put(NAME, name);
        db.insert(TABLE_NAME, null, contentValues);

        db.close();
        return true;
    }

    public Cursor DisplayList(){
        Cursor cursor;
        cursor = db.query(TABLE_NAME, null,null, null, null, null, null);
        return cursor;
    }

    public boolean DeleteRow(String name){
        db.delete(TABLE_NAME, NAME
        + "=?", new String[]{name}) ;
        return  true;
    }
}
