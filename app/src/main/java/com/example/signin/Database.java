package com.example.signin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users(username text, email text, password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "create table cart(username text, product text, price float, otype text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3 = ("create table orderplace(username text, fullname text, address text, contactno text, pincode int, date text, time text, amount float, otype text)");
        sqLiteDatabase.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void register(String username, String email, String password){
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("email",email);
        values.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,values);
        db.close();
    }

    public int login(String username, String password){
        int result=0;
        String[] str = new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select * from users where username=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }

    public void addToCart(String username, String product, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }

    public int checkCart(String username, String product){
        int result=0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select * from cart where username = ? and product = ?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    public void removeCart(String username, String otype){
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart","username=? and otype=?",str);
        db.close();
    }

    public ArrayList<String> getCartData(String username, String otype){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[2];
        str[0]=username;
        str[1]=otype;
        Cursor c = db.rawQuery("SELECT * FROM cart WHERE username = ? and otype = ?",str);

        if(c.moveToFirst()){
            do{
                String product = c.getString(1);
                float price = c.getFloat(2);
                arr.add(product+"$"+price);
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contactno",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }

    public ArrayList<String> getOrderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0]=username;
        Cursor c = db.rawQuery("Select * from orderplace where username = ?",str);
        if (c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time){
        int result=0;
        String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("Select * from orderplace where username = ? and fullname =? and address = ? and contactno = ? and date = ? and time =?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    // New method to delete an order from the order list
    public void deleteOrder(String username, String fullname, String date, String time) {
        String str[] = new String[4];
        str[0] = username;
        str[1] = fullname;
        str[2] = date;
        str[3] = time;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("orderplace", "username=? and fullname=? and date=? and time=?", str);
        db.close();
    }
}
