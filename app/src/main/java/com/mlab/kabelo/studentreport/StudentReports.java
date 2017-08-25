package com.mlab.kabelo.studentreport;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.id.list;

public class StudentReports extends AppCompatActivity {
    DataBaseHandler peopleDB;
    Button btnViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reports);

        peopleDB = new DataBaseHandler(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnViewData = (Button)findViewById(R.id.btn_viewData);

        ViewData();
    }

    public void ViewData(){

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data =  peopleDB.showData();

                if (data.getCount() == 0){

                    display("No Data","The Report is not added yet ");
                    return;

                }
                StringBuffer buffer = new StringBuffer();
                while(data.moveToNext()){
                    buffer.append("S.No.: " + data.getString(0) + "\n");
                    buffer.append("Full Names: " + data.getString(1) + "\n");
                    buffer.append("Identity: " + data.getString(2) + "\n");
                    buffer.append("Grade: " + data.getString(3) + "\n");
                    buffer.append("Date: " + data.getString(4) + "\n");
                    buffer.append("" + data.getString(5) + "\n");
                    buffer.append("" + data.getString(6) + "\n");
                    buffer.append("" + data.getString(7) + "\n");
                    buffer.append("" + data.getString(8) + "\n");
                    buffer.append("" + data.getString(9) + "\n");
                    buffer.append("" + data.getString(10) + "\n");
                    buffer.append("Average Percentage(100%)" + data.getString(11) + "\n");
                    buffer.append("Comment:" + data.getString(12) + "\n");


                    display("Students Reports:", buffer.toString());



                }
            }

        });
    }
    public  void  display(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
        builder.setView(list);

    }



}

