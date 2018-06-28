package com.ahmedteleb.requestnotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.util.ArrayList;

/**
 * Created by Ahmed Teleb on 24-Aug-17.
 */

public class NotesAdapter extends BaseAdapter {
    ArrayList<Note> notes;

    public NotesAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notes.get(position).getNoteId();
    }

    @Override
    public View getView(int position, View oldView, ViewGroup adapterView)
    {
        oldView = LayoutInflater.from(adapterView.getContext())
                .inflate(R.layout.note_in_grid, null);

        TextView subject_et = oldView.findViewById(R.id.tv_sub_grid);
        TextView title_et = oldView.findViewById(R.id.tv_title_grid);

         Note note = getItem(position);

        subject_et.setText(note.getSubject());
        title_et.setText(note.getTitle());


        return oldView;
    }
}
