package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmanagement.database.Database;
import com.example.studentmanagement.model.Subject;

public class AddSubjectActivity extends AppCompatActivity {
    EditText edtTitle, edtCredit, edtTime, edtPlace;
    Button btnAdd;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        edtTitle = findViewById(R.id.edtTitleSubject);
        edtCredit = findViewById(R.id.edtSubjectCredit);
        edtTime = findViewById(R.id.edtSubjectTime);
        edtPlace = findViewById(R.id.edtSubjectPlace);
        btnAdd = findViewById(R.id.btnAddSubject);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAdd();
            }
        });
    }

    private void dialogAdd() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_dialog);
        dialog.setCanceledOnTouchOutside(true);

        Database database = new Database(this);
        Button btnYes = dialog.findViewById(R.id.btnYesAddSubject);
        Button btnNo = dialog.findViewById(R.id.btnNoAddSubject);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString().trim();
                String credit = edtCredit.getText().toString().trim();
                String time = edtTime.getText().toString().trim();
                String place = edtPlace.getText().toString().trim();

                if (title.equals("") || credit.equals(" ") || time.equals("") || place.equals("")) {
                    Toast.makeText(AddSubjectActivity.this, "Bạn chưa điền đầy đủ thông tin, vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddSubjectActivity.this, AddSubjectActivity.class);
                    startActivity(intent);
                } else {
                    database.addSubject(addSubject());

                    Toast.makeText(AddSubjectActivity.this, "Bạn đã thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddSubjectActivity.this, SubjectActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddSubjectActivity.this, SubjectActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    public Subject addSubject() {
        String title = edtTitle.getText().toString().trim();
        int credit = Integer.parseInt(edtCredit.getText().toString().trim());
        String time = edtTime.getText().toString().trim();
        String place = edtPlace.getText().toString().trim();

        Subject subject = new Subject(title, credit, time, place);
        return subject;
    }
}