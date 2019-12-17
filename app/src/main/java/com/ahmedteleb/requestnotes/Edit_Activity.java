package com.ahmedteleb.requestnotes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class Edit_Activity extends AppCompatActivity implements View.OnClickListener {
    EditText subject_et_ ;
    EditText title_et_ ;
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        subject_et_ = (EditText) findViewById(R.id.subject_ed_Update);
        title_et_ = (EditText) findViewById(R.id.title_ed_Update);
        btn_update = (Button) findViewById(R.id.btn_noteUpdate);
        btn_update.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        title_et_.setText(b.getString("title"));
        subject_et_.setText(b.getString("subject"));

    }

    @Override
    public void onClick(View view) {
        try {
        final Note note = new Note();

        DatabaseHelper helper = new DatabaseHelper(this);
        final Dao<Note, Integer> dao = helper.getNoteDao();

        note.setTitle(title_et_.getText().toString());
        note.setSubject(subject_et_.getText().toString());
        Bundle b =getIntent().getExtras();
        note.setNoteId(b.getInt("id"));


            dao.update(note);
            dao.notifyChanges();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Toast.makeText(getBaseContext(),"Update Success . . .",Toast.LENGTH_SHORT).show();



    }
}
