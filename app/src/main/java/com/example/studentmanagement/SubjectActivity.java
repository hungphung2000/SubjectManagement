package com.example.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.studentmanagement.adapter.SubjectAdapter;
import com.example.studentmanagement.database.Database;
import com.example.studentmanagement.model.Subject;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {
    Database database;
    SubjectAdapter subjectAdapter;
    ArrayList<Subject> subjectArrayList;
    Toolbar toolbar;
    ListView listView;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        listView = findViewById(R.id.listviewSubject);

        toolbar = findViewById(R.id.toobarSubject);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        subjectArrayList = new ArrayList<>();

        database = new Database(this);
        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            int credit = cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);


            subjectArrayList.add(new Subject(id,  title, credit, time, place));
        }
        cursor.moveToFirst();
        cursor.close();

        subjectAdapter = new SubjectAdapter(this, subjectArrayList);
        listView.setAdapter(subjectAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SubjectActivity.this, StudentActivity.class);
                int idSubject = subjectArrayList.get(i).getId();
                intent.putExtra("ID_SUBJECT", idSubject);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        count++;
        if(count >= 1) {
            Intent intent = new Intent(SubjectActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent = new Intent(SubjectActivity.this, AddSubjectActivity.class);
                startActivity(intent);
                break;
            default:
                Intent intent1 = new Intent(SubjectActivity.this, MainActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void seeInfor(int id) {
        Cursor cursor = database.getDataSubject();
        while(cursor.moveToNext()) {
            int idSubject = cursor.getInt(0);
            if (id == idSubject) {
                Intent intent = new Intent(SubjectActivity.this, SubjectInforActivity.class);
                intent.putExtra("title", cursor.getString(1));
                intent.putExtra("credit", cursor.getInt(2));
                intent.putExtra("time", cursor.getString(3));
                intent.putExtra("place", cursor.getString(4));

                startActivity(intent);
                break;
            }
        }
        cursor.moveToFirst();
        database.close();
    }

    public void deleteSubject(int id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.deletesubject_dialog);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesDeleteSubject);
        Button btnNo = dialog.findViewById(R.id.btnNoDeleteSubject);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.deleteSubject(id);
                Toast.makeText(SubjectActivity.this, "Bạn đã xóa thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SubjectActivity.this, SubjectActivity.class);
                startActivity(intent);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog.cancel();
            }
        });
        dialog.show();
    }
}