package com.ahmedteleb.requestnotes;

import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Home_Activity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;


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


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder
                    , @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                noteViewModel.delete(notesAdapter.getNoteAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(noteRecycler);


        notesAdapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(Home_Activity.this, Edit_Activity.class);
                intent.putExtra(Edit_Activity.EXTRA_ID, note.getNoteId());
                intent.putExtra(Edit_Activity.EXTRA_TITLE, note.getTitle());
                intent.putExtra(Edit_Activity.EXTRA_DESCRIPTION, note.getSubject());
                startActivityForResult(intent,EDIT_NOTE_REQUEST);
            }
        });

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Intent i = new Intent(getApplicationContext(), Note_Item_Activity.class);
                        startActivityForResult(i, ADD_NOTE_REQUEST);
                    }

                });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(Note_Item_Activity.EXTRA_TITLE);
            String description = data.getStringExtra(Note_Item_Activity.EXTRA_DESCRIPTION);

            Note note = new Note(title, description);
            noteViewModel.insert(note);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(Edit_Activity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(Edit_Activity.EXTRA_TITLE);
            String description = data.getStringExtra(Edit_Activity.EXTRA_DESCRIPTION);

            Note note = new Note(title, description);
            note.setNoteId(id);
            noteViewModel.update(note);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
