package com.ahmedteleb.requestnotes;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by Ahmed Teleb on 24-Aug-17.
 */

public class Note_Item_Activity extends AppCompatActivity
{
    EditText subject_et ;
    EditText title_et ;
    Button   btn_insert;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_note);

        subject_et = (EditText) findViewById(R.id.subject_ed);
        title_et = (EditText) findViewById(R.id.title_ed);
        btn_insert = (Button) findViewById(R.id.btn_noteInsert);

        final Note note = new Note();

        DatabaseHelper helper = new DatabaseHelper(this);
        final Dao<Note, Integer> dao = helper.getNoteDao();

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                        note.setTitle(title_et.getText().toString());
                        note.setSubject(subject_et.getText().toString());

                        dao.createIfNotExists(note);
                        dao.notifyChanges();
                    Toast.makeText(getBaseContext(),"Insert Success . . .",Toast.LENGTH_SHORT).show();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


    }

}
