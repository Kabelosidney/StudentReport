package com.mlab.kabelo.studentreport;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button studentBtn,teachersBtn;
    //https://www.youtube.com/watch?v=1ByOLvrJDTA
    //https://www.youtube.com/watch?v=cp2rL3sAFmI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teachersBtn = (Button)findViewById(R.id.teacher);
        teachersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TeachersLoggin.class);
                startActivity(intent);
            }
        });

        studentBtn = (Button)findViewById(R.id.student);
        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentLoggin.class);
                startActivity(intent);
            }
        });
    }
}
