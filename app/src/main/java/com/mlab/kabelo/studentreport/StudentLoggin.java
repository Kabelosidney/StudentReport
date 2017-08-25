package com.mlab.kabelo.studentreport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.mlab.kabelo.studentreport.R.id.signupBtn1;

public class StudentLoggin extends AppCompatActivity {

    public Button Login,signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_loggin);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final DataBaseHelper helper = new DataBaseHelper(this);



        Login = (Button)findViewById(R.id.loginBtn1);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.loginBtn1){

                    EditText a = (EditText)findViewById(R.id.editUsername);
                    String str = a.getText().toString();

                    EditText b = (EditText)findViewById(R.id.editPassword1);
                    String pass = b.getText().toString();

                    String password = helper.searchPass(str);
                    if (!pass.equals(password)){

                        Intent kb = new Intent(StudentLoggin.this, StudentReports.class);
                        kb.putExtra("Username",str);
                        startActivity(kb);

                        Toast.makeText(StudentLoggin.this, "Successfully Logged in.Enter your datas", Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(StudentLoggin.this, "Username & Passwords don't match. Try again with the correct one", Toast.LENGTH_LONG).show();
                    }





                }
            }




        });
        signup=(Button)findViewById(R.id.signupBtn1);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kb = new Intent(StudentLoggin.this, StudentSignupActivity.class);
                startActivity(kb);
            }
        });

    }
}
