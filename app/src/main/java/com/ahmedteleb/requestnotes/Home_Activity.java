package com.ahmedteleb.requestnotes;

import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Home_Activity extends AppCompatActivity {


    private NoteViewModel noteViewModel;
    FloatingActionButton fab;
    RecyclerView noteRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        fab = findViewById(R.id.myFAB);
        noteRecycler = findViewById(R.id.list_Notes);
        noteRecycler.setLayoutManager(new GridLayoutManager(this,2));

        final NotesAdapter notesAdapter =new NotesAdapter();
                noteRecycler.setAdapter(notesAdapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes)
            {
                notesAdapter.setNotes(notes);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(getApplicationContext(), Note_Item_Activity.class);
                startActivity(i);
            }

        });

        final Intent i2 = new Intent(this, ViewActivity.class);

      /*  listNotes.On(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                TextView tv_title= view.findViewById(R.id.tv_title_grid);
                TextView tv_sub=view.findViewById(R.id.tv_sub_grid);


                try {
                    DatabaseHelper helper = new DatabaseHelper(adapterView.getContext());
                    NotesAdapter adapter = new NotesAdapter((ArrayList<Note>) helper.getNoteDao().queryForAll());
                    Note note = adapter.getItem(i);


                i2.putExtra("id", note.getNoteId());
                i2.putExtra("title",tv_title.getText().toString());
                i2.putExtra("subject",tv_sub.getText().toString());

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                startActivity(i2);


            }
        });
    }



    private void getNoteOffline() {
        DatabaseHelper helper = new DatabaseHelper(this);
        try {

            showFlowers((ArrayList<Note>) helper.getNoteDao().queryForAll());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    NotesAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        getNoteOffline();

    }

    private void showFlowers(ArrayList<Note> notes) {

        adapter = new NotesAdapter(notes);

        adapter.notifyDataSetChanged();

        listNotes.setAdapter(adapter);

    }


       */
    }
}
