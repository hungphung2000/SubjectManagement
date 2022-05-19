package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubjectInforActivity extends AppCompatActivity {
    TextView txtTitle, txtCredit, txtPlace, txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_infor);

        txtTitle = findViewById(R.id.txtSubjectInfortitle);
        txtCredit = findViewById(R.id.txtSubjectInforCredit);
        txtTime = findViewById(R.id.txtSubjectInforTime);
        txtPlace = findViewById(R.id.txtSubjectInforPlace);

        Intent intent = getIntent();
        txtTitle.setText(intent.getStringExtra("title"));
        txtCredit.setText(intent.getIntExtra("credit", 0) + "");
        txtTime.setText(intent.getStringExtra("time"));
        txtCredit.setText(intent.getStringExtra("place"));
    }
}