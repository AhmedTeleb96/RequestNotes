package com.ahmedteleb.requestnotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Ahmed Teleb on 24-Aug-17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private final static String databaseName = "NotesDB";
    private final static int databaseVersion = 1;

    public DatabaseHelper(Context context) {
        super(context, databaseName,null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try
        {

            TableUtils.createTable(connectionSource,Note.class);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public Dao<Note,Integer> getNoteDao()
    {
        try {

            return getDao(Note.class);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
