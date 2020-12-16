package com.example.ex4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DBHelper(this);
        e1=(EditText)findViewById(R.id.stud_name);
        e2=(EditText)findViewById(R.id.stud_rollNo);
    }

    public void insert(View view) {
        mydb.insertData(e1.getText().toString(),e2.getText().toString());
        Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show();
    }

    public void clear(View view) {
        e1.setText("");
        e2.setText("");
    }

    public void viewData(View view) {
        Cursor c=mydb.getAllData();
        if(c.getCount()==0){showDialog("alert","No data found"); }
        else{
            StringBuilder sb=new StringBuilder();
            while(c.moveToNext()){
                sb.append("Name: ").append(c.getString(0)).append("\n");
                sb.append("Roll no: ").append(c.getString(1)).append("\n");
            }
            showDialog("Data",sb.toString());
        }
    }
    public void showDialog(String title,String msg){
        AlertDialog.Builder bu=new AlertDialog.Builder(this);
        bu.setCancelable(true);
        bu.setTitle(title);
        bu.setMessage(msg);
        bu.show();
    }
}