package bluebase.in.ats;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    Context context;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "ats.sqlite", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating Tables

        // User Master
        String createEmployeeMasterTable = "CREATE TABLE user_master (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, full_name VARCHAR NOT NULL, user_name VARCHAR NOT NULL, password VARCHAR NOT NULL , email VARCHAR NOT NULL, mobile_no VARCHAR NOT NULL)";
        db.execSQL(createEmployeeMasterTable);

    }

    public void deleteUserMaster(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("user_master", null, null);
        db.close();
    }

    public void insertUserMaster(String fullName, String userName, String password, String email, String mobileNo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("full_name", fullName);
        cv.put("user_name", userName);
        cv.put("password", password);
        cv.put("email", email);
        cv.put("mobile_no", mobileNo);

        db.insert("user_master", null, cv);
        db.close();
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // As of now no upgrading!
    }
}
