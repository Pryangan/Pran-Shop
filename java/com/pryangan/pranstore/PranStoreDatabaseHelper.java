package com.pryangan.pranstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pryangan on 9/8/17.
 */

public class PranStoreDatabaseHelper  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PranStore.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_TYPE = "userType";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts(id integer primary key not null , "+
            "name text not null , email text not null , uname text not null , pass text not null, userType text not null);";

    private static final String TABLE_CREATE_PRODUCT= "create table product(id integer primary key not null , "+
            "" + "image integer not null , product_name text not null , description text not null);";

    public PranStoreDatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        sqLiteDatabase.execSQL(TABLE_CREATE_PRODUCT);
        this.db = sqLiteDatabase;
    }

    public void insertContact(Contact contact)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,contact.getName());
        values.put(COLUMN_EMAIL,contact.getEmail());
        values.put(COLUMN_UNAME,contact.getUname());
        values.put(COLUMN_PASS,contact.getPass());
        values.put(COLUMN_TYPE,contact.getType());

        db.insert(TABLE_NAME,null,values);
    }

    public void insertProduct(Product product)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from product";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put("image",product.getImageId());
        values.put("product_name",product.getTitle());
        values.put("description",product.getDescription());

        db.insert(TABLE_CREATE_PRODUCT,null,values);
    }

    public boolean isUserNameAlreadyExist(String username)
    {
        db = this.getReadableDatabase();
        String query = "select uname from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a;
        boolean chk =false;
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(username))
                {
                    chk = true;
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return chk;
    }

    public String searchPass(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, pass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b= "not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }

    public String searchType(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, userType from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b= "not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}