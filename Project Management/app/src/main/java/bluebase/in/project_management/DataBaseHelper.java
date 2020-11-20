package bluebase.in.project_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    Context context;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "project_management.sqlite", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating Tables

        // User Master
        String employeeMasterTable = "CREATE TABLE user_master (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                                        "user_name VARCHAR NOT NULL, password VARCHAR NOT NULL)";
        db.execSQL(employeeMasterTable);

        // Project Customer Master
        String projectCustomerMasterTable = "CREATE TABLE project_customer_master (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                            "name VARCHAR, address VARCHAR, in_charge VARCHAR," +
                                            "gst_number VARCHAR, contact_number VARCHAR, status INTEGER, " +
                                            "created_by INTEGER, created_on VARCHAR)";
        db.execSQL(projectCustomerMasterTable);

        // Project Master
        String projectMasterTable = "CREATE TABLE project_master (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                    "name VARCHAR, customer_id VARCHAR, description VARCHAR," +
                                    "due_date VARCHAR, duration INTEGER, cost INTEGER, " +
                                    "status INTEGER, po_number INTEGER, po_detail VARCHAR, " +
                                    "created_by INTEGER, created_on VARCHAR)";
        db.execSQL(projectMasterTable);
    }

    public void deleteUserMaster(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("user_master", null, null);
        db.close();
    }

    public void insertUserMaster(String fullName, String userName, String password, String email, String mobileNo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_name", userName);
        cv.put("password", password);

        db.insert("user_master", null, cv);
        db.close();
    }

    public void deleteProjectCustomerMaster(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("project_customer_master", null, null);
        db.close();
    }

    public void insertProjectCustomerMaster(int id, String name, String address, String inCharge, String gstNumber, String contactNumber, int status, int createdBy, String createdOn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("name", name);
        cv.put("address", address);
        cv.put("in_charge", inCharge);
        cv.put("gst_number", gstNumber);
        cv.put("contact_number", contactNumber);
        cv.put("status", status);
        cv.put("created_by", createdBy);
        cv.put("created_on", createdOn);

        db.insert("project_customer_master", null, cv);
        db.close();
    }

    public void deleteProjectMaster(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("project_master", null, null);
        db.close();
    }

    public void insertProjectMaster(int id, String name, String customerId, String description, String dueDate, int duration, int cost, int status, int poNumber, String poDetail, int createdBy, String createdOn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("name", name);
        cv.put("customer_id", customerId);
        cv.put("description", description);
        cv.put("due_date", dueDate);
        cv.put("duration", duration);
        cv.put("cost", cost);
        cv.put("status", status);
        cv.put("po_number", poNumber);
        cv.put("po_detail", poDetail);
        cv.put("created_by", createdBy);
        cv.put("created_on", createdOn);

        db.insert("project_master", null, cv);
        db.close();
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // As of now no upgrading!
    }
}
