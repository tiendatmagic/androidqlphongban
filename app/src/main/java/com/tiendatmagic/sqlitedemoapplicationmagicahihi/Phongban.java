package com.tiendatmagic.sqlitedemoapplicationmagicahihi;

public class Phongban {
    //các biến tương ứng với các cột của bảng Phongbans
    private int _studentid;
    private String _studentname;

    //Các phương thức khởi tạo (constructors)
    //Phương thức khởi tạo mặc định
    public Phongban(){
//© 2020 Copyright by Tiendatmagic
    }
    //Phương thức khởi tạo có tham số
    public Phongban(int id, String name){
        this._studentid = id;
        this._studentname = name;
    }
    // Các bạn có thấy mình đẹp trai không

    //các phương thức truy cập các biến thành viên

    public int getPhongbanID(){
        return this._studentid;
    }
    public void setPhongbanID(int id){
        this._studentid = id;
    }

    public String getPhongbanName(){
        return this._studentname;
    }
    public void setPhongbanName(String name){
        this._studentname = name;
    }



}