package com.ahmedteleb.requestnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class Edit_Activity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_ID =
            "com.ahmedteleb.requestnotes.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.ahmedteleb.requestnotes.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.ahmedteleb.requestnotes.EXTRA_DESCRIPTION";

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
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title_et_.getText().toString());
        data.putExtra(EXTRA_DESCRIPTION, subject_et_.getText().toString());

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
        Toast.makeText(getBaseContext(),"Update Success",Toast.LENGTH_SHORT).show();


    }
}
