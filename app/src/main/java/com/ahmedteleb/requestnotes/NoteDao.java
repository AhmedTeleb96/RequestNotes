package com.ahmedteleb.requestnotes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao
{
    @Query("SELECT * FROM Note")
    List<Note> getAllNotes();


    @Insert
    void insertNote(Note note);

    @Delete
    void delete(Note note);
}
