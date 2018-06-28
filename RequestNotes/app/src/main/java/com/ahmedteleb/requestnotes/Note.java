package com.ahmedteleb.requestnotes;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Ahmed Teleb on 24-Aug-17.
 */

public class Note
{
    @DatabaseField(columnName = "NoteId", generatedId = true)
    private int  NoteId;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "subject")
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
