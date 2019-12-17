package com.ahmedteleb.requestnotes;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.ActionMenuView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class ViewActivity extends AppCompatActivity  {

    TextView tv_title;
    TextView tv_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        tv_title = (TextView) findViewById(R.id.view_tv_title);
        tv_sub = (TextView) findViewById(R.id.view_tv_subject);

    }

    @Override
    protected void onResume() {
        super.onResume();

        DatabaseHelper helper = new DatabaseHelper(this);
        Dao<Note, Integer> dao = helper.getNoteDao();
        Bundle b = getIntent().getExtras();
        try {
            Note note = dao.queryForId(b.getInt("id"));
            tv_title.setText(note.getTitle());
            tv_sub.setText(note.getSubject());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.view_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle b = getIntent().getExtras();
        if(item.getItemId() == R.id.delete_icon)
        {
            try {

                DatabaseHelper helper = new DatabaseHelper(this);
                Dao<Note, Integer> dao = helper.getNoteDao();

                if (dao.idExists(b.getInt("id"))) {
                    dao.delete(dao.queryForId(b.getInt("id")));
                    Toast.makeText(this,"Delete Success . . .",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }catch (Exception e)
            {
                Toast.makeText(this,"error . . .",Toast.LENGTH_SHORT).show();
            }


        }
        else
        {

            Intent in = new Intent(this,Edit_Activity.class);
            in.putExtra("id",b.getInt("id"));
            in.putExtra("title",tv_title.getText().toString());
            in.putExtra("subject",tv_sub.getText().toString());

            startActivity(in);
        }
        return super.onOptionsItemSelected(item);
    }




}
