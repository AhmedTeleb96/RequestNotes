package com.ahmedteleb.requestnotes;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by Ahmed Teleb on 24-Aug-17.
 */

public class Note_Item_Activity extends AppCompatActivity
{
    public static final String EXTRA_TITLE =
            "com.ahmedteleb.requestnotes.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.ahmedteleb.requestnotes.EXTRA_DESCRIPTION";

    EditText subject_et ;
    EditText title_et ;
    Button   btn_insert;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_note);

        subject_et = findViewById(R.id.subject_ed);
        title_et =  findViewById(R.id.title_ed);
        btn_insert = findViewById(R.id.btn_noteInsert);


        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveNote();
            }
        });


    }

    private void saveNote() {
        String title = title_et.getText().toString();
        String description = subject_et.getText().toString();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);

        setResult(RESULT_OK, data);
        finish();
    }
}
