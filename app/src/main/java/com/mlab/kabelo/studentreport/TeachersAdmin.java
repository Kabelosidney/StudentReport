package com.mlab.kabelo.studentreport;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TeachersAdmin extends AppCompatActivity {

    //https://www.youtube.com/watch?v=F-k5gwz_91o



    DataBaseHandler peopleDB;

    Button btnAddData,btnViewData,btnUpdate,btnDelete;
    EditText etName,etIdentity,etGrade,etDate,etSub1,etSub2,etSub3,etSub4,etSub5,etSub6,etSub7,etStatus,etStudentNumber;
//toom
    //jom


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_admin);


        peopleDB = new DataBaseHandler(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String username = getIntent().getStringExtra("Username");

        etName = (EditText)findViewById(R.id.editText_name);
        etIdentity = (EditText)findViewById(R.id.editText_identity);
        etGrade = (EditText)findViewById(R.id.editText_grade);
        etDate = (EditText)findViewById(R.id.editText_date);
        etSub1 = (EditText)findViewById(R.id.editText_sub1);
        etSub2 = (EditText)findViewById(R.id.editText_sub2);
        etSub3 = (EditText)findViewById(R.id.editText_sub3);
        etSub4 = (EditText)findViewById(R.id.editText_sub4);
        etSub5 = (EditText)findViewById(R.id.editText_sub5);
        etSub6 = (EditText)findViewById(R.id.editText_sub6);
        etSub7 = (EditText)findViewById(R.id.editText_sub7);
        etStatus = (EditText)findViewById(R.id.editText__status);
        etStudentNumber = (EditText)findViewById(R.id.editText__student_number);
        btnAddData = (Button)findViewById(R.id.btnAdd);
        btnViewData = (Button)findViewById(R.id.btn_viewData);
        btnUpdate = (Button)findViewById(R.id.btn_Update);
        btnDelete = (Button)findViewById(R.id.btn_Delete);

        AddData();
        ViewData();
        UpdateData();
        deleteData();
    }

    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString();
                String identity = etIdentity.getText().toString();
                String grade = etGrade.getText().toString();
                String date = etDate.getText().toString();
                String sub1 = etSub1.getText().toString();
                String sub2 = etSub2.getText().toString();
                String sub3 = etSub3.getText().toString();
                String sub4 = etSub4.getText().toString();
                String sub5 = etSub5.getText().toString();
                String sub6 = etSub6.getText().toString();
                String sub7 = etSub7.getText().toString();
                String status = etStatus.getText().toString();

                boolean insertData = peopleDB.addData(name,identity,grade,date,sub1,sub2,sub3,sub4,sub5,sub6,sub7,status);
                if (insertData == true){
                    Toast.makeText(TeachersAdmin.this, "Data successfully Added", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(TeachersAdmin.this, "Remove all the texts except the Student Number ", Toast.LENGTH_SHORT).show();
                }

            }
        });
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

    }

    public void UpdateData(){

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = etStudentNumber.getText().toString().length();

                if (temp > 0){

                    boolean update = peopleDB.updateData(etStudentNumber.getText().toString(),etName.getText().toString(),etIdentity.getText().toString(),etDate.getText().toString(),etGrade.getText().toString(),etSub1.getText().toString(),etSub2.getText().toString(),etSub3.getText().toString(),etSub4.getText().toString(),etSub5.getText().toString(),etSub6.getText().toString(),etSub7.getText().toString(),etStatus.getText().toString());
                    if (update == true){
                        Toast.makeText(TeachersAdmin.this, "Data successfully updated", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(TeachersAdmin.this, "You must enter a Student Number to update", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = etStudentNumber.getText().toString().length();
                if (temp > 0){
                    Integer deleteRow = peopleDB.deleteData(etStudentNumber.getText().toString());
                    if (deleteRow > 0){

                        Toast.makeText(TeachersAdmin.this, "Data succesfully deleted", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(TeachersAdmin.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(TeachersAdmin.this, "You must enter a Stdent number to delete", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
