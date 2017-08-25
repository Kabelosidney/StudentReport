package com.mlab.kabelo.studentreport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentSignupActivity extends AppCompatActivity {

    public Button signUp,login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final DataBaseHelper helper = new DataBaseHelper(this);

        login=(Button)findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kb = new Intent(StudentSignupActivity.this,StudentLoggin.class);
                startActivity(kb);
            }
        });

        //onClicklistener when the signUp button is clicked
        signUp = (Button)findViewById(R.id.signupBtn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.signupBtn)
                {
                    //finding the components by ID
                    EditText name = (EditText)findViewById(R.id.TFName);
                    EditText email = (EditText)findViewById(R.id.TFEmail);
                    EditText username = (EditText)findViewById(R.id.TFUsername);
                    EditText password = (EditText)findViewById(R.id.TFPassword);
                    EditText confirm = (EditText)findViewById(R.id.TFConfirm);

                    String namestr = name.getText().toString();
                    String emailstr = email.getText().toString();
                    String usernamestr = username.getText().toString();
                    String passwordstr = password.getText().toString();
                    String confirmstr = confirm.getText().toString();

                    if (!passwordstr.equals(confirmstr))
                    {
                        //popup message when you enter the incorrect passwords
                        Toast.makeText(StudentSignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                    if (passwordstr.equals(confirmstr))
                    {
                        Intent kb = new Intent(StudentSignupActivity.this, StudentLoggin.class);
                        startActivity(kb);
                        //pop up message when the registration is successful(Toast)
                        Toast.makeText(StudentSignupActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                    }else{
                        //Insert the details on database
                        Contact c = new Contact();
                        c.setName(namestr);
                        c.setEmail(emailstr);
                        c.setUsername(usernamestr);
                        c.setPassword(passwordstr);

                        helper.insertContact(c);
                    }


                }
            }
        });



    }
}
