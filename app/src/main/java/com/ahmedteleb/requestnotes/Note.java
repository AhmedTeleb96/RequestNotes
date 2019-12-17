package com.ahmedteleb.requestnotes;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * Created by Ahmed Teleb on 24-Aug-17.
 */
@Entity(tableName = "Note")
public class Note
{
    @ColumnInfo(name = "NoteId")
    @PrimaryKey(autoGenerate = true)
    private int  NoteId;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "subject")
    private String subject;

    public int getNoteId() {
        return NoteId;
    }

    public void setNoteId(int noteId) {
        NoteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
