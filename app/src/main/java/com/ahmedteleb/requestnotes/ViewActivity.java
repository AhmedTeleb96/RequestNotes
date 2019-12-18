package com.ahmedteleb.requestnotes;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.ActionMenuView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class ViewActivity extends AppCompatActivity  {

    public static final String EXTRA_ID =
            "com.ahmedteleb.requestnotes.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.ahmedteleb.requestnotes.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.ahmedteleb.requestnotes.EXTRA_DESCRIPTION";
    public static final int EDIT_NOTE_REQUEST = 2;

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

        Intent intent = getIntent();

             setTitle("Edit Note");
             tv_title.setText(intent.getStringExtra(EXTRA_TITLE));
             tv_sub.setText(intent.getStringExtra(EXTRA_DESCRIPTION));

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

        if(item.getItemId() == R.id.edit_icon)
        {

            Intent response= getIntent();

            Intent intent = new Intent(this,Edit_Activity.class);
            intent.putExtra(EXTRA_ID, intent.getStringExtra(EXTRA_ID));
            intent.putExtra(EXTRA_TITLE, intent.getStringExtra(EXTRA_TITLE));
            intent.putExtra(EXTRA_DESCRIPTION, intent.getStringExtra(EXTRA_DESCRIPTION));
            startActivityForResult(intent,EDIT_NOTE_REQUEST);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }




}
