package com.tiendatmagic.sqlitedemoapplicationmagicahihi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SQLiteDemoApplicationActivity extends AppCompatActivity {
    TextView datalist;
    EditText studentid;
    EditText studentname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_demo_application);

        datalist = (TextView) findViewById(R.id.txtData);
        studentid = (EditText) findViewById(R.id.phongbanid);
        studentname = (EditText) findViewById(R.id.phongbanname);
        Hello();
        Helloahihi();
    }

    public void Hello()
    {
        // System.exit(0);
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("© 2020 Copyright by Tiendatmagic - All Rights Reserved | Designed by Tiendatmagic 😁😁😁");
        dlgAlert.setTitle("Bản quyền:");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
//© 2020 Copyright by Tiendatmagic
    }

    public void  Helloahihi() {
        Toast toast=Toast.makeText(SQLiteDemoApplicationActivity.this, "© 2020 Copyright by Tiendatmagic  😀",   Toast.LENGTH_SHORT);
        toast.show();
    }
    // Các bạn có thấy mình đẹp trai không
    public void addPhongban(View view) {
        //khởi tạo đối tượng xử lý dữ liệu
        DataHandler dbHandler = new DataHandler(this, null, null, 1);
        //nhận id
        int id = Integer.parseInt(studentid.getText().toString());
        //nhận name
        String name = studentname.getText().toString();
        //gán id và name đến đối tượng Phongban
        Phongban student = new Phongban(id, name);
        //thêm đối tượng Phongban đến bảng dữ liệu
        dbHandler.addDataHandler(student);
        //xóa sạch các PlainText
        studentid.setText("");
        studentname.setText("");
        Helloahihi();
    }
    //© 2020 Copyright by Tiendatmagic
    public void loadPhongbans(View view) {
        //khởi tạo đối tượng xử lý dữ liệu
        DataHandler dbHandler = new DataHandler(this, null, null, 1);
        //hiển thị dữ liệu
        datalist.setText(dbHandler.loadDataHandler());
        //xóa sạch các PlainText
        studentid.setText("");
        studentname.setText("");
        Helloahihi();
    }

    public void deletePhongban(View view) {
        DataHandler dbHandler = new DataHandler(this, null, null, 1);
        boolean result = dbHandler.deleteDataHandler(Integer.parseInt(
                studentid.getText().toString()));
        if (result) {
            studentid.setText("");
            studentname.setText("");
            datalist.setText("Phongban Deleted");
        } else
            studentid.setText("No Match Found");
        Helloahihi();
    }
    // Các bạn có thấy mình đẹp trai không
    public void updatePhongban(View view) {
        DataHandler dbHandler = new DataHandler(this, null,null, 1);
        boolean result = dbHandler.updateDataHandler(Integer.parseInt(
                studentid.getText().toString()), studentname.getText().toString());
        if (result) {
            studentid.setText("");
            studentname.setText("");
            datalist.setText("Phongban Updated");
        } else
            studentid.setText("No Match Found");
        Helloahihi();
    }
    //© 2020 Copyright by Tiendatmagic
    public void findFirstPhongban(View view) {
        DataHandler dbHandler = new DataHandler(this, null, null, 1);
        Phongban student =
                dbHandler.findFisrtDataHandler
                        (studentname.getText().toString());
        if (student != null) {
            datalist.setText(String.valueOf(student.getPhongbanID())
                    + " " + student.getPhongbanName()
                    + System.getProperty("line.separator"));
            studentid.setText("");
            studentname.setText("");
        } else {
            datalist.setText("No Match Found");
            studentid.setText("");
            studentname.setText("");
        }
    }

    public void findAllPhongban(View view) {
        DataHandler dbHandler = new DataHandler(this, null, null, 1);
        List<Phongban> lst =
                dbHandler.findAllDataHandler
                        (studentname.getText().toString());
        String studentsList = "";
        if (!lst.isEmpty()) {
            for(Phongban st:lst)
            {
                studentsList += String.valueOf(st.getPhongbanID())
                        + " " + st.getPhongbanName()
                        + System.getProperty("line.separator");
                studentid.setText("");
                studentname.setText("");
            }
            datalist.setText(studentsList);
        } else {
            datalist.setText("No Match Found");
            studentid.setText("");
            studentname.setText("");
        }
    }
//© 2020 Copyright by Tiendatmagic
}


