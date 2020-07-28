package com.tiendatmagic.sqlitedemoapplicationmagicahihi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHandler extends SQLiteOpenHelper {

    // các biến mô tả cơ sở dữ liệu
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Tiendatmagicahihi.db";
    public static final String TABLE_NAME = "Phongbans";
    public static final String COLUMN_ID = "PhongbanID";
    public static final String COLUMN_NAME = "PhongbanName";

    //phương thức khởi tạo
    public DataHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    // Các bạn có thấy mình đẹp trai không
    @Override
    public void onCreate(SQLiteDatabase db) {

        //chuỗi lệnh truy vấn tạo bảng Phongbans
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT )";

        //thực thi truy vấn
        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        //Xóa bảng nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //Tạo bảng mới
        onCreate(db);
    }
    //© 2020 Copyright by Tiendatmagic
    public String loadDataHandler() {
        String result = "";
        //chuỗi truy vấn SELECT
        String query = "SELECT* FROM " + TABLE_NAME;
        //sẵn sàng thực thi các truy vấn
        SQLiteDatabase db = this.getWritableDatabase();
        //thực thi truy vấn bằng phương thức rawQuery()
        //kết quả trả về lưu trong đối tượng Cursor
        Cursor cursor = db.rawQuery(query, null);
        //duyệt qua dữ liệu từ đối tượng Cursor
        while (cursor.moveToNext()) {
            //nhận giá trị cột thứ nhất (PhongbanID)
            int result_0 = cursor.getInt(0);
            //nhận giá trị cột thứ hai (PhongbanName)
            String result_1 = cursor.getString(1);
            //hiển thị mỗi hàng trong một chuỗi
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        //đóng đối tượng Cursor
        cursor.close();
        //đóng đối tượng SQLiteDatabase
        db.close();
        return result;
    }

    //thêm dữ liệu đến bảng Phongbans
    public void addDataHandler(Phongban student) {
        //tạo đối tượng ContentValues
        ContentValues values = new ContentValues();
        //thêm giá trị các cột đến đối tượng ContentValues
        values.put(COLUMN_ID, student.getPhongbanID());
        values.put(COLUMN_NAME, student.getPhongbanName());
        SQLiteDatabase db = this.getWritableDatabase();
        //chèn dữ liệu đến bảng
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    // Các bạn có thấy mình đẹp trai không
    public boolean deleteDataHandler(int ID) {
        boolean result = false;
        String query = "Select * FROM "
                + TABLE_NAME + " WHERE "
                + COLUMN_ID + " = '"
                + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Phongban student = new Phongban();
        if (cursor.moveToFirst()) {
            student.setPhongbanID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(student.getPhongbanID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    //© 2020 Copyright by Tiendatmagic
    public boolean updateDataHandler(int ID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        return db.update(TABLE_NAME, args, COLUMN_ID + " = " + ID, null) > 0;
    }

    //tìm kiếm Phongban theo PhongbanName
//kết quả trả về là Phongban đầu tiên trong danh sách kết quả
    public Phongban findFisrtDataHandler(String studentname) {

        //chuỗi truy vấn tìm kiếm Phongban theo PhongbanName
        String query = "Select * FROM " + TABLE_NAME
                + " WHERE " + COLUMN_NAME + " = "
                + "'" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        // Thực thi truy vấn và gán kết quả đến đối tượng Cursor
        Cursor cursor = db.rawQuery(query, null);
        Phongban student = new Phongban();
        //trả về hàng đầu tiên trong kết quả
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setPhongbanID(Integer.parseInt(cursor.getString(0)));
            student.setPhongbanName(cursor.getString(1));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        //trả về sinh viên đầu tiên tìm được
        return student;
    }
    //© 2020 Copyright by Tiendatmagic
    //tìm kiếm Phongban theo PhongbanName
//kết quả trả về là tất cả Phongban trong danh sách kết quả
    public List<Phongban> findAllDataHandler(String studentname) {
        //chuỗi truy vấn tìm kiếm Phongban theo PhongbanName
        String query = "Select * FROM " + TABLE_NAME
                + " WHERE " + COLUMN_NAME + " = "
                + "'" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        //danh sách chứa tất cả các Phongban tìm được
        List<Phongban> lst =  new ArrayList<Phongban>();
        // Thực thi truy vấn và gán kết quả đến đối tượng Cursor
        Cursor cursor = db.rawQuery(query, null);
        //duyệt qua tất cả các hàng từ hàng đầu tiên
        if(cursor.moveToFirst()) {
            do {
                Phongban student = new Phongban();
                student.setPhongbanID
                        (Integer.parseInt(cursor.getString(0)));
                student.setPhongbanName(cursor.getString(1));
                lst.add(student);
            }while (cursor.moveToNext());
        }
        //đóng các đối tượng
        cursor.close();
        db.close();
        //trả về danh sách sinh viên tìm được
        return lst;
    }

// Các bạn có thấy mình đẹp trai không

}