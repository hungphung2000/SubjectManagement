package com.example.studentmanagement.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.example.studentmanagement.R;
import com.example.studentmanagement.SubjectActivity;
import com.example.studentmanagement.database.Database;
import com.example.studentmanagement.model.Subject;

import java.util.ArrayList;

public class SubjectAdapter extends BaseAdapter {
    private SubjectActivity subjectActivity;
    private ArrayList<Subject> arrayListSubject;

    public SubjectAdapter(SubjectActivity subjectActivity, ArrayList<Subject> arrayListSubject) {
        this.subjectActivity = subjectActivity;
        this.arrayListSubject = arrayListSubject;
    }

    @Override
    public int getCount() {
        return arrayListSubject.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListSubject.get(i);
    }

    @Override
    public long getItemId(int i) {
        return arrayListSubject.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) subjectActivity.getSystemService(subjectActivity.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.listsubject_item, null);

        ImageButton ibtnSubjectInfor = view.findViewById(R.id.ibtnSubjectInfor);
        ImageButton ibtnSubjectUpdate = view.findViewById(R.id.ibtnSubjectUpdate);
        ImageButton ibtnSubjectDelete = view.findViewById(R.id.ibtnSubjectDelete);

        int id = arrayListSubject.get(i).getId();

        ibtnSubjectInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectActivity.seeInfor(id);
            }
        });

        ibtnSubjectUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ibtnSubjectDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectActivity.deleteSubject(id);
            }
        });

        return view;
    }
}
